public class gauss extends matrix {

    // manatau butuh
    // mengalikan baris dengan konstanta
    public static double[][] kali_baris (double[][] m, int row, int k) {
        for (int j = 0; j < getCol(m); j++) {
            m[row][j] = m[row][j] * k;
        }
        return m;
    }

    // swap/tukar baris
    public static double[][] tukar_baris (double[][] m, int row1, int row2) {
        for (int j = 0; j < getCol(m); j++) {
            double temp = m[row1][j];
            m[row1][j] = m[row2][j];
            m[row2][j] = temp;
        }
        return m;
    }

    // tambahin baris ke baris lain
    public static double[][] tambah_baris(double[][] m, int row_asal, int row_yangdiubah) {
        for (int j = 0; j < getCol(m); j++) {
            m[row_yangdiubah][j] += m[row_asal][j];
        }
        return m;
    }

    // kurangin baris
    public static double[][] kurang_baris(double[][] m, int row_asal, int row_yangdiubah) {
        for (int j = 0; j < getCol(m); j++) {
            m[row_yangdiubah][j] -= m[row_asal][j];
        }
        return m;
    }

    public static double[] eliminasiGauss(double[][] m) {
        int row = getRow(m);
        double[] hasil = new double[row];

        for (int baris = 0; baris < row; baris++) {
            // mau cari 1 utama dan nukar baris
            int baris1utama = baris;
            for (int i = baris + 1; i < row; i++) {
                if ((m[i][baris]) > (m[baris1utama][baris])) {
                    baris1utama = i;
                }
            }

            double[] temp = m[baris];
            m[baris] = m[baris1utama];
            m[baris1utama] = temp;

            // jadikan elemen diagonal menjadi 1
            double elemen1utama = m[baris][baris];
            for (int j = baris; j < row + 1; j++) {
                m[baris][j] /= elemen1utama;
            }

            // eliminasi baris lainnya
            for (int i = 0; i < row; i++) {
                if (i != baris) {
                    double faktor = m[i][baris];
                    for (int j = baris; j < row + 1; j++) {
                        m[i][j] -= faktor * m[baris][j];
                    }
                }
            }
        }

        // displayMatrix(m);

        for (int i = 0; i < row; i++) {
            hasil[i] = m[i][row];
        }
        
        return hasil;
    }

    public static void main(String[] args) {
        double[][] matriks = readMatrixKeyboard();
        double[] solusi = eliminasiGauss(matriks);

        System.out.println("Solusi:");
        for (int i = 0; i < solusi.length; i++) {
            System.out.printf("x%d = %.2f\n", i + 1, solusi[i]);
        }
    }
}

