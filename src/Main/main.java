package src.Main;
import java.util.Scanner;
import src.Matrix.*;
import src.Functions.Interpolasi;
import src.Functions.MainFunctions;
import src.Functions.regresiLinearBerganda;

public class main {
    public static Scanner scan;
    public static void main (String[] args) {
        double[][] matriks;
        // untuk interpolasi
        double[] m;
        double[] x;
        double[] s;
        int inputsave;
        String name;

        scan = new Scanner(System.in);

        System.out.println("                     KALKULATOR MATRIKS");
        System.out.println();
        System.out.println();
        System.out.println("██╗  ██╗ █████╗ ██╗   ██╗██████╗ ███████╗███╗   ██╗     ██╗██╗");
        System.out.println("██║ ██╔╝██╔══██╗╚██╗ ██╔╝██╔══██╗██╔════╝████╗  ██║     ██║██║");
        System.out.println("█████╔╝ ███████║ ╚████╔╝ ██║  ██║█████╗  ██╔██╗ ██║     ██║██║");
        System.out.println("██╔═██╗ ██╔══██║  ╚██╔╝  ██║  ██║██╔══╝  ██║╚██╗██║██   ██║██║");
        System.out.println("██║  ██╗██║  ██║   ██║   ██████╔╝███████╗██║ ╚████║╚█████╔╝██║");
        System.out.println("╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═════╝ ╚══════╝╚═╝  ╚═══╝ ╚════╝ ╚═╝");
        System.out.println("");

        String SPL = """
            1. Metode Eliminasi Gauss
            2. Metode Eliminasi Gauss-Jordan
            3. Metode Matriks Balikan
            4. Kaidah Cramer
                """;

        String determinan = """
            1. Metode Reduksi Baris
            2. Metode Ekspansi Kofaktor
                """;

        String input = """
            1. Keyboard
            2. File              
                """;

        String save = """
            1. Yes
            2. No            
                """;

        scan = new Scanner(System.in);
        int pilihan;

        do {

            System.out.println();
            System.out.println("**************************************************************");
            System.out.println("********************** MENU KALKULATOR ***********************");
            System.out.println("**************************************************************");
            System.out.println();
            System.out.println("1. Sistem Persamaan Linear");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic Spline");
            System.out.println("6. Regresi Linear Berganda");
            System.out.println("7. Keluar");
            System.out.println();
            System.out.print("Masukkan pilihan menu: ");
            
            pilihan = scan.nextInt();

            while (pilihan < 1 || pilihan > 7) {
                System.out.println("Masukan tidak valid. Mohon hanya menginput bilangan 1-7.");
                System.out.println("Masukkan pilihan menu: ");
                pilihan = scan.nextInt(); 
            }

            System.out.println();

            if (pilihan == 7) {
                System.out.println("Terima kasih telah menggunakan kalkulator. Anda akan keluar dari program.");
            }
            
            // MENU
            switch (pilihan) {
                case 1:
                    System.out.println("**************************************************************");
                    System.out.println("******************* SISTEM PERSAMAAN LINIER ******************");
                    System.out.println("**************************************************************");
                    System.out.println();
                    System.out.println("Pilih metode yang kamu inginkan untuk menyelesaikan SPL!");
                    System.out.println(SPL);
                    System.out.println("Masukkan pilihan menu: ");
                    
                    int pilihanspl = scan.nextInt();
                
                    
                    while (pilihanspl < 1 || pilihanspl > 4) {
                        System.out.println("Masukan tidak valid. Mohon hanya menginput bilangan 1-4.");
                        System.out.println("Masukkan pilihan menu: ");
                        pilihanspl = scan.nextInt();
                    }

                    System.out.println();
                    
                    switch (pilihanspl) {
                        case 1:
                            System.out.println("******************* Metode Eliminasi Gauss *******************");
                            // pilih cara input
                            System.out.println(input);
                            System.out.print("Masukan pilihan input: ");
                            int inputgauss;
                            inputgauss = scan.nextInt();  

                            while (inputgauss < 1 || inputgauss > 2) {
                                System.out.println("Pilihan input tidak tersedia, mohon hanya menginput bilangan 1 atau 2.");
                                System.out.print("Masukan pilihan input: ");
                                inputgauss = scan.nextInt();  
                            }
                            System.out.println();
                            
                            if (inputgauss == 1) {
                                System.out.println("INPUT SOURCE: KEYBOARD");
                                matriks = matrixIO.readMatrixSPL();
                                MainFunctions.GaussKeyboard(matriks);
                                System.out.println("Apakah anda ingin menyimpan jawaban? ");
                                System.out.println(save);
                                System.out.print("Masukan pilihan input: ");
                                inputsave = scan.nextInt();
                                while (inputsave < 1 || inputsave > 2) {
                                    System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                                    System.out.println("Masukkan pilihan menu: ");
                                    inputsave = scan.nextInt();
                                    
                                }
                                if (inputsave == 1) {
                                    outputFile.fileGauss(matriks);
                                    break;
                                }
                                else {
                                    continue;
                                }
                            }
                            else {
                                System.out.println("INPUT SOURCE: FILE");
                                String path = matrixIO.inputFile();
                                matriks = matrixIO.fileToMatrix(path, 1);
                                MainFunctions.GaussFile(matriks);
                                System.out.println("Apakah anda ingin menyimpan jawaban? ");
                                System.out.println(save);
                                System.out.print("Masukan pilihan input: ");
                                inputsave = scan.nextInt();
                                while (inputsave < 1 || inputsave > 2) {
                                    System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                                    System.out.println("Masukkan pilihan menu: ");
                                    inputsave = scan.nextInt();
                                    
                                }
                                if (inputsave == 1) {
                                    outputFile.fileGauss(matriks);
                                    break;
                                }
                                else {
                                    continue;
                                }
                            }
                            // break;
                        case 2:
                            System.out.println("**************** Metode Eliminasi Gauss-Jordan ***************");
                            // pilih cara input
                            System.out.println(input);
                            System.out.print("Masukan pilihan input: ");
                            int inputGJ;
                            inputGJ = scan.nextInt();  
                            while (inputGJ < 1 || inputGJ > 2) {
                                System.out.println("Pilihan input tidak tersedia, mohon hanya menginput bilangan 1 atau 2.");
                                System.out.print("Masukan pilihan input: ");
                                inputGJ = scan.nextInt();  
                            }
                            System.out.println();
                            
                            if (inputGJ == 1) {
                                System.out.println("INPUT SOURCE: KEYBOARD");
                                matriks = matrixIO.readMatrixSPL();
                                MainFunctions.GaussJordanKeyboard(matriks);
                                System.out.println("Apakah anda ingin menyimpan jawaban? ");
                                System.out.println(save);
                                System.out.print("Masukan pilihan input: ");
                                inputsave = scan.nextInt();
                                while (inputsave < 1 || inputsave > 2) {
                                    System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                                    System.out.println("Masukkan pilihan menu: ");
                                    inputsave = scan.nextInt();
                                    
                                }
                                if (inputsave == 1) {
                                    outputFile.fileGaussJordan(matriks);
                                    break;
                                }
                                else {
                                    continue;
                                }
                            }
                            else {
                                System.out.println("INPUT SOURCE: FILE");
                                String path = matrixIO.inputFile();
                                matriks = matrixIO.fileToMatrix(path, 1);
                                MainFunctions.GaussJordanFile(matriks);
                                System.out.println("Apakah anda ingin menyimpan jawaban? ");
                                System.out.println(save);
                                System.out.print("Masukan pilihan input: ");
                                inputsave = scan.nextInt();
                                while (inputsave < 1 || inputsave > 2) {
                                    System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                                    System.out.println("Masukkan pilihan menu: ");
                                    inputsave = scan.nextInt();
                                    
                                }
                                if (inputsave == 1) {
                                    outputFile.fileGaussJordan(matriks);
                                    break;
                                }
                                else {
                                    continue;
                                }
                            }
                        case 3:
                            System.out.println("******************* Metode Matriks Balikan *******************");
                            // pilih cara input
                            System.out.println(input);
                            System.out.print("Masukan pilihan input: ");
                            int inputbalikan;
                            inputbalikan = scan.nextInt();  
                            while (inputbalikan < 1 || inputbalikan > 2) {
                                System.out.println("Pilihan input tidak tersedia, mohon hanya menginput bilangan 1 atau 2.");
                                System.out.print("Masukan pilihan input: ");
                                inputbalikan = scan.nextInt();  
                            }
                            System.out.println();
                            
                            if (inputbalikan == 1) {
                                System.out.println("INPUT SOURCE: KEYBOARD");
                                MainFunctions.BalikanKeyboard();
                                System.out.println("Apakah anda ingin menyimpan jawaban? ");
                                System.out.println(save);
                                System.out.print("Masukan pilihan input: ");
                                inputsave = scan.nextInt();
                                while (inputsave < 1 || inputsave > 2) {
                                    System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                                    System.out.println("Masukkan pilihan menu: ");
                                    inputsave = scan.nextInt();
                                    
                                }
                                if (inputsave == 1) {
                                    break;
                                }
                                else {
                                    continue;
                                }
                            }
                            else {
                                System.out.println("INPUT SOURCE: FILE");
                                // BIKIN FUNCTION POTONG MATRIX UNTUK MATRIKS BALIKAN FILE
                            }
                            break;
                        case 4:
                            System.out.println("************************ Kaidah Cramer ***********************");
                            // pilih cara input
                            System.out.println(input);
                            System.out.print("Masukan pilihan input: ");
                            int inputcramer;
                            inputcramer = scan.nextInt();  
                            while (inputcramer < 1 || inputcramer > 2) {
                                System.out.println("Pilihan input tidak tersedia, mohon hanya menginput bilangan 1 atau 2.");
                                System.out.print("Masukan pilihan input: ");
                                inputcramer = scan.nextInt();  
                            }
                            System.out.println();
                            
                            if (inputcramer == 1) {
                                System.out.println("INPUT SOURCE: KEYBOARD");
                                matriks = matrixIO.readMatrixSPL();
                                MainFunctions.CramerKeyboard(matriks);
                                System.out.println("Apakah anda ingin menyimpan jawaban? ");
                                System.out.println(save);
                                System.out.print("Masukan pilihan input: ");
                                inputsave = scan.nextInt();
                                while (inputsave < 1 || inputsave > 2) {
                                    System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                                    System.out.println("Masukkan pilihan menu: ");
                                    inputsave = scan.nextInt();
                                    
                                }
                                if (inputsave == 1) {
                                    outputFile.fileCrammer(matriks);
                                    break;
                                }
                                else {
                                    continue;
                                }
                            }
                            else {
                                System.out.println("INPUT SOURCE: FILE");
                                String path = matrixIO.inputFile();
                                matriks = matrixIO.fileToMatrix(path, 1);
                                MainFunctions.CramerFile(matriks);
                                System.out.println("Apakah anda ingin menyimpan jawaban? ");
                                System.out.println(save);
                                System.out.print("Masukan pilihan input: ");
                                inputsave = scan.nextInt();
                                while (inputsave < 1 || inputsave > 2) {
                                    System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                                    System.out.println("Masukkan pilihan menu: ");
                                    inputsave = scan.nextInt();
                                    
                                }
                                if (inputsave == 1) {
                                    outputFile.fileCrammer(matriks);
                                    break;
                                }
                                else {
                                    continue;
                                }
                            }
                    }
                    break;
                case 2:
                    System.out.println("**************************************************************");
                    System.out.println("************************* DETERMINAN *************************");
                    System.out.println("**************************************************************");
                    System.out.println();
                    System.out.println("Pilih metode yang kamu inginkan untuk mencari determinan!");
                    System.out.println(determinan);
                    System.out.println("Masukkan pilihan menu: ");
                    
                    int pilihandet = scan.nextInt();

                    while (pilihandet < 1 || pilihandet > 2) {
                                System.out.println("Pilihan input tidak tersedia, mohon hanya menginput bilangan 1 atau 2.");
                                System.out.print("Masukan pilihan input: ");
                                pilihandet = scan.nextInt();  
                            }
                            System.out.println();
                    
                    switch (pilihandet) {
                        case 1:
                            System.out.println("******************** Metode Reduksi Baris ********************");
                            System.out.println(input);
                            System.out.print("Masukan pilihan input: ");
                            int inputreduksi;
                            inputreduksi = scan.nextInt();
                            while (inputreduksi < 1 || inputreduksi > 2) {
                                System.out.println("Pilihan input tidak tersedia, mohon hanya menginput bilangan 1 atau 2.");
                                System.out.print("Masukan pilihan input: ");
                                inputreduksi = scan.nextInt();  
                            }
                            System.out.println();
                            
                            if (inputreduksi == 1) {
                                System.out.println("INPUT SOURCE: KEYBOARD");
                                matriks = matrixIO.readMatrixKeyboard();
                                MainFunctions.redBarisKeyboard(matriks);
                                System.out.println("Apakah anda ingin menyimpan jawaban? ");
                                System.out.println(save);
                                System.out.print("Masukan pilihan input: ");
                                inputsave = scan.nextInt();
                                while (inputsave < 1 || inputsave > 2) {
                                    System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                                    System.out.println("Masukkan pilihan menu: ");
                                    inputsave = scan.nextInt();
                                    
                                }
                                if (inputsave == 1) {
                                    outputFile.fileDeterminan(matriks, 1);
                                    break;
                                }
                                else {
                                    continue;
                                }
                                
                            }
                            else {
                                System.out.println("INPUT SOURCE: FILE");
                                String path = matrixIO.inputFile();
                                matriks = matrixIO.fileToMatrix(path, 1);
                                MainFunctions.redBarisfile(matriks);
                                System.out.println("Apakah anda ingin menyimpan jawaban? ");
                                System.out.println(save);
                                System.out.print("Masukan pilihan input: ");
                                inputsave = scan.nextInt();
                                while (inputsave < 1 || inputsave > 2) {
                                    System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                                    System.out.println("Masukkan pilihan menu: ");
                                    inputsave = scan.nextInt();
                                    
                                }
                                if (inputsave == 1) {
                                    outputFile.fileDeterminan(matriks, 1);
                                    break;
                                }
                                else {
                                    continue;
                                }
                            }
                        case 2:
                            System.out.println("****************** Metode Ekspansi Kofaktor ******************");
                            System.out.println(input);
                            System.out.print("Masukan pilihan input: ");
                            int inputeks;
                            inputeks = scan.nextInt();
                            while (inputeks < 1 || inputeks > 2) {
                                System.out.println("Pilihan input tidak tersedia, mohon hanya menginput bilangan 1 atau 2.");
                                System.out.print("Masukan pilihan input: ");
                                inputeks = scan.nextInt();  
                            }
                            System.out.println();
                            
                            if (inputeks == 1) {
                                System.out.println("INPUT SOURCE: KEYBOARD");
                                matriks = matrixIO.readMatrixKeyboard();
                                MainFunctions.ekspansiKeyboard(matriks);
                                System.out.println("Apakah anda ingin menyimpan jawaban? ");
                                System.out.println(save);
                                System.out.print("Masukan pilihan input: ");
                                inputsave = scan.nextInt();
                                while (inputsave < 1 || inputsave > 2) {
                                    System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                                    System.out.println("Masukkan pilihan menu: ");
                                    inputsave = scan.nextInt();
                                    
                                }
                                if (inputsave == 1) {
                                    outputFile.fileDeterminan(matriks, 2);
                                    break;
                                }
                                else {
                                    continue;
                                }
                            }
                            else {
                                System.out.println("INPUT SOURCE: FILE");
                                String path = matrixIO.inputFile();
                                matriks = matrixIO.fileToMatrix(path, 1);
                                MainFunctions.ekspansiFile(matriks);
                                System.out.println("Apakah anda ingin menyimpan jawaban? ");
                                System.out.println(save);
                                System.out.print("Masukan pilihan input: ");
                                inputsave = scan.nextInt();
                                while (inputsave < 1 || inputsave > 2) {
                                    System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                                    System.out.println("Masukkan pilihan menu: ");
                                    inputsave = scan.nextInt();
                                    
                                }
                                if (inputsave == 1) {
                                    outputFile.fileDeterminan(matriks, 2);
                                    break;
                                }
                                else {
                                    continue;
                                }
                            }
                    }
                    break;
                case 3:
                    System.out.println("**************************************************************");
                    System.out.println("********************** MATRIKS BALIKAN ***********************");
                    System.out.println("**************************************************************");
                    System.out.println(input);
                    System.out.print("Masukan pilihan input: ");
                    int inputinvers;
                    inputinvers = scan.nextInt();
                    while (inputinvers < 1 || inputinvers > 2) {
                        System.out.println("Pilihan input tidak tersedia, mohon hanya menginput bilangan 1 atau 2.");
                        System.out.print("Masukan pilihan input: ");
                        inputinvers = scan.nextInt();  
                    }
                    System.out.println();
                    
                    if (inputinvers == 1) {
                        System.out.println("INPUT SOURCE: KEYBOARD");
                        matriks = matrixIO.readMatrixKeyboard();
                        MainFunctions.inverseKeyboard(matriks);
                        System.out.println("Apakah anda ingin menyimpan jawaban? ");
                        System.out.println(save);
                        System.out.print("Masukan pilihan input: ");
                        inputsave = scan.nextInt();
                        while (inputsave < 1 || inputsave > 2) {
                            System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                            System.out.println("Masukkan pilihan menu: ");
                            inputsave = scan.nextInt();
                            
                        }
                        if (inputsave == 1) {
                            outputFile.fileInverse(matriks);
                            break;
                        }
                        else {
                            continue;
                        }
                    }
                    else {
                        System.out.println("INPUT SOURCE: FILE");
                        String path = matrixIO.inputFile();
                        matriks = matrixIO.fileToMatrix(path, 1);
                        MainFunctions.inverseFile(matriks);
                        System.out.println("Apakah anda ingin menyimpan jawaban? ");
                        System.out.println(save);
                        System.out.print("Masukan pilihan input: ");
                        inputsave = scan.nextInt();
                        while (inputsave < 1 || inputsave > 2) {
                            System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                            System.out.println("Masukkan pilihan menu: ");
                            inputsave = scan.nextInt();
                            
                        }
                        if (inputsave == 1) {
                            outputFile.fileInverse(matriks);
                            break;
                        }
                        else {
                            continue;
                        }
                    }
                case 4:
                    System.out.println("**************************************************************");
                    System.out.println("******************** INTERPOLASI POLINOM *********************");
                    System.out.println("**************************************************************");
                    System.out.println(input);
                    System.out.print("Masukan pilihan input: ");
                    int inputpolinom;
                    inputpolinom = scan.nextInt();
                    while (inputpolinom < 1 || inputpolinom > 2) {
                        System.out.println("Pilihan input tidak tersedia, mohon hanya menginput bilangan 1 atau 2.");
                        System.out.print("Masukan pilihan input: ");
                        inputpolinom = scan.nextInt();  
                    }
                    System.out.println();
                    
                    if (inputpolinom == 1) {
                        System.out.println("INPUT SOURCE: KEYBOARD");
                        m = Interpolasi.solutionInterpolasi(Interpolasi.inputInterpolasi());
                        x = Interpolasi.inputTaksiran();
                        MainFunctions.InterpolasiKeyboard(m, x);
                        System.out.println("Apakah anda ingin menyimpan jawaban? ");
                        System.out.println(save);
                        System.out.print("Masukan pilihan input: ");
                        inputsave = scan.nextInt();
                        while (inputsave < 1 || inputsave > 2) {
                            System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                            System.out.println("Masukkan pilihan menu: ");
                            inputsave = scan.nextInt();
                            
                        }
                        if (inputsave == 1) {
                            // outputFile.fileInterpolasi();
                            break;
                        }
                        else {
                            continue;
                        }
                    }
                    else {
                        System.out.println("INPUT SOURCE: FILE");
                        String path = matrixIO.inputFile();
                        matriks = matrixIO.fileToMatrix(path,2);
                        x = matrixIO.getTaksiran(path);
                        MainFunctions.InterpolasiFile(matriks, x);
                        System.out.println("Apakah anda ingin menyimpan jawaban? ");
                        System.out.println(save);
                        System.out.print("Masukan pilihan input: ");
                        inputsave = scan.nextInt();
                        while (inputsave < 1 || inputsave > 2) {
                            System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                            System.out.println("Masukkan pilihan menu: ");
                            inputsave = scan.nextInt();
                            
                        }
                        if (inputsave == 1) {
                            // outputFile.fileInterpolasi();
                            break;
                        }
                        else {
                            continue;
                        }
                    }
                case 5:
                    System.out.println("**************************************************************");
                    System.out.println("**************** INTERPOLASI BICUBIC SPLINE ******************");
                    System.out.println("**************************************************************");
                    System.out.println("INPUT SOURCE: FILE");
                    String path = matrixIO.inputFile();
                    matriks = matrixIO.fileToMatrix(path, 1);
                    MainFunctions.InterpolasiSpline(matriks);
                    System.out.println("Apakah anda ingin menyimpan jawaban? ");
                    System.out.println(save);
                    System.out.print("Masukan pilihan input: ");
                    inputsave = scan.nextInt();
                    while (inputsave < 1 || inputsave > 2) {
                        System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                        System.out.println("Masukkan pilihan menu: ");
                        inputsave = scan.nextInt();
                        
                    }
                    if (inputsave == 1) {
                        // outputFile.fileBicubic();
                        break;
                    }
                    else {
                        continue;
                    }
                case 6:
                    System.out.println("**************************************************************");
                    System.out.println("******************* REGRESI LINIER BERGANDA ******************");
                    System.out.println("**************************************************************");
                    System.out.println(input);
                    System.out.print("Masukan pilihan input: ");
                    int inputrlb;
                    inputrlb = scan.nextInt();
                    while (inputrlb < 1 || inputrlb > 2) {
                        System.out.println("Pilihan input tidak tersedia, mohon hanya menginput bilangan 1 atau 2.");
                        System.out.print("Masukan pilihan input: ");
                        inputrlb = scan.nextInt();  
                    }
                    System.out.println();
                    
                    if (inputrlb == 1) {
                        System.out.println("INPUT SOURCE: KEYBOARD");
                        matriks = regresiLinearBerganda.inputReg();
                        x = regresiLinearBerganda.inputTaksiran(matriks);
                        s = regresiLinearBerganda.solutionReg(matriks);
                        MainFunctions.RegresiKeyboard(x, s);
                        System.out.println("Apakah anda ingin menyimpan jawaban? ");
                        System.out.println(save);
                        System.out.print("Masukan pilihan input: ");
                        inputsave = scan.nextInt();
                        while (inputsave < 1 || inputsave > 2) {
                            System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                            System.out.println("Masukkan pilihan menu: ");
                            inputsave = scan.nextInt();
                            
                        }
                        if (inputsave == 1) {
                            // outputFile.fileRLB();
                            break;
                        }
                        else {
                            continue;
                        }
                    }
                    else {
                        System.out.println("INPUT SOURCE: FILE");
                        String paths = matrixIO.inputFile();
                        matriks = matrixIO.fileToMatrix(paths,2);
                        x = matrixIO.getTaksiran(paths);
                        s = regresiLinearBerganda.solutionReg(matriks);
                        MainFunctions.RegresiFile(x, s);
                         System.out.println("Apakah anda ingin menyimpan jawaban? ");
                        System.out.println(save);
                        System.out.print("Masukan pilihan input: ");
                        inputsave = scan.nextInt();
                        while (inputsave < 1 || inputsave > 2) {
                            System.out.println("Masukan tidak valid. Mohon hanya menginput Y atau N.");
                            System.out.println("Masukkan pilihan menu: ");
                            inputsave = scan.nextInt();
                            
                        }
                        if (inputsave == 1) {
                            // outputFile.fileRLB();
                            break;
                        }
                        else {
                            continue;
                        }
                    } 
            }

        } 
        while (pilihan < 7);
        
        scan.close();
    }
}
