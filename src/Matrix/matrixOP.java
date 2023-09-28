package src.Matrix;
import src.Functions.*;

import java.util.Scanner;

public class matrixOP {
    public double[][] matrix;
    public int nRows;
    public int nCols;

    public static Scanner scan; // buat user input
    
    /* KONSTRUKTOR belom, masih bikin matrixnya manual di tiap function */
    
    /* SELECTOR */
    public static int getRow(double[][] m) {
        return m.length;
    }
    
    
    public static int getCol(double[][] m) {
        return m[0].length;
    }

    public static int getLastIdxRow(double[][] m) {
        return (getRow(m) - 1);
    }

    public static int getLastIdxCol(double[][] matrix) {
        return (getCol(matrix) - 1);
    }

    /* FUNCTION */

    // copyMatrix
    public static void copyMatrix(double[][] mIn, double[][] mOut, int rowMin, int rowMax, int colMin, int colMax)
    /* Mengcopy nilai mIn ke mOut dari range rowmin-rowmax dan colmin-colmax */
    {
        int i, j;
        for (i = rowMin; i < rowMax; i ++)
       {
          for (j = colMin; j < colMax; j++)
          {
            mOut[i][j] = mIn[i][j];
          }
       }
    }
    // addMatrix
    public static double[][] addMatrix (double[][] m1, double[][] m2) {
        int row = getRow(m1);
        int col = getCol(m1);
        double[][] m3 = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                m3[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return m3;
    }

    // substractMatrix
    public static double[][] substractMatrix (double[][] m1, double[][] m2) {
        int row = getRow(m1);
        int col = getCol(m1);
        double[][] m3 = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                m3[i][j] = m1[i][j] - m2[i][j];
            }
        }
        return m3;
    }

    // multiplyMatrix
    public static double[][] multiplyMatrixMatrix (double[][] m1, double[][] m2) {
        int row = getRow(m1);
        int col = getCol(m2);
        double[][] m3 = new double[row][col];
        for (int i = 0; i < getRow(m1); i++) {
            for (int j = 0; j < getCol(m2); j++) {
                for (int k = 0; k < getCol(m1); k++) {
                    m3[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return m3;
    }

    // multiplyByConst
    public static double[][] multiplyByConst (double[][] m, int x) {
        int row = getRow(m);
        int col = getCol(m);
        double[][] mX = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mX[i][j] = m[i][j] * x;
            }
        }
        return mX;
    }

    // isMatrixEqual
    public static boolean isMatrixEqual (double[][] m1, double[][] m2) {
        if (getRow(m1) != getRow(m2) || getCol(m1) != getCol(m2)) {
            return false;
        }
        for (int i = 0; i < getRow(m1); i++) {
            for (int j = 0; j < getCol(m1); j++) {
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // isMatrixNotEqual
    public static boolean isMatrixNotEqual (double[][] m1, double[][] m2) {
        return (! (isMatrixEqual(m1, m2)));
    }

    // isMatrixSizeEqual
    public static boolean isMatrixSizeEqual (double[][] m1, double[][] m2) {
        return ((getRow(m1) == getRow(m2)) && (getCol(m1) == getCol(m2)));
    }


    // countElmt
    public static int countElmt(double[][] m) {
        return (getCol(m) * getRow(m));
    }

    // isSquare
    public static boolean isSquare (double[][] m) {
        return (getCol(m) == getRow(m));
    }
    // isSymmetric
    public static boolean isSymmetric (double[][] m){
        if (isSquare(m)){
            for (int i=0;i<getRow(m);i++){
                for (int j=0; j<getCol(m);j++){
                    if (m[i][j] != m[j][i]){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    // isIdentity
    public static boolean isIdentity(double[][] m){
        if(isSquare(m)){
            for (int i=0;i<getRow(m);i++){
                for (int j=0;j<getCol(m);j++){
                    if (i==j){
                        if (m[i][j] !=1){
                            return false;
                        }
                    } else {
                        if (m[i][j] != 0){
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
    // isSparse
    public static boolean isSparse(double[][] m){
        double count = 0;
        for (int i =0;i < getRow(m);i++){
            for (int j=0; j<getCol(m); j++){
                if (m[i][j] !=0){
                    count += 1;
                }
            }
        }
        return (count <= (countElmt(m) * 5/100));
    }
    // negation
    public static void negation(double[][] m){
        for (int i=0;i<getRow(m);i++){
            for (int j=0; j<getCol(m);j++){
                m[i][j] *= -1;
            }
        }
   }
    // transpose
    public static double[][] transpose(double[][] m){
        double[][] trans = new double[m.length][m[0].length];
        if (isSquare(m)){

            for (int i=0;i<getRow(m);i++){
                for (int j=0;j<getCol(m);j++){
                    trans[i][j] = m[j][i];
                }
            }
        }
        return trans;
    }

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

    // cek apakah dia baris terakhirnya semuanya 0 kecuali solusi
    // intinya dia ga ad solusi
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

    public static boolean noSolusiJordan (double[][] m, int col, int i) {
        // cari sampe N-1 elemen, ada yang bukan 0 ga
        for (int j = 0; j < col - 1; j++) {
            if (m[i][j] != 0) {
                return false;  // karena kalo ga 0 dia baik" saja
            }
        }
        // lalu kalo udah cek atas dia ngecek elemen terakhirnya
        // klo bukan 0 terus yang lainnya 0 berarti emang ga ada solusi

        if ((m[i][col-1] != 0)) {
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

    // cek apakah dalam 1 baris ada yang nol ato ga
    public static boolean barisNol(double[][] m, int col, int i) {
        for (int j = 0; j < col; j++) {
            if (m[i][j] != 0) {
                return false;
            }
        }
        return true;
    }

    // pindahin baris nol ke bawah
    public static void pindahBarisNol (double[][] m, int row, int col) {
        for(int i = 0; i < row; i += 1) {
            if (barisNol(m, col, i)) { // kalo dia 0 semua
                tukar_baris(m, i, (row - 1)); // pindah ke paling bawah
            }
        }
    }

    // bikin matriks default yang no solusi
    public static void matriksNoSolusi (double[][] m) {
        // cari sampe N-1 elemen, ada yang bukan 0 ga
        for (int j = 0; j < matrixOP.getCol(m) - 1; j++) {
            m[matrixOP.getRow(m)-1][j] = 0; // bikin dia jadi 0
        }
        m[matrixOP.getRow(m)-1][matrixOP.getCol(m)-1] = 1;

    }

    public static int satuUtama(double[][] m, int row, int j) {
        // j adalah banyak kolom
        int i; 
        for (i = row - 1; i >= 0; i--) {
            if (m[i][j] == 1) {
                break; // dah ketemu satu utamanya
            }
        }
        if (i != row) {
            return i; // return index si 1 utama
        } 
        else {
            
            return -999; // mark
        }

    }

    public static void hasilSPLGauss (double[][] matriks) {
        double[] solusi = new double[matrixOP.getRow(matriks)];

        if (matrixOP.noSolusi(matriks)) {
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else if (matrixOP.Nol(matriks)) {
            System.out.println("Matriks memiliki banyak solusi.");
            SPL.parameter(matriks, false);
        }
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

    public static void hasilSPLGaussJordan (double[][] matriks) {
        double[] solusi = new double[matrixOP.getRow(matriks)];

        if (matrixOP.noSolusi(matriks)) {
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else if (matrixOP.Nol(matriks)) {
            System.out.println("Matriks memiliki banyak solusi.");
            SPL.parameter(matriks, true);
        }
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
    
    public static void hasilSPLInverse (double[][] matriks) {
        // simpan hasil dalam array biar bisa ditampilin
        double[] solusi = new double[matrixOP.getRow(matriks)];

        for (int x = matrixOP.getRow(matriks) - 1; x >= 0; x -= 1) {
            solusi[x] = matriks[x][0];
        }

        System.out.println("Solusi:");
        for (int i = 0; i < solusi.length; i++) {
            System.out.printf("x%d = %.3f\n", i+1, solusi[i]);
        }
    }
}
