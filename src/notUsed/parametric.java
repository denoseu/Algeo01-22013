package src.notUsed;
import src.Matrix.*;

public class parametric {

    public static String[] parameter (double[][] m, boolean gaussJordan) {
        // boolean gaussJordan tu buat tau dia sblmnya baru di gaus aja ato udah smpe gauss jordan
        // true = udah gauss jordan
        // false = baru gauss aja

        String[] solusi = new String[m[0].length - 1]; // array hasilnya nanti (output)
        String hasil = "";
        String[] par = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        if (gaussJordan == false) { // kalo masih blm di gauss jordan, di gauss jordan in dulu 
            gaussjordan.GaussJ(m);
        }

        for (int j = 0; j < (m[0].length) - 1; j++) { // i = kolom
            int lOne = matrixOP.satuUtama(m, m.length, j);
            if (lOne != (-999)) {
                hasil = hasil + ("x" + (j + 1) + " = " + m[lOne][(m[0].length) - 1]);
                for (int i = (j + 1); i < (m[0].length) - 1; i++) {
                    double xN = m[lOne][i];
                    if (xN != 0) {
                        hasil = hasil + (" + (" + (-xN) + ")" + par[i+1]);
                    }
                }
            } 
            else {
                hasil = "x" + (j + 1) + " = " + par[j+1];
            }
            solusi[j] = hasil;
            hasil = "";
        }

        for (int i = 0; i < m[0].length - 1; i++) {
            System.out.println(solusi[i]);
        }

        return solusi;
    }
} 
