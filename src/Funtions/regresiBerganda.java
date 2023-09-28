package src.Funtions;
import java.util.Scanner;
import src.Matrix.*;


public class regresiBerganda {
    public static Scanner scan;
    public static double[][] inputX(){
        scan = new Scanner(System.in);
        System.out.println("Masukkan jumlah pengubah x: "); int n = scan.nextInt();
        System.out.println("Masukkan banyak sampel yang diinginkan: "); int m = scan.nextInt();
        double[][] matrix = new double[n][m];

        for (int i =0;i<n;i++){
            System.out.println("Masukkan sampel untuk x" + (i+1));
            for (int j=0;j<m;j++){
                matrix[i][j] = scan.nextDouble();
            }
        }
        return matrix;
    }

    public static double[][] inputY(double[][] m){
        scan = new Scanner(System.in);
        System.out.println("Masukkan nilai y sesuai dengan jumlah sampel: ");
        System.out.println("Jumlah sampel: "+ m[0].length);
        double[][] matrix = new double[m[0].length][1];

        for (int i =0;i<m[0].length;i++){
            System.out.println("Masukkan sampel untuk y" + (i+1));
            for (int j=0;j<1;j++){
                matrix[i][j] = scan.nextDouble();
            }
        }
        return matrix;
    }


    public static double[][] matrixReg(double[][] a, double [][]b){
        double[][] m = new double[a.length+1][a.length+2];
        for (int i =0; i<(a.length+1);i++){
            for (int j = 0;j<(a.length+2);j++){
                if (i==0){
                    if (j==0){
                        m[i][j] = a[0].length;
                        System.out.println("ELemen: " + a.length + " Posisi: " + i + " " + j);
                        System.out.println("-------------------------------------------------");
                    } else if (j == (a.length +1)){
                        m[i][j] = addY(b);
                        System.out.println("ELemen: " + addY(b) + " Posisi: " + i + " " + j);
                        System.out.println("-------------------------------------------------");
                    } else {
                        m[i][j] = addOneRow(a, j-1);
                        System.out.println("ELemen: " + addOneRow(a, j-1) + " Posisi: " + i + " " + j);
                        System.out.println("-------------------------------------------------");
                    }
                } else {
                    if (j==0){
                        m[i][j] = addOneRow(a, i-1);
                        System.out.println("ELemen: " + addOneRow(a, i-1) + " Posisi: " + i + " " + j);
                        System.out.println("-------------------------------------------------");

                    } else if (j == (a.length +1)){
                        m[i][j] = yOperation(a, b, i-1);
                        System.out.println("ELemen: " + yOperation(a, b, i-1) + " Posisi: " + i + " " + j);
                        System.out.println("-------------------------------------------------");
                    } else {
                        m[i][j] = multiplyEl(a, i-1, j-1);
                        System.out.println("ELemen: " + multiplyEl(a, i-1, j-1) + " Posisi: " + i + " " + j);
                        System.out.println("-------------------------------------------------");
                    }
                }
                matrixIO.displayMatrix(m);
                System.out.println("-------- NEXT -----------");
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
    public static double addOneRow(double[][] m, int row){
        double result = 0;
        System.out.println("----ADD ROW ----");
        for (int i = 0;i<m[0].length;i++){
            System.out.print(row + " " + i);
            result += m[row][i];
            System.out.println("Elemen yang ditambahkan: " + m[row][i]);
        }
        return result;
    }

    // Mendapatkan hasil kali pengubah x
    public static double multiplyEl(double[][] m, int rowSelector, int rowAcuan)
    {
        double result;
        result = 0;
        for (int i=0;i<m.length;i++){
            for (int j=0;j<m[0].length;j++){
                if (i==rowAcuan){
                    result += m[rowSelector][j] * m[i][j];
                    System.out.println("Pengkali: " + m[rowSelector][j] + " Yang dikali: " + m[i][j]);
                    System.out.println("Result: " + result);
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
        double [][] m = inputX();
        double [][] a = inputY(m);
        // for (int i =0;i<m.length;i++){
        //     for (int j=0;j<m[0].length;j++){
        //         m[i][j] = 5-i;
        //     }
        // }
        matrixIO.displayMatrix(m);
        matrixIO.displayMatrix(a);
        // System.out.println(yOperation(m, a, 1));
        matrixIO.displayMatrix(matrixReg(m, a));
    }
    
}
