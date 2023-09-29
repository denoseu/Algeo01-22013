package src.Matrix;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class matrixIO {
    /*------ INPUT ------- */
    // Membaca Matrix dari Keyboard untuk SPL
    public static Scanner scan;
    public static double[][] readMatrixSPL() {
        scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris: "); int row = scan.nextInt();
        System.out.print("Masukkan jumlah kolom: "); int col = scan.nextInt();
        // bikin matrix uk. row x col (A)
        double[][] a = new double[row][col];
        // isi matrix
        System.out.println("Elemen matriks A: ");
        for(int i = 0;i < row;i++) {
            for(int j = 0;j < col;j++) {
                a[i][j] = scan.nextDouble();
            }
        }

        // bikin matrix uk. row A x 1 col (B)
        double[][] b = new double[matrixOP.getRow(a)][1];
        // isi opmatrixOP
        System.out.println("Elemen matriks B: ");
        for(int p = 0; p < matrixOP.getRow(a); p++) {
            for(int q = 0; q < 1; q++) {
                b[p][q] = scan.nextDouble();
            }
        }

        // menggabungkan matriks A dan B
        
        int numRows1 = matrixOP.getRow(a);
        int numCols1 = matrixOP.getCol(a);
        int numCols2 = matrixOP.getCol(b);

        double[][] mergedMatrix = new double[numRows1][numCols1 + numCols2];

        for (int i = 0; i < numRows1; i++) {
            System.arraycopy(a[i], 0, mergedMatrix[i], 0, numCols1);
            System.arraycopy(b[i], 0, mergedMatrix[i], numCols1, numCols2);
        }

        return mergedMatrix;
    }
    
    // Membaca matrix dari keyboard
    public static double[][] readMatrixKeyboard() {
        scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris: "); int row = scan.nextInt();
        System.out.print("Masukkan jumlah kolom: "); int col = scan.nextInt();
        System.out.println("Masukkan elemen matriks: ");
        double[][] a = new double[row][col];
        for(int i = 0;i < row;i++) {
            for(int j = 0;j < col;j++) {
                a[i][j] = scan.nextDouble();
            }
        }
        return a;
    }
    // Membaca Matrix dari Keyboard untuk determinan dan inverse
    public static double[][] readMatrixDet(){
        double[][] m;
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan n: "); int row = scan.nextInt();
        scan.close();
        System.out.println("Silakan masukan isi matrix");
        System.out.println("Elemen matriks: ");
        m = readMatrixKeyboard();
        return m;
    }
    
    // Meminta name file txt
    public static String inputFile(){
        Scanner nameSc = new Scanner(System.in);
        System.out.print("Masukkan nama file input lengkap dengan .txt: ");
        String name = nameSc.nextLine();
        return name;
    }
    // Membaca Matrix dari File
    public static double[][] fileToMatrix(String path, int type){
        String name = path;

        String newPath;
        String currentPath = System.getProperty("user.dir");
        if (currentPath.contains("src\\")){
            String sub[] = currentPath.split("src");
            currentPath = sub[0];

            newPath = currentPath + "test/input/" + name;
            System.out.println(currentPath);
        } else {
            newPath = currentPath + "/test/input/" + name;
        }
        try{
            
            int[] count = new int [2];
            // Membuat matrix
            if (type == 1){
                count = countRowCol(newPath);

            } else if (type == 2){
                count = countRowColSC(newPath);
            }
            
            double[][] matrix = new double[count[0]][count[1]];

            //Mengisi matrix
            File file = new File(newPath);
            Scanner fReader = new Scanner(file);
            int i = 0;
            int j = 0;

            while(fReader.hasNextLine() && i<count[0] && j<count[1]){
                String[] el = (fReader.nextLine()).split("\\s+");
                for (int s=0; s<el.length;s++){
                    matrix[i][j] = Double.parseDouble(el[s]);
                    j+=1;
                }
                j = 0;
                i +=1;
            }
            fReader.close();
            return matrix;

        } catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan. Telah terjadi kesalahan");
            System.out.println("Sistem akan mengeluarkan matrix 1x1 kosong");
            double[][] matrix = new double[1][1];
            matrix[0][0] = 0;
            return matrix;

        }

    }

    // Mendapatkan nilai ab dari file untuk bicubic spline interpolation
    public static double[] getTaksiran(String path){
        String name = path;
        String newPath;
        String currentPath = System.getProperty("user.dir");
        if (currentPath.contains("src\\")){
            String sub[] = currentPath.split("src");
            currentPath = sub[0];

            newPath = currentPath + "test/input/" + name;
            System.out.println(currentPath);
        } else {
            newPath = currentPath + "/test/input/" + name;
        }
        int m = getCol(newPath);
        double[] a = new double[m];
        try{
            BufferedReader fileReader = new BufferedReader(new FileReader(newPath));
            
            // Mencari nCol dan nRow
            int lineCount = countLine(newPath);
            int currentLine = 0;
            while ((fileReader.readLine()) != null){
                currentLine++;
                
                if (currentLine == (lineCount-1)){
                    String[] temp = (fileReader.readLine()).split("\\s+");
                    for (int i =0;i<a.length;i++){
                        a[i] = Double.parseDouble(temp[i]);
                    }
                }
                
            }
            fileReader.close();

            return a;

        } catch (IOException e){
            System.out.println("File tidak ditemukan. Terjadi kesalahan.");
            return a;
        }


    }
    // Mendapatkan banyak nCol dari file;
    public static int getCol(String path){
        int count = 0;
        try{
            BufferedReader fileReader = new BufferedReader(new FileReader(path));

            // Mencari nCol dan nRow
            int row = (countLine(path));
            int col = 0;
            int currentLine = 0;
            while ((fileReader.readLine()) != null){
                currentLine++;
                if (currentLine == (row-1)){
                    col = (fileReader.readLine()).split("\\s+").length;
                }
            }
            count = col;
            fileReader.close();
            

            return count;

        } catch (IOException e){
            System.out.println("File tidak ditemukan. Terjadi kesalahan.");

            return count;
        }

    }
    // Mendapatkan banyak nRow dan nCol dari file
    public static int[] countRowCol(String path){
        int[] count = new int[2];

        try{
            BufferedReader fileReader = new BufferedReader(new FileReader(path));

            // Mencari nCol dan nRow
            int row = (countLine(path));
            int col = 0;
            int currentLine = 0;
            while ((fileReader.readLine()) != null){
                currentLine++;
                if (currentLine == (row-2)){
                    col = (fileReader.readLine()).split("\\s+").length;
                }
            }
            fileReader.close();
            
            count[0] = row;
            count[1] = col;

            return count;

        } catch (IOException e){
            System.out.println("File tidak ditemukan. Terjadi kesalahan.");
            count[0] = 0;
            count[1] = 0;

            return count;
        }
    }

    // Mendapatkan banyak nRow dan nCol dari file yang memiliki taksiran
    public static int[] countRowColSC(String path){
        int[] count = new int[2];

        try{
            BufferedReader fileReader = new BufferedReader(new FileReader(path));

            // Mencari nCol dan nRow
            int row = (countLine(path) - 1);
            int col = 0;
            int currentLine = 0;
            while ((fileReader.readLine()) != null){
                currentLine++;

                if (currentLine == (row-1)){
                    col = (fileReader.readLine()).split("\\s+").length;
                }
                
            }
            fileReader.close();
            
            count[0] = row;
            count[1] = col;

            return count;

        } catch (IOException e){
            System.out.println("File tidak ditemukan. Terjadi kesalahan.");
            count[0] = 0;
            count[1] = 0;

            return count;
        }

    }

    // Menghitung jumalah baris dalam file
    public static int countLine(String path){
        // Mencari jumlah baris
        int lineCount = 0;
        try{
            BufferedReader fileReader = new BufferedReader(new FileReader(path));
            
            while (fileReader.readLine() != null){
                lineCount ++;
            }
            fileReader.close();

            return lineCount;

        } catch (IOException e){
            System.out.println("File tidak ditemukan. Terjadi kesalahan.");

            return lineCount;
        }
    }

    

    /*------OUTPUT------- */
    // Menampilkan matrix pada layar
    // matrix buat access row, matrix[] buat access col
    public static void displayMatrix (double[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (j < m[i].length-1) {
                    System.out.print(m[i][j] + " ");
                }
                else {
                    System.out.println(m[i][j]);
                }
            }
        }
    }

    // Membuat file baru
    public static void createFile(String path){
        // User input nama file
        String name = path;

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
        System.out.println(newPath);
        //File handling
        File file = new File(newPath);

        if (file.exists()){
            file.delete();
        } 

        try {
            file.createNewFile();
        } catch (IOException e){
            System.out.println("An error occured. Cannot create new file.");
            e.printStackTrace();
        }

    }

    //Mengubah matrix ke string
    public static String matrixString(double[][] m){
        String text ="";
        for (int i=0; i<m.length;i++){
            for (int j=0;j<m[i].length;j++){
                if (j!= m[i].length -1){
                    text += m[i][j] + " ";
                } 
                else {
                    text += m[i][j] + "\n";
                }
            }
        }

        return text;
    }
    
    public static void main(String[] args){
        // System.out.println(countLine("./test/input/text.txt"));
        // int [] rc = countRowCol("./test/input/text.txt");
        // for (int i=0;i<rc.length;i++){
        //     System.out.println(rc[i]);
        // }
        String path = inputFile();
        // double[][] m = fileToMatrix(path,2);
        // displayMatrix(m);
        // // System.out.println(getAB(path));
        // double[] s = getAB(path);
        // for (int i=0;i<s.length;i++){
        //     System.out.println(s[i]);
        // }
        // System.out.println(getAB("./test/input/text.txt"));
        createFile(path);
        

    }
}