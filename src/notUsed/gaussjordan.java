package src.notUsed;
import src.Matrix.*;

public class gaussjordan {

    public static void GaussJ(double[][] m) {

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
        GaussJ(matriks);

        System.out.println("Matriks eselon baris tereduksi: ");
        matrixIO.displayMatrix(matriks);

        solusi.hasilSPLGauss(matriks);
    }

}