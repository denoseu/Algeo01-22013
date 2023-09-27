package src.Matrix;

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

    // tukar baris
    public static double[][] tukar_baris (double[][] m, int row1, int row2) {
        for (int j = 0; j < getCol(m); j++) {
            double temp = m[row1][j];
            m[row1][j] = m[row2][j];
            m[row2][j] = temp;
        }
        return m;
    }


    
}
