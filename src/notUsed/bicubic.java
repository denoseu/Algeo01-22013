package src.notUsed;
import src.Matrix.*;

public class bicubic {
    // resize matrix 4x4 menjadi 16x1 untuk membuat y
    // dari input matrix 4x4
    public static double[][] resizeMatrix4x4(double[][] m) {
        double[][] newM = new double[16][1];

        int x = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newM[x][x%1] = m[i][j];
                x++;
            }
        }
        return newM;
    }

    public static void main (String[] args) {
        double[][] m = matrixIO.readMatrixKeyboard();
        double[][] newM = resizeMatrix4x4(m);
        matrixIO.displayMatrix(newM);
    }
}

