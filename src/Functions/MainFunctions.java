package src.Functions;
import java.util.Scanner;

import src.Matrix.*;

// untuk di run di main
public class MainFunctions {
    /* SPL DENGAN ELIMINASI GAUSS */
    public static Scanner scan;
    public static void GaussKeyboard() {
        double[][] matriks = matrixIO.readMatrixSPL();
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

    public static void GaussFile() {
        double[][] matriks;
        String path = matrixIO.inputFile();
        matriks = matrixIO.fileToMatrix(path, 1);
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
    public static void GaussJordanKeyboard() {
        double[][] matriks = matrixIO.readMatrixSPL();
        SPL.GaussJ(matriks);

        System.out.println("Matriks eselon baris tereduksi: ");
        matrixIO.displayMatrix(matriks);

        matrixOP.hasilSPLGaussJordan(matriks);
    }

    public static void GaussJordanFile() {
        double[][] matriks;
        String path = matrixIO.inputFile();
        matriks = matrixIO.fileToMatrix(path, 1);
        SPL.GaussJ(matriks);

        System.out.println("Matriks eselon baris tereduksi: ");
        matrixIO.displayMatrix(matriks);

        matrixOP.hasilSPLGaussJordan(matriks);
    }

    /* SPL DENGAN MATRIKS BALIKAN */
    public static void BalikanKeyboard() {
        double[][] matriks = SPL.SPLBalikan();
        matrixIO.displayMatrix(SPL.inverse(matriks));
        matrixOP.hasilSPLInverse(matriks);
    }

    /* SPL DENGAN KAIDAH CRAMER */
    public static void CramerKeyboard() {
        double[][] m;
        m = matrixIO.readMatrixSPL();

        System.out.println("Solusi:");
        for (int i = 0; i < SPL.kaidahCramer(m).length; i++) {
            System.out.printf("x%d = %.3f\n", i+1, SPL.kaidahCramer(m)[i][0]);
        }
    }

    public static void CramerFile() {
        double[][] m;
        String path = matrixIO.inputFile();
        m = matrixIO.fileToMatrix(path, 1);

        System.out.println("Solusi:");
        for (int i = 0; i < SPL.kaidahCramer(m).length; i++) {
            System.out.printf("x%d = %.3f\n", i+1, SPL.kaidahCramer(m)[i][0]);
        }
    }

    /* DETERMINAN DENGAN REDUKSI BARIS */
    public static void redBarisKeyboard() {
        double[][] matriks = matrixIO.readMatrixKeyboard();

        System.out.printf("Determinan: %.3f\n", SPL.detReduksiBaris(matriks));
    }

    public static void redBarisfile() {
        double[][] matriks;
        String path = matrixIO.inputFile();
        matriks = matrixIO.fileToMatrix(path, 1);

        System.out.printf("Determinan: %.3f\n", SPL.detReduksiBaris(matriks));
    }

    /* DETERMINAN DENGAN EKSPANSI KOFAKTOR */
    public static void ekspansiKeyboard() {
        double[][] matriks = matrixIO.readMatrixKeyboard();

        System.out.printf("Determinan: %.3f\n", SPL.determinan(matriks));
    }

    public static void ekspansiFile() {
        double[][] matriks;
        String path = matrixIO.inputFile();
        matriks = matrixIO.fileToMatrix(path, 1);

        System.out.printf("Determinan: %.3f\n", SPL.determinan(matriks));
    }

    /* INVERSE MATRIKS */
    public static void inverseKeyboard () {
        double[][] m;
        m = matrixIO.readMatrixKeyboard();
        matrixIO.displayMatrix(SPL.inverse(m));
    }
    public static void inverseFile () {
        double[][] m;
        String path = matrixIO.inputFile();
        m = matrixIO.fileToMatrix(path, 1);
        matrixIO.displayMatrix(SPL.inverse(m));
    }
    
    /* INTERPOLASI POLINOM */
    public static void InterpolasiKeyboard() {
        double[] m = Interpolasi.solutionInterpolasi(Interpolasi.inputInterpolasi());
        double[] x = Interpolasi.inputTaksiran();
        double result = Interpolasi.estimate(m, x);
        Interpolasi.hasilInterpolasi(m);
        Interpolasi.hasilEstimateInter(result,x);
    }

    public static void InterpolasiFile() {
        String path = matrixIO.inputFile();
        double[][] matrix = matrixIO.fileToMatrix(path,2);
        double[] x = matrixIO.getTaksiran(path);
        double[] solution = Interpolasi.solutionInterpolasi(matrix);
        double result = Interpolasi.estimate(solution, x);
    }

    /* INTERPOLASI BICUBIC SPLINE */
    public static void InterpolasiSpline() {
        double[][] matrixInput;
        String path = matrixIO.inputFile();
        matrixInput = matrixIO.fileToMatrix(path, 1);
        scan = new Scanner(System.in);
        System.out.print("Masukkan x: "); double x = scan.nextDouble();
        System.out.print("Masukkan y: "); double y = scan.nextDouble();
        
        double solusi = bicubicInterpolation.bicubicSpline(matrixInput, x, y);
        System.out.println(solusi);
    }

    /* REGRESI LINEAR BERGANDA */
    public static void RegresiKeyboard() {
        double[][] m = regresiLinearBerganda.inputReg();
        double[] x = regresiLinearBerganda.inputTaksiran(m);
        double[] s = regresiLinearBerganda.solutionReg(m);
        double result = regresiLinearBerganda.estimateReg(s, x);
        regresiLinearBerganda.hasilRLB(s);
        regresiLinearBerganda.hasilEstimateRLB(result,x);
    }

    public static void RegresiFile() {
        String path = matrixIO.inputFile();
        double[][] m = matrixIO.fileToMatrix(path,2);
        double[] x = matrixIO.getTaksiran(path);
        double[] s = regresiLinearBerganda.solutionReg(m);
        double result = regresiLinearBerganda.estimateReg(s, x);
        regresiLinearBerganda.hasilRLB(s);
        regresiLinearBerganda.hasilEstimateRLB(result,x);
    }
}
