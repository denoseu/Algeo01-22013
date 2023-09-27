package src.Functions;
import src.Matrix.*;

public class matriksBalikan {
    public static void main () {
        double[][] matriks = SPL.SPLBalikan();

        matrixOP.hasilSPLInverse(matriks);
    }
}
