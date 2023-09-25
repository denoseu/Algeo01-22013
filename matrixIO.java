import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class matrixIO {

    // Membaca Matrix dari Keyboard
    public static Scanner scan;
    public static double[][] readMatrixKeyboard() {
        scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris: "); int row = scan.nextInt();
        System.out.print("Masukkan jumlah kolom: "); int col = scan.nextInt();
        // bikin matrix uk. row x col
        double[][] m = new double[row][col];
        // isi matrix
        System.out.println("Elemen matriks: ");
        for(int i = 0;i < row;i++) {
            for(int j = 0;j < col;j++) {
                m[i][j] = scan.nextDouble();
            }
        }

        return m;
    }

    // Membaca Matrix dari File
    public static double[][] fileToMatrix(){
        Scanner nameSc = new Scanner(System.in);
        System.out.println("Masukkan nama file: ");
        String name = nameSc.nextLine();
        nameSc.close();

        String path = "./test/" +name + ".txt";
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
            matrix[1][1] = 0;
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
    

}
