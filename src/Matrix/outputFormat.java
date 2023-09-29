package src.Matrix;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import src.Matrix.*;

public class outputFormat {
    /*
    Interpolasi
     */ 

    public static void fileInterpolasi(){
        String m = matrixIO.inputFile();
        matrixIO.createFile(m);

        FileWriter write;
        try {

            write = new FileWriter(m);
    
            BufferedWriter writeFile = new BufferedWriter(write);
            writeFile.write("-----HASIL INTERPOLASI-----");
            writeFile.newLine();
            writeFile.flush();
            writeFile.close();

        } catch (IOException e){
            System.out.println("Terjadi Kesalahan. Tidak bisa menyimpan file.");
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        fileInterpolasi();
    }
}
