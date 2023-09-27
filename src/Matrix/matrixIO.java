package src.Matrix;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    public static double[][] readMatrixKeyboard(int n, int m){
        Scanner scan = new Scanner(System.in);
        double[][] a = new double[n][m];
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < m;j++) {
                a[i][j] = scan.nextDouble();
            }
        }
        scan.close();
        return a;
    }
    // Membaca Matrix dari Keyboard untuk determinan dan inverse
    public static double[][] readMatrixDet(){
        double[][] m;
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukkan n: "); int row = scan.nextInt();
        scan.close();
        System.out.println("Silakan masukan isi matrix");
        System.out.println("Elemen matriks: ");
        m = readMatrixKeyboard(row, row);
        return m;
    }

    // Membaca Matrix dari Keyboard untuk interpolasi
    public static double[][] readMatrixInterpolasi(){
        double[][] m;
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukkan n: "); int row = scan.nextInt();
        scan.close();
        System.out.println("Silakan masukan isi matrix");
        System.out.println("Elemen matriks: ");
        m = readMatrixKeyboard(row, 2);
        return m;
    }

    // Membaca Matrix dari Keyboard untuk regresi linear
    

    // Membaca Matrix dari File
    public static double[][] fileToMatrix(){
        Scanner nameSc = new Scanner(System.in);
        System.out.println("Masukkan nama file input lengkap dengan .txt: ");
        String name = nameSc.nextLine();
        nameSc.close();

        String path = "./test/input/" +name;
        try{
            
            // Membuat matrix
            int[] count = countRowCol(path);
            double[][] matrix = new double[count[0]][count[1]];

            //Mengisi matrix
            File file = new File(path);
            Scanner fReader = new Scanner(file);
            int i=0;
            int j = 0;

            while(fReader.hasNextLine() && i<count[0] & j<count[1]){
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
    
    // Mendapatkan banyak nRow dan nCol dari file
    public static int[] countRowCol(String path){
        int[] count = new int[2];

        try{
            File file = new File(path);
            Scanner fileReader = new Scanner(file);

            // Mencari nCol dan nRow
            int row = 0;
            int col = 0;
            
            while (fileReader.hasNextLine()){
                col = (fileReader.nextLine()).split("\\s+").length;
                row +=1;
            }
            fileReader.close();
            
            count[0] = row;
            count[1] = col;

            return count;

        } catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan. Terjadi kesalahan.");
            count[0] = 0;
            count[1] = 0;

            return count;
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
    public static void createFile(){
        // User input nama file
        Scanner nameSc = new Scanner(System.in);
        System.out.println("Masukkan nama file output lengkap dengan .txt: ");
        String name = nameSc.nextLine();
        nameSc.close();

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
                } else {
                    text += m[i][j] + "\n";
                }
            }
        }

        return text;
    }
    

}