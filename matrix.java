// ADT MATRIX

import java.util.Scanner;

public class matrix {
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

    // readMatrix
    public static double[][] readMatrixKeyboard() {
        scan = new Scanner(System.in);
        System.out.print("row: "); int row = scan.nextInt();
        System.out.print("col: "); int col = scan.nextInt();
        // bikin matrix uk. row x col
        double[][] m = new double[row][col];
        // isi matrix
        System.out.println("Elemen matriks: ");
        for(int i = 0;i < row;i++) {
            for(int j = 0;j < col;j++) {
                m[i][j] = scan.nextDouble();
            }
        }

        return m;
    }

    // displayMatrix
    // matrix buat access row, matrix[] buat access col
    public static void displayMatrix (double[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (j < m[i].length-1) {
                    System.out.print(m[i][j] + " ");
                }
                else {
                    System.out.println(m[i][j]);
                }
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
    public static void transpose(double[][] m){
        for (int i=0;i<getRow(m);i++){
            for (int j=0;j<getCol(m);j++){
                double temp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = temp;
            }
        }
    }

}
