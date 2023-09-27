import src.Matrix.*;

import java.util.Scanner;

public class inverse {

    public static Scanner scan;

    public static double[][] getMatrixA() {
        scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris: "); int row = scan.nextInt();
        System.out.print("Masukkan jumlah kolom: "); int col = scan.nextInt();
        // bikin matrix uk. row x col (A)
        double[][] A = new double[row][col];
        // isi matrix
        System.out.println("Elemen matriks A: ");
        for(int i = 0;i < row;i++) {
            for(int j = 0;j < col;j++) {
                A[i][j] = scan.nextDouble();
            }
        }
        return A;
    }

    public static double[][] getMatrixB() {
        scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris: "); int row = scan.nextInt();
        // bikin matrix uk. row A x 1 col (B)
        double[][] B = new double[row][1];
        // isi matrix
        System.out.println("Elemen matriks B: ");
        for(int p = 0; p < row; p++) {
            for(int q = 0; q < 1; q++) {
                B[p][q] = scan.nextDouble();
            }
        }
        return B;
    }
    
    public static double[][] SPLBalikan () {
        // membaca matriks koefisien (A)
        double[][] A = getMatrixA();

        // membaca matriks hasil (B)
        double[][] B = getMatrixB();

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
        // matrix.displayMatrix(I);

        // menghitung matriks augmented [A | I]
        double[][] augmentedMatrix = new double[matrixOP.getRow(A)][2 * matrixOP.getRow(A)];
        for (int i = 0; i < matrixOP.getRow(A); i++) {
            for (int j = 0; j < matrixOP.getRow(A); j++) {
                augmentedMatrix[i][j] = A[i][j];
                augmentedMatrix[i][j + matrixOP.getRow(A)] = I[i][j];
            }
        }
        // matrix.displayMatrix(augmentedMatrix);

        // lakukan eliminasi Gauss-Jordan
        gaussjordan.eliminasiGauss(augmentedMatrix);

        // matrix.displayMatrix(augmentedMatrix);

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
        // matrix.displayMatrix(hasil);

        return hasil;
        // // simpan hasil dalam array biar bisa ditampilin
        // double[] solusi = new double[matrixOP.getRow(hasil)];

        // for (int x = matrixOP.getRow(hasil) - 1; x >= 0; x -= 1) {
        //     solusi[x] = hasil[x][0];
        // }

        // System.out.println("Solusi:");
        // for (int i = 0; i < solusi.length; i++) {
        //     System.out.printf("x%d = %.3f\n", i+1, solusi[i]);
        // }
    }

    public static void main (String[] args) {
        double[][] matriks = SPLBalikan();

        // simpan hasil dalam array biar bisa ditampilin
        double[] solusi = new double[matrixOP.getRow(matriks)];

        for (int x = matrixOP.getRow(matriks) - 1; x >= 0; x -= 1) {
            solusi[x] = matriks[x][0];
        }

        System.out.println("Solusi:");
        for (int i = 0; i < solusi.length; i++) {
            System.out.printf("x%d = %.3f\n", i+1, solusi[i]);
        }
    }
}
