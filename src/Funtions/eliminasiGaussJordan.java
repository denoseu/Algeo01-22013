package src.Funtions;
import src.Matrix.*;

public class eliminasiGaussJordan {

    public static void main () {
        double[][] matriks = matrixIO.readMatrixSPL();
        SPL.GaussJ(matriks);

        System.out.println("Matriks eselon baris tereduksi: ");
        matrixIO.displayMatrix(matriks);

        matrixOP.hasilSPLGauss(matriks);
    }

}
