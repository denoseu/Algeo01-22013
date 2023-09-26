import java.util.Scanner;

public class inverse {

    public static Scanner scan;
    public static void main(String[] args) {
        scan = new Scanner(System.in);
        System.out.print("Masukkan n (matriks persegi): "); int n = scan.nextInt();
        // membaca matriks koefisien (A)
        double[][] A = new double[n][n];
        System.out.println("Masukkan elemen matriks (A):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scan.nextDouble();
            }
        }

        // membaca matriks hasil (B)
        double[][] B = new double[matrix.getRow(A)][1];
        System.out.println("Elemen matriks B: ");
        for(int p = 0; p < matrix.getRow(A); p++) {
            for(int q = 0; q < 1; q++) {
                B[p][q] = scan.nextDouble();
            }
        }

        // membuat matriks identitas (I)
        double[][] I = new double[n][n];
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    I[i][j] = 1.0;
                } else {
                    I[i][j] = 0.0;
                }
            }
        }
        matrix.displayMatrix(I);

        // menghitung matriks augmented [A | I]
        double[][] augmentedMatrix = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = A[i][j];
                augmentedMatrix[i][j + n] = I[i][j];
            }
        }
        matrix.displayMatrix(augmentedMatrix);

        // lakukan eliminasi Gauss-Jordan
        gaussjordan.eliminasiGauss(augmentedMatrix);

        matrix.displayMatrix(augmentedMatrix);

        // mendapatkan matriks balikan dari matriks augmented yang sudah dilakukan operasi gauss-jordan
        double[][] AInverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AInverse[i][j] = augmentedMatrix[i][j + n];
            }
        }

        // hasil matriks balikan = AInverse
        System.out.println("Matriks Balikan (A^(-1)): ");
        matrix.displayMatrix(AInverse);

        // mencari solusi SPL
        double[][] hasil = matrix.multiplyMatrixMatrix(AInverse, B);
        // matrix.displayMatrix(hasil);

        // simpan hasil dalam array biar bisa ditampilin
        double[] solusi = new double[matrix.getRow(hasil)];

        for (int m = matrix.getRow(hasil) - 1; m >= 0; m -= 1) {
            solusi[m] = hasil[m][0];
        }

        System.out.println("Solusi:");
        for (int i = 0; i < solusi.length; i++) {
            System.out.printf("x%d = %.3f\n", i+1, solusi[i]);
        }
            
    }
}
