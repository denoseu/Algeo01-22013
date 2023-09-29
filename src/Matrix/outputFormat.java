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
        String name = m;

        //Mendapatkan directory untuk file
        String newPath;
        String currentPath = System.getProperty("user.dir");
        System.out.println(currentPath);
        if (currentPath.contains("src\\")){
            String sub[] = currentPath.split("src");
            currentPath = sub[0];

            newPath = currentPath + "test\\output\\" + name;
            System.out.println(currentPath);
        } else {
            newPath = currentPath + "\\test\\output\\" + name;
        }
        FileWriter write;
        try {

            write = new FileWriter(newPath);

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
