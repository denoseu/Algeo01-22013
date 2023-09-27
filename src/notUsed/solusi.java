package src.notUsed;
import src.Matrix.*;

public class solusi {
    public static void hasilSPLGauss (double[][] matriks) {
        double[] solusi = new double[matrixOP.getRow(matriks)];

        if (matrixOP.noSolusi(matriks)) {
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else if (matrixOP.Nol(matriks)) {
            System.out.println("Matriks memiliki banyak solusi.");
        } // persamaan parametriknya menyusul ya :)
        else {
            for (int m = matrixOP.getRow(matriks) - 1; m >= 0; m -= 1) {
                solusi[m] = matriks[m][matrixOP.getCol(matriks)-1];
                for (int n = 1; n <= matrixOP.getRow(matriks) - m - 1; n += 1) {
                    solusi[m] = solusi[m] - matriks[m][m + n] * solusi[m + n];
                } 
            }

            System.out.println("Solusi:");
            for (int i = 0; i < solusi.length; i++) {
                System.out.printf("x%d = %.3f\n", i+1, solusi[i]);
            }
            
        }
    }
    
    public static void hasilSPLInverse (double[][] matriks) {
        // simpan hasil dalam array biar bisa ditampilin
        double[] solusi = new double[matrixOP.getRow(matriks)];

        for (int x = matrixOP.getRow(matriks) - 1; x >= 0; x -= 1) {
            solusi[x] = matriks[x][0];
        }

        System.out.println("Solusi:");
        for (int i = 0; i < solusi.length; i++) {
            System.out.printf("x%d = %.3f\n", i+1, solusi[i]);
        }
    }
}
