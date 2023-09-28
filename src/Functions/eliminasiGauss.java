package src.Functions;
import src.Matrix.*;

public class eliminasiGauss {
    public static void keyboard () {
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

    public static void file () {
        double[][] matriks;
        matriks = matrixIO.fileToMatrix(2);
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
}