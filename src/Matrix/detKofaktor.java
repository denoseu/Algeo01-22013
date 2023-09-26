package src.Matrix;

public class detKofaktor {
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

}
