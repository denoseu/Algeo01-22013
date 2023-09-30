package src.Functions;
import src.Matrix.*;

public class matriksBalikan {

    // SPL
    public static void keyboard () {
        double[][] matriks = SPL.SPLBalikan();
        matrixIO.displayMatrix(SPL.inverse(matriks));
        matrixOP.hasilSPLInverse(matriks);
    }

    // INVERSE
    public static void inversKey () {
        double[][] m;
        m = matrixIO.readMatrixKeyboard();
        matrixIO.displayMatrix(SPL.inverse(m));
    }
    public static void inversFile () {
        double[][] m;
        String path = matrixIO.inputFile();
        m = matrixIO.fileToMatrix(path, 1);
        matrixIO.displayMatrix(SPL.inverse(m));
    }

}
