package src.Functions;
import src.Matrix.*;

public class matriksBalikan {

    // SPL
    public static void keyboard () {
        double[][] matriks = SPL.SPLBalikan();

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
        m = matrixIO.fileToMatrix(1);
        matrixIO.displayMatrix(SPL.inverse(m));
    }

}
