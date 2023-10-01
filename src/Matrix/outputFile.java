package src.Matrix;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;

import src.Functions.*;
import src.Matrix.*;

public class outputFile {

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

    public static boolean isNoSolution (double[] s){
        for (int i =0 ;i< s.length;i++){
            if (s[i] != 0){
                return false;
            }
        }
        return true;
    }

    public static boolean isSolutionBanyak (double[] s){
        for (int i =0 ;i< s.length;i++){
            if (s[i] != -999 ){
                return false;
            }
        }
        return true;
    }

    public static void fileInterpolasi(double[] s, double[] taksiran,double result){
        String m = matrixIO.inputFile();
        matrixIO.createFile(m);
        String newPath = getPathOut(m);
        FileWriter write;
        try {

            write = new FileWriter(newPath);

            BufferedWriter writeFile = new BufferedWriter(write);
            writeFile.write("-----HASIL INTERPOLASI-----");
            writeFile.newLine();
            DecimalFormat df = new DecimalFormat("0.000");
            String fx = "f(x)= ";
            for (int i = s.length-1 ; i>=0;i--){
                if (i == s.length-1){
                    if (s[i] == 0){
                        fx += "";
                    }else {

                        fx +=(df.format(s[i]));
                        fx +=("x^" + Integer.toString(i));
                    }
                } else if (i == 0) {
                    if (s[i] < 0){
                        fx += (" - " + df.format(Math.abs(s[i])));
                    } else if (s[i] == 0){
                        fx += ("");
                    } else {
                        fx += (" + " + df.format(s[i]));
                    }
                } else if (i == 1) {
                    if (s[i] < 0){
                        fx += (" - " + df.format(Math.abs(s[i])));
                        fx += ("x");
                    } else if (s[i] == 0){
                        fx += ("");
                    } else {

                        fx += (" + " + df.format(s[i]));
                        fx += ("x");
                    }
                } else {
                    
                    if (s[i] < 0){
                        fx += (" - " + df.format(Math.abs(s[i])));
                        fx += ("x^" + Integer.toString(i));
                    } else if (s[i] == 0){
                        fx += ("");
                    } else {

                        fx += (" + " + df.format(s[i]));
                        fx += ("x^" + Integer.toString(i));
                    }
                }
            }
            writeFile.write(fx);
            writeFile.newLine();
            writeFile.write("Hasil taksiran: ");
            writeFile.newLine();
            String res = "f(";
            res += df.format(taksiran[0]);
            res += ")= ";
            res += df.format(result);
            writeFile.write(res);
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
            double[][] eselon = SPL.getGauss(matrix);
            writeFile.write("Hasil Eselon Baris:");
            writeFile.newLine();
            String mat = matrixIO.matrixString(matrix);
            writeFile.write(mat);
            
            double[] hasil = matrixOP.hasilSPLGaussFile(eselon);
            if (isNoSolution(hasil) == true){
                writeFile.write("Matrix di atas tidak memiliki solusi.");
                writeFile.newLine();
            } else if (isSolutionBanyak(hasil) == true){
                writeFile.write("Matrix memiliki banyak solusi.");
                writeFile.newLine();
                String[] banyakS = SPL.parameterFile(eselon, true);
                String parametrik = "";
                for (int i = 0; i< banyakS.length ; i++){
                    parametrik += "x";
                    parametrik += Integer.toString(i+1);
                    parametrik += " = ";
                    parametrik += banyakS[i];
                    parametrik += "\n";
                }
                writeFile.write(parametrik);
            } else {
                writeFile.write("Solusi: ");
                writeFile.newLine();
                String solusi = "";
                DecimalFormat df = new DecimalFormat("0.000");
                for (int i = 0; i< hasil.length ; i++){
                    solusi += "x";
                    solusi += Integer.toString(i+1);
                    solusi += " = ";
                    solusi += df.format(hasil[i]);
                    solusi += "\n";
                }
                writeFile.write(solusi);
            }
            // hasilGausstoFile(matrix, newPath);
            writeFile.flush();
            writeFile.close();

        } catch (IOException e){
            System.out.println("Terjadi Kesalahan. Tidak bisa menyimpan file.");
            e.printStackTrace();
        }

    }
    
    public static void fileGaussJordan(double[][] matrix){
        String m = matrixIO.inputFile();
        matrixIO.createFile(m);
        String newPath = getPathOut(m);
        FileWriter write;
        try {

            write = new FileWriter(newPath);

            BufferedWriter writeFile = new BufferedWriter(write);
            writeFile.write("-----HASIL GAUSS-----");
            writeFile.newLine();
            double[][] eselon = SPL.getGauss(matrix);
            writeFile.write("Hasil Eselon Baris:");
            writeFile.newLine();
            String mat = matrixIO.matrixString(matrix);
            writeFile.write(mat);
            
            double[] hasil = matrixOP.hasilSPLGaussJordanFile(eselon);
            if (isNoSolution(hasil) == true){
                writeFile.write("Matrix di atas tidak memiliki solusi.");
                writeFile.newLine();
            } else if (isSolutionBanyak(hasil) == true){
                writeFile.write("Matrix memiliki banyak solusi.");
                writeFile.newLine();
                String[] banyakS = SPL.parameterFile(eselon, true);
                String parametrik = "";
                for (int i = 0; i< banyakS.length ; i++){
                    parametrik += "x";
                    parametrik += Integer.toString(i+1);
                    parametrik += " = ";
                    parametrik += banyakS[i];
                    parametrik += "\n";
                }
                writeFile.write(parametrik);
            } else {
                writeFile.write("Solusi: ");
                writeFile.newLine();
                String solusi = "";
                DecimalFormat df = new DecimalFormat("0.000");
                for (int i = 0; i< hasil.length ; i++){
                    solusi += "x";
                    solusi += Integer.toString(i+1);
                    solusi += " = ";
                    solusi += df.format(hasil[i]);
                    solusi += "\n";
                }
                writeFile.write(solusi);
            }
            // hasilGausstoFile(matrix, newPath);
            writeFile.flush();
            writeFile.close();

        } catch (IOException e){
            System.out.println("Terjadi Kesalahan. Tidak bisa menyimpan file.");
            e.printStackTrace();
        }
    }
    
    public static void fileRLB(double[]s,double x,double[] taksiran){
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
                    if (s[i] == 0){
                        str += "";
                    } else {
                        str += df.format(s[i]);
                    }
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
            writeFile.newLine();

            String fx = "f(";
            for (int i = 0;i<taksiran.length;i++){
                if (i == (taksiran.length-1)){
                    fx += df.format(taksiran[i]);
                    fx += ")= ";
                } else {
                    fx += df.format(taksiran[i]);
                    fx += ",";
                }
            }
            writeFile.write(fx);
            writeFile.write(df.format(x));
            writeFile.flush();
            writeFile.close();

        } catch (IOException e){
            System.out.println("Terjadi Kesalahan. Tidak bisa menyimpan file.");
            e.printStackTrace();
        }

    }

    public static void fileDeterminan(double[][] matrix, int type){
        String m = matrixIO.inputFile();
        matrixIO.createFile(m);
        String newPath = getPathOut(m);
        FileWriter write;
        try {

            write = new FileWriter(newPath);

            BufferedWriter writeFile = new BufferedWriter(write);

            String mat = matrixIO.matrixString(matrix);
            String det = "";
            if (type == 1){
                writeFile.write("-----HASIL DETERMINAN DENGAN REDUKSI BARIS-----");
                if (SPL.detReduksiBaris(matrix) != -9999){
                    det += SPL.detReduksiBaris(matrix);
                }
            } else if (type == 2) {
                writeFile.write("-----HASIL DETERMINAN DENGAN EXPANSI KOFAKTOR-----");
                if (SPL.determinan(matrix) != -9999){
                    det += SPL.determinan(matrix);
                }
            }
            writeFile.newLine();
            writeFile.write(mat);
            // writeFile.newLine();
            if (det ==""){
                writeFile.write("Matrix di atas tidak memiliki determinan.");
            } else {

                writeFile.write("Determinan untuk matrix di atas adalah: " + det);
            }
            writeFile.flush();
            writeFile.close();

        } catch (IOException e){
            System.out.println("Terjadi Kesalahan. Tidak bisa menyimpan file.");
            e.printStackTrace();
        }
    }

    public static void fileInverse(double[][] matrix){
        String m = matrixIO.inputFile();
        matrixIO.createFile(m);
        String newPath = getPathOut(m);
        FileWriter write;
        try {

            write = new FileWriter(newPath);

            BufferedWriter writeFile = new BufferedWriter(write);

            String mat = matrixIO.matrixString(matrix);
            writeFile.write("-----HASIL INVERSE MATRIX-----");
            writeFile.newLine();
            writeFile.write(mat);

            // writeFile.newLine();
            if (SPL.noInv(SPL.inverse(matrix)) == true){
                writeFile.write("Matrix di atas tidak memiliki inverse.");
            } else {
                String inverse = matrixIO.matrixString(SPL.inverse(matrix));
                writeFile.write("Inverse untuk matrix di atas adalah: " );
                writeFile.newLine();
                writeFile.write(inverse);
            }
            
            writeFile.flush();
            writeFile.close();

        } catch (IOException e){
            System.out.println("Terjadi Kesalahan. Tidak bisa menyimpan file.");
            e.printStackTrace();
        }
    }

    public static void fileBicubic(double[][] matrix , double[] taksiran){
        String m = matrixIO.inputFile();
        matrixIO.createFile(m);
        String newPath = getPathOut(m);
        FileWriter write;
        try {

            write = new FileWriter(newPath);

            BufferedWriter writeFile = new BufferedWriter(write);

            String mat = matrixIO.matrixString(matrix);
            writeFile.write("-----HASIL BICUBIC SPLINE INTERPOLATION-----");
            writeFile.newLine();
            writeFile.write(mat);

            writeFile.write("Hasil taksiran matrix di atas adalah: " );
            writeFile.newLine();
            DecimalFormat df = new DecimalFormat("0.000");
            String fx = "f(";
            for (int i = 0;i<taksiran.length;i++){
                if (i == (taksiran.length-1)){
                    fx += df.format(taksiran[i]);
                    fx += ")= ";
                } else {
                    fx += df.format(taksiran[i]);
                    fx += ",";
                }
            }
            writeFile.write(fx);
            // writeFile.newLine();
            writeFile.write(Double.toString(bicubicInterpolation.bicubicSpline(matrix, taksiran[0], taksiran[1])));
            
            writeFile.flush();
            writeFile.close();

        } catch (IOException e){
            System.out.println("Terjadi Kesalahan. Tidak bisa menyimpan file.");
            e.printStackTrace();
        }
    }
    
    public static void fileCrammer(double[][] matrix){
        String m = matrixIO.inputFile();
        matrixIO.createFile(m);
        String newPath = getPathOut(m);
        FileWriter write;
        try {

            write = new FileWriter(newPath);

            BufferedWriter writeFile = new BufferedWriter(write);

            String mat = matrixIO.matrixString(matrix);
            writeFile.write("-----HASIL SPL DENGAN KAIDAH CRAMMER-----");
            writeFile.newLine();
            writeFile.write(mat);

            writeFile.write("Solusi: " );
            writeFile.newLine();

            double[][] hasil = SPL.kaidahCramer(matrix);
            if (hasil[0][0] == -9999){
                writeFile.write("Matrix di atas tidak bisa diselesaikan dengan kaidah cramer");
            } else if (hasil[0][0] == -999){
                writeFile.write("Matrix di atas tidak memiliki solusi");
            } else if (hasil[0][0] == -99999){
                writeFile.write("Matrix di atas tidak bisa diselesaikan dengan kaidah cramer");
            } else{

                String solusi = "";
                DecimalFormat df = new DecimalFormat("0.000");
                for (int i = 0; i< hasil.length ; i++){
                    for (int j = 0;j <hasil[0].length;j++){
                        solusi += "x";
                        solusi += Integer.toString(i+1);
                        solusi += " = ";
                        solusi += df.format(hasil[i][j]);
                        solusi += "\n";
                    }
                }
                writeFile.write(solusi);
            }
            
            // writeFile.newLine();
            
            writeFile.flush();
            writeFile.close();

        } catch (IOException e){
            System.out.println("Terjadi Kesalahan. Tidak bisa menyimpan file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        String path = matrixIO.inputFile();
        double[][] mat = matrixIO.fileToMatrix(path,1);
        // fileCrammer(mat);
        if (SPL.noInv(SPL.inverse(mat))){
            System.out.println("Matriks di atas tidak memiliki invers.");
        }
        else{
            matrixIO.displayMatrix(SPL.inverse(mat));
        }
    }
}

    

