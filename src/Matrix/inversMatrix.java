package src.Matrix;

public class inversMatrix {
    /*Mencari invers matrix dengan menggunakan adjoin dan determinan  */
    public static double[][] adjoin(double[][] m){
        double[][] cofactor = new double[m.length][m[0].length];
        if (m.length == m[0].length){
            for (int i=0;i<m.length;i++){
                for (int j=0;j<m[0].length;j++){
                    if (i%2 == 0){
                        if (j%2 == 0){
                            cofactor[i][j] = detKofaktor.determinan(subMatrix(m, m.length, i, j));
                        } else {
                            cofactor[i][j] = detKofaktor.determinan(subMatrix(m, m.length, i, j)) * (-1);
                        }
                    } else {
                        if (j%2 == 0){
                            cofactor[i][j] = detKofaktor.determinan(subMatrix(m, m.length, i, j)) * (-1);
                        } else {
                            cofactor[i][j] = detKofaktor.determinan(subMatrix(m, m.length, i, j));
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
        if (m.length == m[0].length){

            if (detKofaktor.determinan(m)!=0){
    
                for (int i=0;i<m.length;i++){
                    for (int j=0;j<m[0].length;j++){
                        inv[i][j] = adj[i][j]/(detKofaktor.determinan(m));
                    }
                }
            } 
        } 
        return inv;
    }
    
    public static void main(String[] args){
        double[][] m;
        m = matrixIO.readMatrixKeyboard();
        matrixIO.displayMatrix(inverse(m));
    }

}
