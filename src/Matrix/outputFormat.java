package src.Matrix;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.security.Principal;
import java.text.DecimalFormat;

import src.Functions.SPL;
import src.Functions.regresiLinearBerganda;
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
            // hasilGausstoFile(matrix, newPath);
            writeFile.flush();
            writeFile.close();

        } catch (IOException e){
            System.out.println("Terjadi Kesalahan. Tidak bisa menyimpan file.");
            e.printStackTrace();
        }

    }
    
    public static void fileRLB(double[]s,double x){
        String m = matrixIO.inputFile();
        matrixIO.createFile(m);
        String newPath = getPathOut(m);
        FileWriter write;
        try {

            write = new FileWriter(newPath);

            BufferedWriter writeFile = new BufferedWriter(write);
            writeFile.write("-----HASIL REGRESI LINEAR BERGANDA-----");
            writeFile.newLine();
            // writeFile.write("f(x)= ");
            String str = "f(x)= ";
            DecimalFormat df = new DecimalFormat("0.000");
            for (int i = 0 ; i<s.length;i++){
                if (i == 0){
                    str += df.format(s[i]);
                } else {
                    if (s[i] < 0){
                        str += " - ";
                        str += df.format(Math.abs(s[i]));
                        str += "x";
                        str += Integer.toString(i);
                    } else if (s[i] == 0){
                        str += "";
                    } else {
                        str += " + ";
                        str += df.format(s[i]);
                        str += "x";
                        str += Integer.toString(i);

                    }
                }
            }
            writeFile.write(str);
            writeFile.newLine();
            writeFile.write("Hasil taksiran: ");
            writeFile.write(df.format(x));
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
        double[] s = regresiLinearBerganda.solutionReg(mat);
        double[] x = matrixIO.getTaksiran(path);
        double result = regresiLinearBerganda.estimateReg(s, x);
        fileRLB(s, result);

    }
}
