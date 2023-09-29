package src.notUsed;
import src.Functions.*;
import src.Matrix.*;

public class cramer {
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
        
        double detA = SPL.determinan(A);

        int i,j,k;
        for (j = 0; j < nCol-1; j++) {
            for (i = 0; i < nRow; i++) {
                //Mengganti nilai di tiap kolom A dengan nilai submatrix B
                A[i][j] = m[i][nCol-1];
            }
            X[j][0] = (double) SPL.determinan(A)/detA;
            for (k = 0; k < nRow; k++) {
                //Mereset nilai matrix A
                A[k][j] = m[k][j];
            }
        }
        return X;
    } 

    public static void main(String[] args){
        double[][] m;
        m = matrixIO.readMatrixSPL();

        System.out.println("Solusi:");
        for (int i = 0; i < kaidahCramer(m).length; i++) {
            System.out.printf("x%d = %.3f\n", i+1, kaidahCramer(m)[i][0]);
        }
    }
    
}
