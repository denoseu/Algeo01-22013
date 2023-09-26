public class gauss extends matrix {

    // cek apakah dia baris terakhirnya semuanya 0 kecuali solusi
    // intinya dia ga ad solusi
    public static boolean noSolusi (double[][] m) {
        // cari sampe N-1 elemen, ada yang bukan 0 ga
        for (int j = 0; j < getCol(m) - 1; j++) {
            if (m[getRow(m)-1][j] != 0) {
                return false;  // karena kalo ga 0 dia baik" saja
            }
        }
        // lalu kalo udah cek atas dia ngecek elemen terakhirnya
        // klo bukan 0 terus yang lainnya 0 berarti emang ga ada solusi

        if ((m[getRow(m)-1][getCol(m)-1] != 0)) {
            return true;
        }
        else {
            return false;
        }
    }

    // cek last barisnya 0 ato ga
    public static boolean Nol (double[][] m) {
        // cari sampe N elemen, ada yang bukan 0 ga
        for (int j = 0; j < getCol(m); j++) {
            if (m[getRow(m)-1][j] != 0) {
                return false;  // ada yang bukan 0, berarti dia ga full 0
            }
        }
        return true;
    }

    public static void eselonbaris(double[][] m) {
        int row = getRow(m);
        int col = getCol(m);

        for (int i = 0; i < row; i++) {
            // cari elemen pertama yang tidak nol di baris
            for (int j = 0; j < col; j++) {
                if (m[i][j] != 0) {
                    double bukannol = m[i][j];

                    // bagi baris oleh elemen yang bukan nol (biar dapet 1 utama)
                    for (int p = 0; p < col; p++) { // ato p < row+1?
                        m[i][p] /= bukannol; 
                    }
                    // System=.out.println(" ");
                    // displayMatrix(m);

                    // menolkan elemen dibawah 1 utama
                    for (int k = i + 1; k < row; k++) {
                        double faktor = m[k][j];
                        for (int l = 0; l < col; l++) {
                            m[k][l] -= faktor * m[i][l];
                        }
                    }
                    // System.out.println(" ");
                    // displayMatrix(m);
                    break;
                }
            }
        }
    }
    public static void main (String[] args) {
        double[][] matriks = matrixIO.readMatrixKeyboard();
        eselonbaris(matriks);

        // untuk fix kasus -0
        for(int i = 0; i < getRow(matriks); i++) {
            for(int j = 0; j < getCol(matriks); j++) {
                if (matriks[i][j] == -0) {
                    matriks[i][j] = 0;
                }
            }
        }
        System.out.println("Matriks eselon baris:");
        displayMatrix(matriks);

        double[] solusi = new double[getRow(matriks)];

        if (noSolusi(matriks)) {
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else if (Nol(matriks)) {
            System.out.println("Matriks memiliki banyak solusi.");
        } // persamaan parametriknya menyusul ya :)
        else {
            for (int m = getRow(matriks) - 1; m >= 0; m -= 1) {
                solusi[m] = matriks[m][getCol(matriks)-1];
                for (int n = 1; n <= getRow(matriks) - m - 1; n += 1) {
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
