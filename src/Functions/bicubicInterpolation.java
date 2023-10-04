package src.Functions;
import src.Matrix.*;

import java.util.Scanner;

public class bicubicInterpolation {
    public static Scanner scan;

    public static double[][] createY(double[][] m) {
        //Membuat matrix y berukuran 16x1 yang merupakan resize dari matrix input 4x4
        double[][] newM = new double[16][1];

        int idx = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newM[idx][0] = m[i][j];
                idx++;
            }
        }
        return newM;
    }

    public static double koefFungsi(int x, int y, int i, int j){
        return Math.pow(x, i) * Math.pow(y, j);
    }
    public static double koefTurunanX(int x, int y, int i, int j){
        if (i == 0 && x == 0){
            return 0;
        }
        else{
            return i * Math.pow(x, i-1) * Math.pow(y, j);
        }
    }
    public static double koefTurunanY(int x, int y, int i, int j){
        if (j == 0 && y == 0){
            return 0;
        }
        else{
            return j * Math.pow(x, i) * Math.pow(y, j-1);
        }
    }
    public static double koefTurunanXY(int x, int y, int i, int j){
        if ((i == 0 && x == 0) || j == 0 && y == 0){
            return 0;
        }
        else{
            return i * j * Math.pow(x, i-1) * Math.pow(y, j-1);
        }
    }
    public static double[][] createX(){
        //Membuat matrix X berukuran 16x16 berisi koefisien aij dari ekspansi sigma tiap fungsi
        double[][] X = new double[16][16];

        int i,j,k,x,y;
        int rowX = 0;
        for (k = 1; k <= 4; k++){
            for (y = 0; y <=1 ; y++){
                for (x = 0; x <=1 ; x++){
                    
                    int colX = 0;
                    for (j = 0; j < 4; j++){
                        for (i = 0; i < 4; i++){
                            
                            switch (k){
                                case 1:
                                    X[rowX][colX] = koefFungsi(x, y, i, j);
                                    break;
                                case 2:
                                    X[rowX][colX] = koefTurunanX(x, y, i, j);
                                    break;
                                case 3:
                                    X[rowX][colX] = koefTurunanY(x, y, i, j);
                                    break;
                                case 4:
                                    X[rowX][colX] = koefTurunanXY(x, y, i, j);
                                    break;
                            }
                            colX++;
                        }
                    }
                    rowX ++;
                }
            }
        }
        return X;
    }

    public static double[][] inverseX (double[][] A ) {
        // membuat matriks identitas (I)
        double[][] I = new double[matrixOP.getRow(A)][matrixOP.getRow(A)];
        for (int i = 0; i < matrixOP.getRow(A); i++) {
            for (int j = 0; j < matrixOP.getRow(A); j++) {
                if (i == j) {
                    I[i][j] = 1.0;
                } else {
                    I[i][j] = 0.0;
                }
            }
        }

        // menghitung matriks augmented [A | I]
        double[][] augmentedMatrix = new double[matrixOP.getRow(A)][2 * matrixOP.getRow(A)];
        for (int i = 0; i < matrixOP.getRow(A); i++) {
            for (int j = 0; j < matrixOP.getRow(A); j++) {
                augmentedMatrix[i][j] = A[i][j];
                augmentedMatrix[i][j + matrixOP.getRow(A)] = I[i][j];
            }
        }

        // lakukan eliminasi Gauss-Jordan
        SPL.GaussJ(augmentedMatrix);

        // mendapatkan matriks balikan dari matriks augmented yang sudah dilakukan operasi gauss-jordan
        double[][] AInverse = new double[matrixOP.getRow(A)][matrixOP.getRow(A)];
        for (int i = 0; i < matrixOP.getRow(A); i++) {
            for (int j = 0; j < matrixOP.getRow(A); j++) {
                AInverse[i][j] = augmentedMatrix[i][j + matrixOP.getRow(A)];
            }
        }
        return AInverse;
    }

    public static double bicubicSpline (double[][] m, double x, double y) {
        // x = matrixIO.getTaksiran(path);
        double[][] matrixX = createX();
        double[][] matrixY = createY(m);
        double[][] matrixa = matrixOP.multiplyMatrixMatrix(inverseX(matrixX), matrixY);

        double hasil = 0;
        int i, j;
        int row = 0;
        
        //Membaca input matrix 4x4
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                hasil += Math.pow(x, i) * Math.pow(y, j) * matrixa[row][0];
                row++;
            }
        }

        return hasil;
    }

    // public static void main(String[] args){
    //     // double[][] matrixInput = matrixIO.readMatrixKeyboard();
    //     double[][] matrixInput;
    //     String path = matrixIO.inputFile();
    //     matrixInput = matrixIO.fileToMatrix(path, 1);
    //     scan = new Scanner(System.in);
    //     System.out.print("Masukkan x: "); double x = scan.nextDouble();
    //     System.out.print("Masukkan y: "); double y = scan.nextDouble();
    // public static void main(String[] args){
    //     // double[][] matrixInput = matrixIO.readMatrixKeyboard();
    //     double[][] matrixInput;
    //     String path = matrixIO.inputFile();
    //     matrixInput = matrixIO.fileToMatrix(path, 2);
    //     double[] taksiran = matrixIO.getTaksiran(path);
    //     scan = new Scanner(System.in);
    //     // System.out.print("Masukkan x: "); double x = scan.nextDouble();
    //     // System.out.print("Masukkan y: "); double y = scan.nextDouble();
    //     double x = taksiran[0];
    //     double y =taksiran[1];
        
    //     double solusi = bicubicSpline(matrixInput, x, y);
    //     System.out.println(solusi);
    // }
}
