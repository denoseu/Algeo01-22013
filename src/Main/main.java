package src.Main;
import java.util.Scanner;

import src.Functions.eliminasiGauss;
import src.Functions.eliminasiGaussJordan;
import src.Functions.kaidahCramer;
import src.Functions.matriksBalikan;

public class Main {
    public static Scanner scan;
    
    public static void main (String[] args) {

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
            System.out.println("5. Interpolasi Bicubic");
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
                                eliminasiGauss.keyboard();
                            }
                            else {
                                System.out.println("INPUT SOURCE: FILE");
                                eliminasiGauss.file();
                            }
                            break;
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
                                eliminasiGaussJordan.main();
                            }
                            else {
                                System.out.println("INPUT SOURCE: FILE");
                            }
                            break;
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
                                matriksBalikan.main();
                            }
                            else {
                                System.out.println("INPUT SOURCE: FILE");
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
                                kaidahCramer.main();
                            }
                            else {
                                System.out.println("INPUT SOURCE: FILE");
                            }
                            break;
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
                            break; // nanti
                        case 2:
                            System.out.println("****************** Metode Ekspansi Kofaktor ******************");
                            break; // nanti
                    }
                    break;
                case 3:
                    System.out.println("**************************************************************");
                    System.out.println("********************** MATRIKS BALIKAN ***********************");
                    System.out.println("**************************************************************");
                    break;
                case 4:
                    System.out.println("**************************************************************");
                    System.out.println("******************** INTERPOLASI POLINOM *********************");
                    System.out.println("**************************************************************");
                    break;
                case 5:
                    System.out.println("**************************************************************");
                    System.out.println("******************** INTERPOLASI BICUBIC *********************");
                    System.out.println("**************************************************************");
                    break;
                case 6:
                    System.out.println("**************************************************************");
                    System.out.println("******************* REGRESI LINIER BERGANDA ******************");
                    System.out.println("**************************************************************");
                    break; 
            }

        } while (pilihan < 7);
        scan.close();
    }
}
