package src.Matrix;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.security.Principal;

import src.Functions.SPL;
import src.Matrix.*;

public class outputFormat {
    /*
    Interpolasi
     */ 
    public static String getPathOut(String name){
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
        return newPath;
    }
    public static void fileInterpolasi(){
        String m = matrixIO.inputFile();
        matrixIO.createFile(m);
        String newPath = getPathOut(m);
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
    public static void hasilGausstoFile(double[][] matriks, String path){
        double[] solusi = new double[matrixOP.getRow(matriks)];
        try {
            PrintStream file = new PrintStream(new FileOutputStream(path));
            PrintStream out = System.out;

            System.setOut(file);
            if (matrixOP.noSolusi(matriks)) {
                System.out.println("Matriks tidak memiliki solusi.");
            }
            else if ((matrixOP.Nol(matriks)) || (matrixOP.KolomNol(matriks))) {
                System.out.println("Matriks memiliki banyak solusi.");
                SPL.parameter(matriks, false);
            }
            else {
                for (int m = matrixOP.getRow(matriks) - 1; m >= 0; m -= 1) {
                    solusi[m] = matriks[m][matrixOP.getCol(matriks)-1];
                    for (int n = 1; n <= matrixOP.getRow(matriks) - m - 1; n += 1) {
                        solusi[m] = solusi[m] - matriks[m][m + n] * solusi[m + n];
                    } 
                }
    
                System.out.println("Solusi:");
                for (int i = 0; i < solusi.length; i++) {
                    System.out.printf("x%d = %.3f\n", i+1, solusi[i]);
                }
                
            }
            System.setOut(out);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public static void fileGauss(double[][] matrix){
        String m = matrixIO.inputFile();
        matrixIO.createFile(m);
        String newPath = getPathOut(m);
        FileWriter write;
        try {

            write = new FileWriter(newPath);

            BufferedWriter writeFile = new BufferedWriter(write);
            writeFile.write("-----HASIL GAUSS-----");
            writeFile.newLine();
            hasilGausstoFile(matrix, newPath);
            writeFile.flush();
            writeFile.close();

        } catch (IOException e){
            System.out.println("Terjadi Kesalahan. Tidak bisa menyimpan file.");
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        String path = matrixIO.inputFile();
        double[][] mat = matrixIO.fileToMatrix(path,2);
        fileGauss(mat);

    }
}
