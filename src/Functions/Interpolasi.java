package src.Functions;
import java.text.DecimalFormat;
import java.util.Scanner;

import src.Matrix.*;
public class Interpolasi {
    // Mencari hasil interpolasi
    public static Scanner scan;
    public static double[] solutionInterpolasi(double[][] m){
        double [][] tempM = new double[m.length][m.length+1];
        
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
    public static double estimate(double[] s, double x){
        double result = 0;
        for (int i =0; i<s.length;i++){
            result += s[i] * Math.pow(x, i);
        }
        return result;
    }

    // Membaca Matrix dari Keyboard untuk interpolasi
    public static double[][] inputInterpolasi(){
        scan = new Scanner(System.in);
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
        return m;
    }
    public static double inputTaksiran(){
        scan = new Scanner(System.in);
        System.out.print("Masukkan nilai yang ditaksir: "); double n = scan.nextDouble();
        return n;

    }

    public static void hasilInterpolasi(double[] s){
        System.out.println("Hasil interpolasi: ");
        System.out.print("f(x)= ");
        DecimalFormat df = new DecimalFormat("0.000");
        for (int i = s.length-1 ; i>=0;i--){
            if (i == s.length-1){
                if (s[i] == 0){
                    System.out.print("");
                }else {

                    System.out.print(df.format(s[i]));
                    System.out.print("x^" + i);
                }
            } else if (i == 0) {
                if (s[i] < 0){
                    System.out.print(" - " + df.format(Math.abs(s[i])));
                } else if (s[i] == 0){
                    System.out.print("");
                } else {
                    System.out.print(" + " + df.format(s[i]));
                }
            } else if (i == 1) {
                if (s[i] < 0){
                    System.out.print(" - " + df.format(Math.abs(s[i])));
                    System.out.print("x");
                } else if (s[i] == 0){
                    System.out.print("");
                } else {

                    System.out.print(" + " + df.format(s[i]));
                    System.out.print("x");
                }
            } else {
                
                if (s[i] < 0){
                    System.out.print(" - " + df.format(Math.abs(s[i])));
                    System.out.print("x^" + i);
                } else if (s[i] == 0){
                    System.out.print("");
                } else {

                    System.out.print(" + " + df.format(s[i]));
                    System.out.print("x^" + i);
                }
            }
        }
        System.out.println();

    }
    
    public static void hasilEstimateInter(double x, double taksiran){
        DecimalFormat df = new DecimalFormat("0.000");
        System.out.println("Hasil taksiran: ");
        System.out.print("f(" + df.format(taksiran) + ")= ");
        System.out.print(df.format(x));
        System.out.println();
    }
    public static void main(String[] args){
        double[] m = solutionInterpolasi(inputInterpolasi());
        double x = inputTaksiran();
        double result = estimate(m, x);
        hasilInterpolasi(m);
        hasilEstimateInter(result,x);

    }
}
