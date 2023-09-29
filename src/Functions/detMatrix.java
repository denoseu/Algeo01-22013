package src.Functions;
import src.Matrix.*;

public class detMatrix {
    public static void redBarisKeyboard() {
        double[][] matriks = matrixIO.readMatrixKeyboard();

        System.out.printf("Determinan: %.3f\n", SPL.detReduksiBaris(matriks));
    }
}
