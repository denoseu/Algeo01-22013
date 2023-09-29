package src.Functions;
import java.text.DecimalFormat;
import java.util.Scanner;


import src.Matrix.*;


public class regresiLinearBerganda {
    public static Scanner scan;

    // Mencari nilai beta
    public static double[] solutionReg(double[][] mat){
        double[][] matriks = matrixReg(mat);
        double[] solusi = matrixOP.getGaussMatrix(matriks);
        return solusi;
        // matrixOP.hasilSPLGauss(matriks);
    }

    // Mencari taksiran nilai x
    public static double estimateReg(double[] s, double[] x){
        double result = 0;
        for (int i=0 ; i<s.length;i++){
            if (i == 0){
                result += s[i];
            } else {
                result += s[i] * x[i-1];
            }
        }
        return result;
    }

    public static double[] inputTaksiran(double[][] m){
        double[] x = new double[(m[0].length -1)];
        scan = new Scanner(System.in);
        System.out.println("Masukkan x yang ingin ditaksir: ");
        for (int i = 0; i< x.length;i++){
            System.out.print("x" + (i+1) + ": ");
            x[i] = scan.nextDouble();
        }
        return x;
    }

    public static double[][] inputReg(){
        scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah pengubah x: "); int n = scan.nextInt();
        System.out.print("Masukkan banyak sampel yang diinginkan: "); int m = scan.nextInt();
        double[][] matrix = new double[m][n+1];

        for (int i =0;i<m;i++){
            System.out.println("Masukkan titik untuk sampel " + (i+1) + " : ");
            for (int j=0;j<n+1;j++){
                if (j == n){
                    System.out.print("y"+ (i+1) + ": ");
                } else {
                    System.out.print("x" + (j+1) + ": ");
                }
                matrix[i][j] = scan.nextDouble();
            }
        }
        return matrix;
    }

    // Mencari matrix normal estimation equation
    public static double[][] matrixReg(double[][] a){
        double[][] m = new double[a[0].length][a[0].length+1];
        for (int i =0; i<(a[0].length);i++){
            for (int j = 0;j<(a[0].length+1);j++){
                if (i==0){
                    if (j==0){
                        m[i][j] = a.length;
                    } else {
                        m[i][j] = addOneCol(a, j-1);
                    }
                } else {
                    if (j==0){
                        m[i][j] = addOneCol(a, i-1);

                    } else {
                        m[i][j] = multiplyEl(a, i-1, j-1);
                    }
                }
            }
        }
        return m;

    }

    // Menjumkahkan semua satu baris
    public static double addOneCol(double[][] m, int col){
        double result = 0;
        for (int i = 0;i<m.length;i++){
            result += m[i][col];
        }
        return result;
    }

    // Mendapatkan hasil kali pengubah x
    public static double multiplyEl(double[][] m, int colSelector, int colAcuan)
    {
        double result;
        result = 0;
        for (int i=0;i<m.length;i++){
            for (int j=0;j<m[0].length;j++){
                if (j==colAcuan){
                    result += m[i][colSelector] * m[i][j];
                }
            }
        }
        return result;
    }

    public static void hasilRLB(double[] s){
        System.out.print("f(x)= ");
        DecimalFormat df = new DecimalFormat("0.000");
        for (int i = 0 ; i<s.length;i++){
            if (i == 0){
                System.out.print(df.format(s[i]));
            } else {
                if (s[i] < 0){
                    System.out.print(" - " + df.format(Math.abs(s[i])));
                } else{
                    System.out.print(" + " + df.format(s[i]));
                }
            }
        }
    }

    public static void hasilEstimateRLB(double result){
        DecimalFormat df = new DecimalFormat("0.000");
        System.out.print("Hasil taksiran: ");
        System.out.print(df.format(result));
    }
    public static void main(String[] args){
        // double [][] a = inputReg();
        // double[][] a = matrixIO.fileToMatrix(2);
        // matrixIO.displayMatrix(matrixReg(a));
        // double[] s = solutionReg(a);
        // for (int i= 0; i<s.length;i++){
        //     System.out.print("b" + i + " ");
        //     System.out.println(s[i]);
        // }
        double[][] m = matrixIO.fileToMatrix(1);
        double[] x = matrixIO.getAB("./test/input/text.txt",2);
        double[] s = solutionReg(m);
        hasilRLB(s);
        double result = estimateReg(s, x);
        hasilEstimateRLB(result);
        // for (int i=0;i<s.length;i++){
        //     System.out.println("b" + i);
        //     System.out.println(s[i]);
        // 
        // System.out.println(result);
        // matrixIO.displayMatrix(s);
        // solutionReg(m);
        // solutionReg(m);
    }

    
}
