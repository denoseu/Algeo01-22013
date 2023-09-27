import src.Matrix.*;
import java.util.Scanner;

public class tesMain {
    public static void main (String[] args) {
        double[][] matriks = matrixIO.readMatrixSPL();
        boolean found = false;

        for (int i = 0; i < matrixOP.getRow(matriks); i++) {
            // cari elemen pertama yang tidak nol di baris
            for (int j = 0; j < matrixOP.getCol(matriks); j++) {
                if (matriks[i][j] != 0) {
                    gauss.eselonbaris(matriks);
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
                        eselonbaris(matriks);
                    } 
                }
            }
        }
        
        // untuk fix kasus -0
        for(int i = 0; i < matrixOP.getRow(matriks); i++) {
            for(int j = 0; j < matrixOP.getCol(matriks); j++) {
                if (matriks[i][j] == -0) {
                    matriks[i][j] = 0;
                }
            }
        }
        System.out.println("Matriks eselon baris:");
        matrixIO.displayMatrix(matriks);

        double[] solusi = new double[matrixOP.getRow(matriks)];

        if (noSolusi(matriks)) {
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else if (Nol(matriks)) {
            System.out.println("Matriks memiliki banyak solusi.");
        } // persamaan parametriknya menyusul ya :)
        else {
            for (int m = matrixOP.getRow(matriks) - 1; m >= 0; m -= 1) {
                solusi[m] = matriks[m][matrixOP.getCol(matriks)-1];
                for (int n = 1; n <= matrixOP.getRow(matriks) - m - 1; n += 1) {
                    solusi[m] = solusi[m] - matriks[m][m + n] * solusi[m + n];
                } 
            }

            System.out.println("Solusi:");
            for (int i = 0; i < solusi.length; i++) {
                System.out.printf("x%d = %.3f\n", i+1, solusi[i]);
            }
            
        }
    }
}