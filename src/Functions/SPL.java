package src.Functions;
import java.util.Scanner;

import src.Matrix.*;

public class SPL {
    /*-------------- GAUSS ------------------ */
    public static void recursiveGauss (double[][] m) {
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

    public static void eselonbaris(double[][] m) {
        boolean found = false;
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
                else if (m[i][j] == 0) {
                    int max = i;
                    for (int n = i+1; n < matrixOP.getRow(m); n++) { 
                        if (m[n][i] != 0) {
                            found = true;
                            max = n;
                            break;
                        }
                    }
                    if (found == true) {
                        matrixOP.tukar_baris(m, i, max);
                        SPL.recursiveGauss(m);
                        break;
                    } 
                }
            }
        }
    }

    /*-------------- GAUSS JORDAN ------------------ */
    public static void GaussJ(double[][] m) {

        // gauss-in dulu biar dapet matriks eselon baris
        // nanti dilanjutin supaya dapet eselon baris tereduksi
        eselonbaris(m);
        int row = matrixOP.getRow(m);
        int col = matrixOP.getCol(m);
        int p, q, r, s;
        // dari gauss harusnya dah dapet smua leading 1, tinggal buat elemen diatasnya 0
        
        // cari indeks baris leading one pada kolom yang lagi di cek
        for (p = row - 1; p > 0; p--) {
            // cari leading 1 pada baris terakhir
            for (q = 0; q < col; q++) {
                if (m[p][q] != 0) {
                    break; // ketemu 1 utamanya
                }
            }

            if (q >= col) { // untuk kasus baris 0
                q = 0;
            }

            // bikin semua elemen diatas leading 1 menjadi 0
            for (r = p - 1; r >= 0 ; r--){
                double faktor = m[r][q];
                for (s = 0; s < col; s++){
                    m[r][s] -= (faktor * m[p][s]);
                    // System.out.println(" ");
                    // matrixIO.displayMatrix(m);
                }
            }
        }

        // untuk handle kasus -0
        for(int x = 0; x < matrixOP.getRow(m); x++) {
            for(int y = 0; y < matrixOP.getCol(m); y++) {
                if (m[x][y] == -0) {
                    m[x][y] = 0;
                }
            }
        }
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
            det = -9999;
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

    public static double[][] inverse(double[][] m){
        double[][] inv = new double[m.length][m[0].length];
        double[][] adj = adjoin(m);
        if (determinan(m)!=0){

            for (int i=0;i<m.length;i++){
                for (int j=0;j<m[0].length;j++){
                    inv[i][j] = adj[i][j]/(determinan(m));
                }
            }
        } 
        return inv;
    }


    /*-------------- KAIDAH CRAMER ------------------ */
    /*Khusus untuk SPL dengan n variabel dan n persamaan */
    public static double[][] kaidahCramer(double[][] m){
        //Menerima matrix m dari keyboard atau txt dengan ukuran nRow x nCol
        int nRow = m.length;
        int nCol = m[0].length;
        
        //Membuat matrix A, matrix A harus simetri
        double[][] A = new double[nRow][nCol-1];
        //Membuat matrix X berukuran nRow x 1, berisi nilai X
        double[][] X = new double[nRow][1];
        if (nCol - 1 != nRow){
            System.out.println("Tidak dapat menggunakan kaidah Cramer.");
        }
        else if (matrixOP.noSolusi(m)){
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else{
            matrixOP.copyMatrix(m, A, 0, nRow, 0, nCol-1);
            // mencari determinan matrix A, detA != 0
            double detA = determinan(A);
            if (detA == 0){
                System.out.println("Determinan matriks bernilai 0 sehingga tidak dapat menggunakan kaidah Cramer.");
            }
            else{
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
        System.out.print("Masukkan jumlah baris (sama dengan sebelumnya): "); int row = scan.nextInt();
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
        // matrixIO.displayMatrix(I);

        // menghitung matriks augmented [A | I]
        double[][] augmentedMatrix = new double[matrixOP.getRow(A)][2 * matrixOP.getRow(A)];
        for (int i = 0; i < matrixOP.getRow(A); i++) {
            for (int j = 0; j < matrixOP.getRow(A); j++) {
                augmentedMatrix[i][j] = A[i][j];
                augmentedMatrix[i][j + matrixOP.getRow(A)] = I[i][j];
            }
        }
        matrixIO.displayMatrix(augmentedMatrix);

        // lakukan eliminasi Gauss-Jordan
        GaussJ(augmentedMatrix);

        // matrixIO.displayMatrix(augmentedMatrix);

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
        // matrixIO.displayMatrix(hasil);

        return hasil;
    }

    
    /*-------------- DETERMINAN DENGAN REDUKSI BARIS ------------------ */
    public static int idxFirstRowNot0(double[][] m, int idxcol)
    {
        //Mencari idx baris pertama yang nilainya != 0 di kolom idxcol
        int idx = 0;
        for (int i = 0; i < matrixOP.getRow(m); i++){
            if (m[i][idxcol] != 0){
                idx = i;
            }
        }
        return idx;
    }

    /*-------------- DETERMINAN DENGAN REDUKSI BARIS ------------------ */
    public static int FirstElementNot0(double[][] m, int startrowIdx, int colIdx) {
        //Mengecek idx baris pertama yang tidak bernilai 0 pada colIdx 
        int idx = startrowIdx;
        for (int rowIdx = startrowIdx; rowIdx < m.length; rowIdx++){
            if (m[rowIdx][colIdx] != 0){
                idx = rowIdx;
                break;
            }
        }
        return idx;
    }

    public static double detReduksiBaris(double[][] m)
    {
        if (matrixOP.isIdentity(m))
        {
            return 1;
        }
        if (matrixOP.isSquare(m)){
            int i,j;
            int p = 0; //jumlah pertukaran baris
            int n = matrixOP.getRow(m);

            for (int rowidx = 0; rowidx < n-1; rowidx++){
                if (FirstElementNot0(m, rowidx, 0) != rowidx){
                    matrixOP.tukar_baris(m, FirstElementNot0(m, rowidx, 0), rowidx);
                    p++;
                }
            }

            for (i = 1; i < n; i++){
                for (j = 0; j < i; j++){
                    if (m[i][j] != 0){
                        // matrixIO.displayMatrix(m);
                        if (m[i-1][j]==0){
                            double faktor = m[i][j]/m[FirstElementNot0(m, 0, j)][j];
                            for (int l = 0; l < n; l++)
                            {
                                m[i][l] -= m[FirstElementNot0(m, 0, j)][l] * faktor;
                            }
                        }
                        else{
                            double faktor = m[i][j]/m[i-1][j];
                            for (int l = 0; l < n; l++){
                                m[i][l] -= m[i-1][l] * faktor;
                            }
                        }
                    }
                }
            }

            //Menghitung determinan dengan mengali semua elemen diagonal
            double det = 1;
            for (int q = 0; q < n; q++){
                det *= m[q][q];
            }
            det *= Math.pow((-1), p);

            if (det == -0) {
                det = 0;
            }
            return det;
        
        }
        else{
            //Matriks tidak simetri
            return -9999;
        }
    }

    /*-------------- PERSAMAAN PARAMETER UNTUK GAUSS, GAUSS-JORDAN ------------------ */

    public static String[] parameter (double[][] m, boolean gaussJordan) {
        // boolean gaussJordan tu buat tau dia sblmnya baru di gaus aja ato udah smpe gauss jordan
        // true = udah gauss jordan
        // false = baru gauss aja

        String[] solusi = new String[m[0].length - 1]; // array hasilnya nanti (output)
        String hasil = "";
        String[] par = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        if (gaussJordan == false) { // kalo masih blm di gauss jordan, di gauss jordan in dulu 
            GaussJ(m);
        }

        for (int j = 0; j < (matrixOP.getCol(m)) - 1; j++) { // i = kolom
            int lOne = matrixOP.satuUtama(m, matrixOP.getRow(m), j);
            if (lOne != (-1)) {
                hasil = hasil + ("x" + (j + 1) + " = " + m[lOne][(matrixOP.getCol(m)) - 1]);
                for (int i = (j + 1); i < (matrixOP.getCol(m)) - 1; i++) {
                    double xN = m[lOne][i];
                    if (xN != 0) {
                        hasil = hasil + (" + (" + (-xN) + ")" + par[i+1]);
                    }
                }
            } 
            else {
                hasil = "x" + (j + 1) + " = " + par[j+1];
            }
            solusi[j] = hasil;
            hasil = "";
        }

        for (int i = 0; i < (matrixOP.getCol(m)) - 1; i++) {
            System.out.println(solusi[i]);
        }

        return solusi;
    }
}
