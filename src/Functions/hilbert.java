package src.Functions;
import java.text.DecimalFormat;

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
            double detA = SPL.detRedHilbert(A);
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
                    X[j][0] = (double) SPL.detRedHilbert(A)/detA;
                    for (k = 0; k < nRow; k++) {
                        //Mereset nilai matrix A
                        A[k][j] = m[k][j];
                    }
                }
            }
        }
        return X;
    }

    public static double detHilbert(double[][] matrix){
        double[][] m = matrix;
        int cTukarBaris = 0;
        double det = 1;
        double k = 1;
        // mencari element tidak 0 pertama
        for (int i=0; i< m.length;i++){
            int tempRow = i;

            while ( (tempRow < m.length) && (m[tempRow][i] == 0) ){
                tempRow++;
            }

            if (tempRow == m.length){
                System.out.println("Tidak dapat diselesaikan dengan metode ini");
                return 0;
            }

            //tuker baris
            if (tempRow != i){
                for (int j=0;j<m.length;j++){
                    double temp = m[i][j];
                    m[i][j] = m[tempRow][j];
                    m[tempRow][j] = temp;
                }
                cTukarBaris++;
            }

            //OBE
            for ( int j = i+1;j<m.length;j++){
                double el1 = m[i][i];
                double el2 = m[j][i];

                for (int tempCol = 0 ; tempCol < m.length;tempCol++){
                    double newEL = (el1 * m[j][tempCol]) - (el2 * m[i][tempCol]);
                    m[j][tempCol] = newEL;
                }
                System.out.println("el1 : " + el1);
                k = (double) el1 * k;
                System.out.println("k: " + k);

            }

            matrixIO.displayMatrix(m);
        }
        // det
        for (int row = 0; row< m.length;row++){
            det *= m[row][row];
        }
        System.out.println("k: " + k);
        det *= Math.pow(-1, cTukarBaris);
        det = det / k;
        return det;
    }

    public static void main (String[] args){
        double[][] hil = matrixIO.splHilbert(9);
        double[][] ril = matrixOP.getABalikan(hil);
        matrixIO.displayMatrix(hil);
        DecimalFormat df = new DecimalFormat("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        // System.out.println(df.format(SPL.determinan(ril)));
        // System.out.println(df.format(SPL.detReduksiBaris(ril)));
        // double[][] ril = matrixIO.readMatrixKeyboard();
        double det = detHilbert(ril);
        System.out.println("dethilbert:" + df.format(det));
    }
}
