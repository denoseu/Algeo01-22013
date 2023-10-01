package src.Functions;
import src.Matrix.*;

public class kaidahCramer {
    public static void keyboard (){
        double[][] m;
        m = matrixIO.readMatrixSPL();

        System.out.println("Solusi:");
        for (int i = 0; i < SPL.kaidahCramer(m).length; i++) {
            System.out.printf("x%d = %.3f\n", i+1, SPL.kaidahCramer(m)[i][0]);
        }
    }

    public static void file (){
        double[][] m;
        String path = matrixIO.inputFile();
        m = matrixIO.fileToMatrix(path, 1);

        System.out.println("Solusi:");
        for (int i = 0; i < SPL.kaidahCramer(m).length; i++) {
            System.out.printf("x%d = %.3f\n", i+1, SPL.kaidahCramer(m)[i][0]);
        }
    }
}
