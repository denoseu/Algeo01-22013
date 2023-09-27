package src.Matrix;

public class gaussjordan {
    // udah bisa tapi kalo misalnya solusi banyak blm

    // manatau butuh
    // mengalikan baris dengan konstanta
    public static double[][] kali_baris (double[][] m, int row, int k) {
        for (int j = 0; j < matrixOP.getCol(m); j++) {
            m[row][j] = m[row][j] * k;
        }
        return m;
    }

    // swap/tukar baris
    public static double[][] tukar_baris (double[][] m, int row1, int row2) {
        for (int j = 0; j < matrixOP.getCol(m); j++) {
            double temp = m[row1][j];
            m[row1][j] = m[row2][j];
            m[row2][j] = temp;
        }
        return m;
    }

    // tambahin baris ke baris lain
    public static double[][] tambah_baris(double[][] m, int row_asal, int row_yangdiubah) {
        for (int j = 0; j < matrixOP.getCol(m); j++) {
            m[row_yangdiubah][j] += m[row_asal][j];
        }
        return m;
    }

    // kurangin baris
    public static double[][] kurang_baris(double[][] m, int row_asal, int row_yangdiubah) {
        for (int j = 0; j < matrixOP.getCol(m); j++) {
            m[row_yangdiubah][j] -= m[row_asal][j];
        }
        return m;
    }

    // eliminasi gauss-jordan
    public static double[][] eliminasiGauss (double[][] m) {
        boolean noSolution = false;
        boolean manySolution = false;
        int row = matrixOP.getRow(m);
        int col = matrixOP.getCol(m);
        // double[] hasil = new double[row]; // array buat nyimpen hasil

        for (int i = 0; i < row; i++) {
            // cari baris dengan elemen terbesar di kolom i
            int max = i;
            for (int j = i+1; j < row; j++) {
                if ((m[j][i]) > (m[max][i])) {
                    max = j;
                }
            }

            System.out.println("cari baris dengan elemen terbesar di kolom i");
            matrixIO.displayMatrix(m);

            // tukar baris i dengan baris max tadi
            tukar_baris(m, i, max);

            System.out.println("tukar baris i dengan baris dengan elemen terbesar");
            matrixIO.displayMatrix(m);

            // jadikan elemen diagonal menjadi 1 (mo bikin 1 utama)
            double pembagi = m[i][i];
            for (int p = i; p < col; p++) {
                m[i][p] /= pembagi;
            }

            System.out.println("jadikan elemen diagonal menjadi 1 (mo bikin 1 utama)");
            matrixIO.displayMatrix(m);

            // eliminasi baris lainnya
            for (int q = 0; q < row; q++) {
                if (q != i) {
                    double faktor = m[q][i];
                    for (int r = i; r < col; r++) {
                        m[q][r] -= faktor * m[i][r]; // kurangin baris sm yg diatas
                    }
                }
            }

            if (gauss.noSolusi(m)) {
                noSolution = true;
                break;
            }
            else if (gauss.Nol(m)) {
                manySolution = true;
                break; // persamaan parametriknya menyusul ya :)
            }
            
            System.out.println("eliminasi baris lain");
            matrixIO.displayMatrix(m);
        }

        System.out.println("Matriks setelah OBE");
        matrixIO.displayMatrix(m);

        if (noSolution == true) {
            m[0][0] = -999; // anggap idx undef ajalah :)
        }
        else if (manySolution == true) {
            m[0][0] = -2000; 
        }
        
        
        // else {
        //     for (int i = 0; i < row; i++) {
        //     hasil[i] = Math.round(m[i][row]); // isi hasil
        //     }
        
        // }
        return m;
    }

    public static void main (String[] args) {
        double[][] matriks = matrixIO.readMatrixKeyboard();
        double[][] solusi = eliminasiGauss(matriks);
        double[] hasil = new double[matrixOP.getRow(matriks)];

        if (gauss.noSolusi(solusi)) {
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else if (gauss.Nol(solusi)) {
            System.out.println("Matriks memiliki banyak solusi.");
        } // persamaan parametriknya menyusul ya :)
        else {
            for (int m = matrixOP.getRow(solusi) - 1; m >= 0; m -= 1) {
                hasil[m] = Math.round(solusi[m][matrixOP.getCol(solusi)-1]);
                for (int n = 1; n <= matrixOP.getRow(solusi) - m - 1; n += 1) {
                    hasil[m] = Math.round(hasil[m] - solusi[m][m + n] * hasil[m + n]);
                } 
    
            }

            System.out.println("Solusi:");
            for (int i = 0; i < hasil.length; i++) {
                System.out.printf("x%d = %.3f\n", i+1, hasil[i]);
            }
            
        }

        // if (solusi[0] == -999) {
        //     System.out.println("Matriks tidak memiliki solusi.");
        // }
        // else if (solusi[0] == -2000) {
        //     System.out.println("Matriks memiliki banyak solusi."); // persamaan parametrik menyusul
        // }
        // else {
        //     System.out.println("Solusi:");
        //     for (int i = 0; i < solusi.length; i++) {
        //         System.out.printf("x%d = %.3f\n", i+1, solusi[i]);
        //     }

        // }
    }
}
