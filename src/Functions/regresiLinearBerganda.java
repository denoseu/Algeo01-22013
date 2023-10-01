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
        System.out.print("f(x) = ");
        DecimalFormat df = new DecimalFormat("0.000");
        for (int i = 0 ; i<s.length;i++){
            if (i == 0){
                if (s[i] == 0){
                    System.out.print("");
                } else {
                    System.out.print(df.format(s[i]));
                }
            } else {
                if (s[i] < 0){
                    System.out.print(" - " + df.format(Math.abs(s[i])));
                    System.out.print("x" + i);
                } else{
                    System.out.print(" + " + df.format(s[i]));
                    System.out.print("x" + i);
                }
            }
        }
        System.out.println();
    }

    public static void hasilEstimateRLB(double result, double[] taksiran){
        DecimalFormat df = new DecimalFormat("0.000");
        String fx = "f(";
        System.out.println("Hasil taksiran: ");
        for (int i = 0;i<taksiran.length;i++){
            if (i == (taksiran.length-1)){
                fx += df.format(taksiran[i]);
                fx += ")= ";
            } else {
                fx += df.format(taksiran[i]);
                fx += ",";
            }
        }
        System.out.print(fx);
        System.out.print(df.format(result));
        System.out.println();
    }
}