package src.Funtions;
import java.util.Scanner;

import src.Matrix.*;

public class SPL {
    /*-------------- GAUSS ------------------ */
    public static boolean noSolusi (double[][] m) {
        // cari sampe N-1 elemen, ada yang bukan 0 ga
        for (int j = 0; j < matrixOP.getCol(m) - 1; j++) {
            if (m[matrixOP.getRow(m)-1][j] != 0) {
                return false;  // karena kalo ga 0 dia baik" saja
            }
        }
        // lalu kalo udah cek atas dia ngecek elemen terakhirnya
        // klo bukan 0 terus yang lainnya 0 berarti emang ga ada solusi

        if ((m[matrixOP.getRow(m)-1][matrixOP.getCol(m)-1] != 0)) {
            return true;
        }
        else {
            return false;
        }
    }

    // cek last barisnya 0 ato ga
    public static boolean Nol (double[][] m) {
        // cari sampe N elemen, ada yang bukan 0 ga
        for (int j = 0; j < matrixOP.getCol(m); j++) {
            if (m[matrixOP.getRow(m)-1][j] != 0) {
                return false;  // ada yang bukan 0, berarti dia ga full 0
            }
        }
        return true;
    }

    public static void eselonbaris(double[][] m) {
        int row = matrixOP.getRow(m);
        int col = matrixOP.getCol(m);

        for (int i = 0; i < row; i++) {
            // cari elemen pertama yang tidak nol di baris
            for (int j = 0; j < col; j++) {
                if (m[i][j] != 0) {
                    double bukannol = m[i][j];

                    // bagi baris oleh elemen yang bukan nol (biar dapet 1 utama)
                    for (int p = 0; p < row+1; p++) {
                        m[i][p] /= bukannol; 
                    }
                    // System.out.println(" ");
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

    /*-------------- GAUSS JORDAN ------------------ */
    // Mengalikan baris dengan konstanta
    public static double[][] kali_baris (double[][] m, int row, int k) {
        for (int j = 0; j < matrixOP.getCol(m); j++) {
            m[row][j] = m[row][j] * k;
        }
        return m;
    }

    // Swap/tukar baris
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
    public static double[] eliminasiGauss (double[][] m) {
        boolean noSolution = false;
        boolean manySolution = false;
        int row = matrixOP.getRow(m);
        double[] hasil = new double[row]; // array buat nyimpen hasil

        for (int i = 0; i < row; i++) {
            // Cari baris dengan elemen terbesar di kolom i
            int max = i;
            for (int j = i+1; j < row; j++) {
                if ((m[j][i]) > (m[max][i])) {
                    max = j;
                }
            }

            // Tukar baris i dengan baris max tadi
            tukar_baris(m, i, max);

            if (noSolusi(m)) {
                noSolution = true;
                break;
            }
            else if (Nol(m)) {
                manySolution = true;
                break; // persamaan parametriknya menyusul ya :)
            }

            // Jadikan elemen diagonal menjadi 1 (mo bikin 1 utama)
            double pembagi = m[i][i];
            for (int p = i; p < row+1; p++) {
                m[i][p] /= pembagi;
            }

            // eliminasi baris lainnya
            for (int q = 0; q < row; q++) {
                if (q != i) {
                    double faktor = m[q][i];
                    for (int r = i; r < row+1; r++) {
                        m[q][r] -= faktor * m[i][r]; // kurangin baris sm yg diatas
                    }
                }
            }
        }
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

    /*-------------- DETERMINAN DENGAN KOFAKTOR ------------------ */
    /* Mencari determinan akan dilakukan menggunakan kofaktor dari kolumn pertama, hal ini dilakukan karena pencarian deterimana
     * akan dilakuakan secara rekursif
     */

    // Mencari kofaktor dengan acuan kolumn pertama
    public static double[][] kofaktor(double[][] m, int size, int col,int start){
        double[][] cofactor = new double[size-1][size-1];
        int cRow =0;
        int cCol = 0;
        for (int i= 0; i< m.length;i++){
            cCol = 0;
            for (int j=1 ;j< m[0].length;j++){
                if (i != start){
                    cofactor[cRow][cCol] = m[i][j];
                    cCol++;
                }
            }
            if (i != start){
                cRow++;
            }
        }

        return cofactor;
    }

    // Mencari determinan dengan kofaktor kolom pertama
    public static double determinan(double[][]m){
        double det;
        det = 0;
        if (m.length == m[0].length){
            if (m.length == 1 && m[0].length == 1){
                det = m[0][0];
            } else {
                for (int i=0;i<m.length;i++){
                    if (i%2 == 0){
                        det += m[i][0] * determinan(kofaktor(m, m.length, m.length, i));
                    } else {
                        det -= m[i][0] * determinan(kofaktor(m, m.length, m.length, i));
                    }
                }
            }
        } else {
            det = 0;
        }
        return det;
    }

    /*-------------- MATRIKS BALIKAN DENGAN KOFAKTOR ------------------ */
    /*Mencari invers matrix dengan menggunakan adjoin dan determinan  */
    public static double[][] adjoin(double[][] m){
        double[][] cofactor = new double[m.length][m[0].length];
        if (m.length == m[0].length){
            for (int i=0;i<m.length;i++){
                for (int j=0;j<m[0].length;j++){
                    if (i%2 == 0){
                        if (j%2 == 0){
                            cofactor[i][j] = determinan(subMatrix(m, m.length, i, j));
                        } else {
                            cofactor[i][j] = determinan(subMatrix(m, m.length, i, j)) * (-1);
                        }
                    } else {
                        if (j%2 == 0){
                            cofactor[i][j] = determinan(subMatrix(m, m.length, i, j)) * (-1);
                        } else {
                            cofactor[i][j] = determinan(subMatrix(m, m.length, i, j));
                        }
                    }
                }
            }
        } 
        return matrixOP.transpose(cofactor);
    }

    public static double[][] subMatrix(double[][] m, int size, int startRow, int startCol){
        double[][] sub = new double[size-1][size-1];
        if (m.length == m[0].length){

            int cRow =0;
            int cCol = 0;
            for (int i= 0; i< m.length;i++){
                cCol = 0;
                for (int j=0 ;j< m[0].length;j++){
                    if (i != startRow){
                        if (j != startCol){
                            sub[cRow][cCol] = m[i][j];
                            cCol++;
                        }
                    } 
                }
                if (i != startRow){
                    cRow++;
                }
            }
        } 
        return sub;
    }


    /*-------------- KAIDAH CRAMMER ------------------ */
    /*Khusus untuk SPL dengan n variabel dan n persamaan */
    public static double[][] kaidahCramer(double[][] m){
        //Menerima matrix m dari keyboard atau txt dengan ukuran nRow x nCol
        int nRow = m.length;
        int nCol = m[0].length;

        //Membuat matrix X berukuran nRow x 1, berisi nilai X
        double[][] X = new double[nRow][1];
        for (int i = 0; i < nRow; i++){
            X[i][0] = 0;
        }

        //Membuat matrix A yang simetri, lalu mencari determinannya, detA != 0
        double[][] A = new double[nRow][nCol-1];
        matrixOP.copyMatrix(m, A, 0, nRow, 0, nCol-1);
        
        double detA = determinan(A);

        int i,j,k;
        for (j = 0; j < nCol-1; j++) {
            for (i = 0; i < nRow; i++) {
                //Mengganti nilai di tiap kolom A dengan nilai submatrix B
                A[i][j] = m[i][nCol-1];
            }
            X[j][0] = (double) determinan(A)/detA;
            for (k = 0; k < nRow; k++) {
                //Mereset nilai matrix A
                A[k][j] = m[k][j];
            }
        }
        return X;
    } 

    
    /*-------------- SPL DENGAN MATRIX BALIKAN ------------------ */
    public static Scanner scan;

    public static double[][] getMatrixA() {
        scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris: "); int row = scan.nextInt();
        System.out.print("Masukkan jumlah kolom: "); int col = scan.nextInt();
        // bikin matrix uk. row x col (A)
        double[][] A = new double[row][col];
        // isi matrix
        System.out.println("Elemen matriks A: ");
        for(int i = 0;i < row;i++) {
            for(int j = 0;j < col;j++) {
                A[i][j] = scan.nextDouble();
            }
        }
        return A;
    }

    public static double[][] getMatrixB() {
        scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris: "); int row = scan.nextInt();
        // bikin matrix uk. row A x 1 col (B)
        double[][] B = new double[row][1];
        // isi matrix
        System.out.println("Elemen matriks B: ");
        for(int p = 0; p < row; p++) {
            for(int q = 0; q < 1; q++) {
                B[p][q] = scan.nextDouble();
            }
        }
        return B;
    }
    
    public static double[][] SPLBalikan () {
        // membaca matriks koefisien (A)
        double[][] A = getMatrixA();

        // membaca matriks hasil (B)
        double[][] B = getMatrixB();

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

        // menghitung matriks augmented [A | I]
        double[][] augmentedMatrix = new double[matrixOP.getRow(A)][2 * matrixOP.getRow(A)];
        for (int i = 0; i < matrixOP.getRow(A); i++) {
            for (int j = 0; j < matrixOP.getRow(A); j++) {
                augmentedMatrix[i][j] = A[i][j];
                augmentedMatrix[i][j + matrixOP.getRow(A)] = I[i][j];
            }
        }

        // lakukan eliminasi Gauss-Jordan 
        eliminasiGauss(augmentedMatrix);

        // matrix.displayMatrix(augmentedMatrix);

        // mendapatkan matriks balikan dari matriks augmented yang sudah dilakukan operasi gauss-jordan
        double[][] AInverse = new double[matrixOP.getRow(A)][matrixOP.getRow(A)];
        for (int i = 0; i < matrixOP.getRow(A); i++) {
            for (int j = 0; j < matrixOP.getRow(A); j++) {
                AInverse[i][j] = augmentedMatrix[i][j + matrixOP.getRow(A)];
            }
        }

        // hasil matriks balikan = AInverse
        System.out.println("Matriks Balikan (A^(-1)): ");
        matrixIO.displayMatrix(AInverse);

        // mencari solusi SPL
        double[][] hasil = matrixOP.multiplyMatrixMatrix(AInverse, B);
        // matrix.displayMatrix(hasil);

        return hasil;
        // // simpan hasil
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
