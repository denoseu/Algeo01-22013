package src.notUsed;
import src.Matrix.*;

public class gaussjordan {

    public static void eliminasiGauss(double[][] m) {

        // gauss-in dulu biar dapet matriks eselon baris
        // nanti dilanjutin supaya dapet eselon baris tereduksi
        gauss.eselonbaris(m);
        int row = matrixOP.getRow(m);
        int col = matrixOP.getCol(m);

        for (int p = 0; p < col; p++) {
            // cari indeks baris leading one pada kolom yang lagi di cek (j)
            int satu = matrixOP.satuUtama(m, row, p);
            for (int q = 0; q < satu; q++) {
                if (m[q][p] != 0) {
                    double faktor = m[q][p];
                    for (int r = q; r < col; r++) {
                        m[q][r] -= faktor * m[satu][r];
                        // System.out.println(" faktor ");
                        // matrixIO.displayMatrix(m);
                        // handle nilai -0
                        if (m[q][r] == -0) {
                            m[q][r] = 0;
                        }
                    }
                } 
                // handle nilai -0
                else if (m[q][p] == -0) {
                    m[q][p] = 0;
                }
            }
        }

        // untuk fix kasus -0
        for(int a = 0; a < matrixOP.getRow(m); a++) {
            for(int b = 0; b < matrixOP.getCol(m); b++) {
                if (m[a][b] == -0) {
                    m[a][b] = 0;
                }
            }
        }
    }

    public static void main (String[] args) {
        double[][] matriks = matrixIO.readMatrixSPL();
        eliminasiGauss(matriks);

        System.out.println("Matriks eselon baris tereduksi: ");
        matrixIO.displayMatrix(matriks);

        double[] solusi = new double[matrixOP.getRow(matriks)];

        if (matrixOP.noSolusi(matriks)) {
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else if (matrixOP.Nol(matriks)) {
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