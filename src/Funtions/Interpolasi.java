package src.Funtions;
import src.Matrix.*;
public class Interpolasi {
    public static double[][] interpolasi(){
        double [][] m = matrixIO.readMatrixInterpolasi();
        double [][] tempM = new double[m.length][m.length+1];
        // double result = 0;
        // masukan n, (x0,y0)....
        // harus diublah dulu jdi matrix
        for (int i = 0;i< m.length;i++){
            for(int j = 0; j < m.length + 1 ; j++){
                if (j == m.length){
                    tempM[i][j] = m[i][1];
                } else {
                    tempM[i][j] = Math.pow(m[i][0], j);
                }
            }
        }

        
        return tempM;

        // blom jadi
        // harusnya nanti di gauss trus dpt hasil a nya
        // habis itu x nya di power in trus dikali ke a tambahin ke result
    
    }

    public static void main(String[] args){
        double[][] m = interpolasi();
        matrixIO.displayMatrix(m);
    }
}
