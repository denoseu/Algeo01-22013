package src.Funtions;
import java.util.Scanner;

import src.Matrix.*;
public class Interpolasi {
    public static double[][] interpolasi(){
        double [][] m = readMatrixInterpolasi();
        double [][] tempM = new double[m.length][m.length+1];
        // double result = 0;
        // masukan n, (x0,y0)....
        // Mengubah masukan menjadi matrix
        for (int i = 0;i< m.length;i++){
            for(int j = 0; j < m.length + 1 ; j++){
                if (j == m.length){
                    tempM[i][j] = m[i][1];
                } else {
                    tempM[i][j] = Math.pow(m[i][0], j);
                }
            }
        }



        
        return tempM;

        // blom jadi
        // harusnya nanti di gauss trus dpt hasil a nya
        // habis itu x nya di power in trus dikali ke a tambahin ke result
    
    }
    // Membaca Matrix dari Keyboard untuk interpolasi
    public static double[][] readMatrixInterpolasi(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukkan n: "); int n = scan.nextInt();
        System.out.println("Silakan masukan nilai (x,y):");
        double[][] m = new double[n][2];
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < 2;j++) {
                m[i][j] = scan.nextDouble();
            }
        }
        scan.close();
        return m;
    }
    public static void main(String[] args){
        double[][] m = interpolasi();
        matrixIO.displayMatrix(m);
    }
}
