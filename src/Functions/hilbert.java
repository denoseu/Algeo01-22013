package src.Functions;
import src.Matrix.*;

public class hilbert {
    public static double[][] kaidahCramerBert(double[][] m){
        //Menerima matrix m dari keyboard atau txt dengan ukuran nRow x nCol
        int nRow = m.length;
        int nCol = m[0].length;
        
        //Membuat matrix A, matrix A harus simetri
        double[][] A = new double[nRow][nCol-1];
        //Membuat matrix X berukuran nRow x 1, berisi nilai X
        double[][] X = new double[nRow][1];
        if (nCol - 1 != nRow){
            X[0][0] = -9999;
        }
        else if (matrixOP.noSolusi(m)){
            X[0][0] = -999;
        }
        else{
            matrixOP.copyMatrix(m, A, 0, nRow, 0, nCol-1);
            // mencari determinan matrix A, detA != 0
            double detA = SPL.detReduksiBaris(A);
            if (detA == 0){
                //Determinan matriks bernilai 0 sehingga tidak dapat menggunakan kaidah Cramer
                X[0][0] = -99999;
            }
            else{
                int i,j,k;
                for (j = 0; j < nCol-1; j++) {
                    for (i = 0; i < nRow; i++) {
                        //Mengganti nilai di tiap kolom A dengan nilai submatrix B
                        A[i][j] = m[i][nCol-1];
                    }
                    X[j][0] = (double) SPL.detReduksiBaris(A)/detA;
                    for (k = 0; k < nRow; k++) {
                        //Mereset nilai matrix A
                        A[k][j] = m[k][j];
                    }
                }
            }
        }
        return X;
    }
}
