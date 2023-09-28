package src.Functions;
import java.util.Scanner;

import src.Matrix.*;
public class Interpolasi {
    // Mencari hasil interpolasi
    public static double[] solutionInterpolasi(){
        double [][] m = inputInterpolasi();
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

        double[] solusi = matrixOP.getGaussMatrix(tempM);

        return solusi;    
    }

    // Mencari perkiraan titik interpolasi
    public static double estimate(double[] s, int x){
        double result = 0;
        for (int i =0; i<s.length;i++){
            result += s[i] * Math.pow(x, i);
        }
        return result;
    }

    // Membaca Matrix dari Keyboard untuk interpolasi
    public static double[][] inputInterpolasi(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan n: "); int n = scan.nextInt();
        System.out.println("Silakan masukan nilai (x,y):");
        double[][] m = new double[n][2];
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < 2;j++) {
                if (j == 0){
                    System.out.print("x" + (i) + ": ");
                } else {
                    System.out.print("y" + (i) + ": ");
                }
                m[i][j] = scan.nextDouble();
            }
        }
        scan.close();
        return m;
    }
    public static void main(String[] args){
        double[] m = solutionInterpolasi();
        for (int i =0;i<m.length;i++){
            System.out.println("x" + i + ": " + m[i]);
        }
        System.out.println(estimate(m, 9));

    }
}
