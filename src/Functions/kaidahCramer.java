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
}
