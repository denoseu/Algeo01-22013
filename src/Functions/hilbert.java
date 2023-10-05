package src.Functions;
import java.text.DecimalFormat;

import src.Matrix.*;

public class Hilbert {

    public static double[][] copyMatrixHilbert(double[][] mIn) {
        double[][] mOut = new double[matrixOP.getRow(mIn)][matrixOP.getCol(mIn)];
        int i, j;
        for (i = 0; i < matrixOP.getRow(mIn); i ++) {
            for (j = 0; j < matrixOP.getCol(mIn); j++) {
                mOut[i][j] = mIn[i][j];
            }
        }
        return mOut;
    }

    public static double detHilbert(double[][] mIn){
        int i, j, k, l, p;
        double det;
        double bukannol;
        double temp;
        double[][] mOut;
        mOut = copyMatrixHilbert(mIn);
        temp = 1;
        for (i = 0; i < matrixOP.getRow(mOut); i++){
    
            // cari elemen bukan 0 pertama pada baris
            bukannol = 1;
            for (j = 0; j < matrixOP.getCol(mOut); j++){
                if(mOut[i][j] != 0){
                    bukannol = mOut[i][j];
                    temp = temp * bukannol;
                    break;
                }
            }
            if (j >= matrixOP.getCol(mOut)){ // handle kasus yang barisnya 0
                j = 0;
            }
            
            // buat leading 1
            for (k = 0; k < matrixOP.getCol(mIn); k++){
                mOut[i][k] = mOut[i][k]/bukannol;
            }

            // buat elemen di bawah leading 1 jd 0
            for (l = i + 1; l < matrixOP.getRow(mOut); l++) {
                double faktor = mOut[l][j];
                for(p = 0; p < matrixOP.getCol(mOut); p++){
                    mOut[l][p] = mOut[l][p] - faktor*mOut[i][p];
                }
            }      
        }

        // tukar baris
        int left;
        int row = 0;
        int iterate;

        for(iterate = 0; iterate < matrixOP.getRow(mOut); iterate++) {
            left = matrixOP.getCol(mOut)-1;
            row = iterate;
            for(i = iterate; i < matrixOP.getRow(mOut); i++) {
                j = 0;
                while(mOut[i][j] == 0 && j < matrixOP.getCol(mOut)-1){
                    j++;
                }
                
                if(left > j) {
                    left = j;
                    row = i;
                }
            }

            matrixOP.tukar_baris(mOut, row, iterate);

            if (row != iterate) {
                temp = temp*-1;
            }
        }

        det = 1;
        for(i=0; i < matrixOP.getRow(mOut); i++){
            det = det * mOut[i][i];
        }
        return (det * temp);
    }

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
            double detA = detHilbert(A);
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
                    X[j][0] = (double) detHilbert(A)/detA;
                    for (k = 0; k < nRow; k++) {
                        //Mereset nilai matrix A
                        A[k][j] = m[k][j];
                    }
                }
            }
        }
        return X;
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
