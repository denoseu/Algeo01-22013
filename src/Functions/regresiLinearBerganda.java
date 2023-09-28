package src.Functions;
import java.util.Scanner;
import src.Matrix.*;


public class regresiLinearBerganda {
    public static Scanner scan;
    public static double[][] inputReg(){
        scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah pengubah x: "); int n = scan.nextInt();
        System.out.print("Masukkan banyak sampel yang diinginkan: "); int m = scan.nextInt();
        double[][] matrix = new double[m][n+1];

        for (int i =0;i<m;i++){
            System.out.println("Masukkan titik untuk sampel " + (i+1) + " : ");
            for (int j=0;j<n+1;j++){
                if (j == n){
                    System.out.print("y"+ (i+1) + "= ");
                } else {
                    System.out.print("x" + (j+1) + "= ");
                }
                matrix[i][j] = scan.nextDouble();
            }
        }
        return matrix;
    }


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
    // Menjumlahan semua Y
    public static double addY(double[][]m){
        double result = 0;
        for (int i = 0;i<m.length;i++){
            result += m[i][0];
        }
        return result;
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

    // Menghasilkan perkalian dengan y 
    public static double yOperation (double[][] x, double[][]y, int rowSelector){
        double result;
        result = 0;
        for(int i = 0;i<x.length;i++){
            for (int j =0;j<x[0].length;j++){
                if ( i == rowSelector){
                    
                    result += x[rowSelector][j] * y[j][0];
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        double [][] m = inputReg();
        // double [][] a = inputY(m);
        // for (int i =0;i<m.length;i++){
        //     for (int j=0;j<m[0].length;j++){
        //         m[i][j] = 5-i;
        //     }
        // }
        // matrixIO.displayMatrix(m);
        // System.out.println(multiplyEl(m, 1, 0) );
        // matrixIO.displayMatrix(a);
        // System.out.println(yOperation(m, a, 1));
        matrixIO.displayMatrix(matrixReg(m));
    }
    
}
