package src.Functions;
import src.Matrix.*;

public class eliminasiGaussJordan {

    public static void keyboard () {
        double[][] matriks = matrixIO.readMatrixSPL();
        SPL.GaussJ(matriks);

        System.out.println("Matriks eselon baris tereduksi: ");
        matrixIO.displayMatrix(matriks);

        matrixOP.hasilSPLGaussJordan(matriks);
    }

    public static void file () {
        double[][] matriks;
        String path = matrixIO.inputFile();
        matriks = matrixIO.fileToMatrix(path, 1);
        SPL.GaussJ(matriks);

        System.out.println("Matriks eselon baris tereduksi: ");
        matrixIO.displayMatrix(matriks);

        matrixOP.hasilSPLGaussJordan(matriks);
    }

}
