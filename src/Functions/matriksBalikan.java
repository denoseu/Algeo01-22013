package src.Functions;
import src.Matrix.*;

public class matriksBalikan {
    public static void keyboard () {
        double[][] matriks = SPL.SPLBalikan();

        matrixOP.hasilSPLInverse(matriks);
    }
}
