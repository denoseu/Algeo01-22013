package src.Functions;
import src.Matrix.*;
import src.notUsed.*;

public class matriksBalikan {
    public static void main () {
        double[][] matriks = SPL.SPLBalikan();

        matrixOP.hasilSPLInverse(matriks);
    }
}
