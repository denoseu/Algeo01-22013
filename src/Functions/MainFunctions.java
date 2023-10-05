package src.Functions;
import java.util.Scanner;

import src.Matrix.*;

// untuk di run di main
public class MainFunctions {
    /* SPL DENGAN ELIMINASI GAUSS */
    public static Scanner scan;
    public static void Gauss(double[][] matriks) {
        // double[][] matriks = matrixIO.readMatrixSPL();
        boolean found = false;

        for (int i = 0; i < matrixOP.getRow(matriks); i++) {
            // cari elemen pertama yang tidak nol di baris
            for (int j = 0; j < matrixOP.getCol(matriks); j++) {
                if (matriks[i][j] != 0) {
                    SPL.eselonbaris(matriks);
                }
                else {
                    int max = i;
                    for (int n = i+1; n < matrixOP.getRow(matriks); n++) { 
                        if (matriks[n][i] != 0) {
                            found = true;
                            max = n;
                            break;
                        }
                    }
                    if (found == true) {
                        matrixOP.tukar_baris(matriks, i, max);
                        matrixIO.displayMatrix(matriks);
                        SPL.eselonbaris(matriks);
                    } 
                }
            }
        }
        
        System.out.println("Matriks eselon baris:");
        matrixIO.displayMatrix(matriks);

        matrixOP.hasilSPLGauss(matriks);
    }

    /* SPL DENGAN ELIMINASI GAUSS-JORDAN */
    public static void GaussJordan(double[][] matriks) {
        SPL.GaussJ(matriks);

        System.out.println("Matriks eselon baris tereduksi: ");
        matrixIO.displayMatrix(matriks);

        matrixOP.hasilSPLGaussJordan(matriks);
    }

    /* SPL DENGAN MATRIKS BALIKAN */
    public static double[][] SPLBalikan(double[][] A, double[][] B) {

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
        // matrixIO.displayMatrix(I);

        // menghitung matriks augmented [A | I]
        double[][] augmentedMatrix = new double[matrixOP.getRow(A)][2 * matrixOP.getRow(A)];
        for (int i = 0; i < matrixOP.getRow(A); i++) {
            for (int j = 0; j < matrixOP.getRow(A); j++) {
                augmentedMatrix[i][j] = A[i][j];
                augmentedMatrix[i][j + matrixOP.getRow(A)] = I[i][j];
            }
        }
        matrixIO.displayMatrix(augmentedMatrix);

        // lakukan eliminasi Gauss-Jordan
        SPL.GaussJ(augmentedMatrix);

        // matrixIO.displayMatrix(augmentedMatrix);

        // mendapatkan matriks balikan dari matriks augmented yang sudah dilakukan operasi gauss-jordan
        double[][] AInverse = new double[matrixOP.getRow(A)][matrixOP.getRow(A)];
        for (int i = 0; i < matrixOP.getRow(A); i++) {
            for (int j = 0; j < matrixOP.getRow(A); j++) {
                AInverse[i][j] = augmentedMatrix[i][j + matrixOP.getRow(A)];
            }
        }

        // hasil matriks balikan = AInverse
        System.out.println("Matriks Balikan (A^(-1)): ");
        matrixIO.displayMatrix(AInverse);

        // mencari solusi SPL
        double[][] hasil = matrixOP.multiplyMatrixMatrix(AInverse, B);
        // matrixIO.displayMatrix(hasil);

        return hasil;
    }

    /* SPL DENGAN KAIDAH CRAMER */
    public static void Cramer(double[][] matriks) {

        if (SPL.kaidahCramer(matriks)[0][0] == -9999){
            System.out.println("Tidak dapat menggunakan kaidah Cramer.");
        }
        else if (SPL.kaidahCramer(matriks)[0][0] == -999){
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else if (SPL.kaidahCramer(matriks)[0][0] == -99999){
            System.out.println("Determinan matriks bernilai 0 sehingga tidak dapat menggunakan kaidah Cramer.");
        }
        else{
            System.out.println("Solusi:");
            for (int i = 0; i < SPL.kaidahCramer(matriks).length; i++) {
                System.out.printf("x%d = %.3f\n", i+1, SPL.kaidahCramer(matriks)[i][0]);
            }
        }
    }

    /* DETERMINAN DENGAN REDUKSI BARIS */
    public static void redBaris(double[][] matriks) {
        if (SPL.detReduksiBaris(matriks) == -9999){
            System.out.println("Matriks di atas tidak memiliki determinan.");
        }
        else{
            System.out.printf("Determinan: %.3f\n", SPL.detReduksiBaris(matriks));
        }
    }

    /* DETERMINAN DENGAN EKSPANSI KOFAKTOR */
    public static void ekspansi(double[][] matriks) {
        if (SPL.determinan(matriks) == -9999){
            System.out.println("Matriks di atas tidak memiliki determinan.");
        }
        else{
            System.out.printf("Determinan: %.3f\n", SPL.determinan(matriks));
        }
    }

    /* INVERSE MATRIKS */
    public static void inverseM(double[][] matriks) {
        if (SPL.noInv(SPL.inverse(matriks))){
            System.out.println("Matriks di atas tidak memiliki invers.");
        }
        else{
            matrixIO.displayMatrix(SPL.inverse(matriks));
        }
    }
    
    /* INTERPOLASI POLINOM */
    public static void InterpolasiKeyboard(double[] m, double[] x) {
        double result = Interpolasi.estimate(m, x);
        Interpolasi.hasilInterpolasi(m);
        Interpolasi.hasilEstimateInter(result,x);
    }

    public static void InterpolasiFile(double[][] matrix, double[] x) {
        double[] solution = Interpolasi.solutionInterpolasi(matrix);
        double result = Interpolasi.estimate(solution, x);
        Interpolasi.hasilInterpolasi(solution);
        Interpolasi.hasilEstimateInter(result, x);
    }

    /* INTERPOLASI BICUBIC SPLINE */
    public static void InterpolasiSpline(double[][] matrixInput) {
        scan = new Scanner(System.in);
        System.out.print("Masukkan x: "); double x = scan.nextDouble();
        System.out.print("Masukkan y: "); double y = scan.nextDouble();
        
        double solusi = bicubicInterpolation.bicubicSpline(matrixInput, x, y);
        System.out.println(solusi);
    }

}
