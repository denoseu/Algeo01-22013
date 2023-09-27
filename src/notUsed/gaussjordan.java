package src.notUsed;
import src.Matrix.*;

public class gaussjordan extends matrixOP {
    // udah bisa tapi kalo misalnya solusi banyak blm

    // eliminasi gauss-jordan
    public static double[] eliminasiGauss (double[][] m) {
        boolean noSolution = false;
        boolean manySolution = false;
        int row = getRow(m);
        int col = getCol(m);
        double[] hasil = new double[row]; // array buat nyimpen hasil

        for (int i = 0; i < row; i++) {
            // cari baris dengan elemen terbesar di kolom i
            int max = i;
            for (int j = i+1; j < row; j++) {
                if ((m[j][i]) > (m[max][i])) {
                    max = j;
                }
            }

            // System.out.println("cari baris dengan elemen terbesar di kolom i");
            // displayMatrix(m);

            // tukar baris i dengan baris max tadi
            matrixOP.tukar_baris(m, i, max);

            // System.out.println("tukar baris i dengan baris dengan elemen terbesar");
            // displayMatrix(m);

            if (matrixOP.noSolusi(m)) {
                noSolution = true;
                break;
            }
            else if (matrixOP.Nol(m)) {
                manySolution = true;
                break; // persamaan parametriknya menyusul ya :)
            }

            // jadikan elemen diagonal menjadi 1 (mo bikin 1 utama)
            double pembagi = m[i][i];
            for (int p = i; p < col; p++) {
                m[i][p] /= pembagi;
            }

            // System.out.println("jadikan elemen diagonal menjadi 1 (mo bikin 1 utama)");
            // displayMatrix(m);

            // eliminasi baris lainnya
            for (int q = 0; q < row; q++) {
                if (q != i) {
                    double faktor = m[q][i];
                    for (int r = i; r < col; r++) { //row+1
                        m[q][r] -= faktor * m[i][r]; // kurangin baris sm yg diatas
                    }
                }
            }
            
            // System.out.println("eliminasi baris lain");
            // displayMatrix(m);
        }

        // System.out.println("matriks final");
        // displayMatrix(m);
        
        if (noSolution == true) {
            hasil[0] = -999;
        }
        else if (manySolution == true) {
            hasil[0] = -2000;
        }
        else {
            for (int i = 0; i < row; i++) {
            hasil[i] = Math.round(m[i][row]); // isi hasil
            }
        
        }
        return hasil;
    }

    public static void main (String[] args) {
        double[][] matriks = matrixIO.readMatrixSPL();
        double[] solusi = eliminasiGauss(matriks);

        if (solusi[0] == -999) {
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else if (solusi[0] == -2000) {
            System.out.println("Matriks memiliki banyak solusi."); // persamaan parametrik menyusul
        }
        else {
            System.out.println("Solusi:");
            for (int i = 0; i < solusi.length; i++) {
                System.out.printf("x%d = %.3f\n", i+1, solusi[i]);
            }

        }
    }
}

