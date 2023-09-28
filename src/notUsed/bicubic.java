package src.notUsed;
import java.util.Scanner;

import src.Matrix.*;

public class bicubic {
    public static Scanner scan;
    // resize matrix 4x4 menjadi 16x1 untuk membuat y
    // dari input matrix 4x4
    public static double[][] resizeMatrix4x4(double[][] m) {
        double[][] newM = new double[16][1];

        int x = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newM[x][x%1] = m[i][j];
                x++;
            }
        }
        return newM;
    }

    public static double[][] matriksX() {
        double[][] m = new double[16][16];
        int i, j;
        int row = 0;
        int col = 0;
        double x, y;

        while (row < 16) {
            if (row % 4 == 0) {
                for (x = -1; x < 3; x++) {
                    for (y = -1; y < 3; y++) {
                        col = 0;
                        for (i = 0; i < 4; i++) {
                            for (j = 0; j < 4; j++) {
                                m[row][col] = Math.pow(x, i) * Math.pow(y, j);
                                col++;
                            }
                        }
                        row++;
                    }
                }
            }

            else if (row % 4 == 1) {
                for (x = -1; x < 3; x++) {
                    for (y = -1; y < 3; y++) {
                        col = 0;
                        for (j = 0; j < 4; j++) {
                            for (i = 1; i < 4; i++) {
                                m[row][col] = Math.pow(x, i-1) * Math.pow(y, j);
                                col++;
                            }
                        }
                        row++;
                    }
                }
            }

            else if (row % 4 == 2) {
                for (x = -1; x < 3; x++) {
                    for (y = -1; y < 3; y++) {
                        col = 0;
                        for (j = 1; j < 4; j++) {
                            for (i = 0; i < 4; j=i++) {
                                m[row][col] = Math.pow(x, i) * Math.pow(y, j-1);
                                col++;
                            }
                        }
                        row++;
                    }
                }
            }

            else {
                for (x = -1; x < 3; x++) {
                    for (y = -1; y < 3; y++) {
                        col = 0;
                        for (j = 0; j < 4; j++) {
                            for (i = 0; i < 4; i++) {
                                m[row][col] = Math.pow(x, i-1) * Math.pow(y, j-1);
                                col++;
                            }
                        }
                        row++;
                    }
                }
            }
        }
        return m;
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
        // matrixIO.displayMatrix(I);

        // menghitung matriks augmented [A | I]
        double[][] augmentedMatrix = new double[matrixOP.getRow(A)][2 * matrixOP.getRow(A)];
        for (int i = 0; i < matrixOP.getRow(A); i++) {
            for (int j = 0; j < matrixOP.getRow(A); j++) {
                augmentedMatrix[i][j] = A[i][j];
                augmentedMatrix[i][j + matrixOP.getRow(A)] = I[i][j];
            }
        }
        // matrixIO.displayMatrix(augmentedMatrix);

        // lakukan eliminasi Gauss-Jordan
        gaussjordan.GaussJ(augmentedMatrix);

        // matrixIO.displayMatrix(augmentedMatrix);

        // mendapatkan matriks balikan dari matriks augmented yang sudah dilakukan operasi gauss-jordan
        double[][] AInverse = new double[matrixOP.getRow(A)][matrixOP.getRow(A)];
        for (int i = 0; i < matrixOP.getRow(A); i++) {
            for (int j = 0; j < matrixOP.getRow(A); j++) {
                AInverse[i][j] = augmentedMatrix[i][j + matrixOP.getRow(A)];
            }
        }

        // hasil matriks balikan = AInverse
        // System.out.println("Matriks Balikan (A^(-1)): ");
        // matrixIO.displayMatrix(AInverse);

        return AInverse;
    }

    public static double spline (double[][] m, double x, double y) {
        double[][] xMatrix = matriksX();
        double[][] yMatrix = matrixOP.multiplyMatrixMatrix(inverseX(xMatrix), resizeMatrix4x4(m));
        double hasil = 0;
        int i, j;
        int row = 0;
        
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                hasil += Math.pow(x, i) * Math.pow(y, j) * yMatrix[row][0];
                row++;
            }
        }
        return hasil;
    }

    public static void main (String[] args) {
        double[][] m = matrixIO.readMatrixKeyboard();
        scan = new Scanner(System.in);
        System.out.print("Masukkan x: "); double x = scan.nextDouble();
        System.out.print("Masukkan y: "); double y = scan.nextDouble();
        double solusi = spline(m, x, y);
        System.out.println(solusi);

        // double[][] newM = resizeMatrix4x4(m);
        // matrixIO.displayMatrix(newM);
        // System.out.println("");
        // double[][] bicubic = matriksX();
        // // matrixIO.displayMatrix(bicubic);
        // // System.out.println("");
        // double[][] kali = inverseX(bicubic);

        // double[][] baru = matrixOP.multiplyMatrixMatrix(kali, newM);
        // matrixIO.displayMatrix(baru);
    }
}

