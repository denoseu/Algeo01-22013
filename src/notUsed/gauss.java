package src.notUsed;
import src.Matrix.*;
import java.util.Scanner;
// import src.Matrix.matrixOP.getRow;

public class gauss {
    public static Scanner scan;

    public static void eselonbaris(double[][] m) {
        int row = matrixOP.getRow(m);
        int col = matrixOP.getCol(m);
        // boolean found = false;
        for (int i = 0; i < row; i++) {
            // cari elemen pertama yang tidak nol di baris
            for (int j = 0; j < col; j++) {
                if (m[i][j] != 0) {
                    double bukannol = m[i][j];

                    // bagi baris oleh elemen yang bukan nol (biar dapet 1 utama)
                    for (int p = 0; p < col; p++) { // ato p < row+1?
                        m[i][p] /= bukannol; 
                    }
                    // System.out.println(" ");
                    // matrixIO.displayMatrix(m);

                    // menolkan elemen dibawah 1 utama
                    for (int k = i + 1; k < row; k++) {
                        double faktor = m[k][j];
                        for (int l = 0; l < col; l++) {
                            m[k][l] -= faktor * m[i][l];
                        }
                    }
                    // System.out.println(" ");
                    // matrixIO.displayMatrix(m);

                    // untuk fix kasus -0
                    for(int a = 0; a < matrixOP.getRow(m); a++) {
                        for(int b = 0; b < matrixOP.getCol(m); b++) {
                            if (m[a][b] == -0) {
                                m[a][b] = 0;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
    public static void main (String[] args) {
        double[][] matriks = matrixIO.readMatrixSPL();
        boolean found = false;

        for (int i = 0; i < matrixOP.getRow(matriks); i++) {
            // cari elemen pertama yang tidak nol di baris
            for (int j = 0; j < matrixOP.getCol(matriks); j++) {
                if (matriks[i][j] != 0) {
                    eselonbaris(matriks);
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
        
        System.out.println("Matriks eselon baris:");
        matrixIO.displayMatrix(matriks);

        solusi.hasilSPLGauss(matriks);
    }

}