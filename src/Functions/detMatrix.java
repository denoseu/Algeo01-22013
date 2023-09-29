package src.Functions;
import src.Matrix.*;

public class detMatrix {
    public static void redBarisKeyboard() {
        double[][] matriks = matrixIO.readMatrixKeyboard();

        System.out.printf("Determinan: %.3f\n", SPL.detReduksiBaris(matriks));
    }

    public static void redBarisfile() {
        double[][] matriks = matrixIO.fileToMatrix(1);

        System.out.printf("Determinan: %.3f\n", SPL.detReduksiBaris(matriks));
    }

    public static void ekspansiKeyboard() {
        double[][] matriks = matrixIO.readMatrixKeyboard();

        System.out.printf("Determinan: %.3f\n", SPL.determinan(matriks));
    }

    public static void ekspansiFile() {
        double[][] matriks = matrixIO.fileToMatrix(1);

        System.out.printf("Determinan: %.3f\n", SPL.determinan(matriks));
    }
}
