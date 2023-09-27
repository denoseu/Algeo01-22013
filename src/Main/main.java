package src.Main;
import java.util.Scanner;

import src.Funtions.eliminasiGauss;
import src.Funtions.eliminasiGaussJordan;

public class main {
    public static Scanner scan;
    public static void main (String[] args) {

        String SPL = """
            1. Metode Eliminasi Gauss
            2. Metode Eliminasi Gauss-Jordan
            3. Metode Cramer
            4. Metode Matriks Balikan
                """;

        String determinan = """
            1. Metode reduksi baris
            2. Metode ekspansi kofaktor
                """;
        
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
        
        int pilihan = scan.nextInt();
        
        boolean play = true;

        while (play) {

            while (pilihan < 1 || pilihan > 7) {
                System.out.println("Masukan tidak valid. Mohon hanya menginput bilangan 1-7.");
                System.out.println("Masukkan pilihan menu: ");
                pilihan = scan.nextInt();
            }
            
            System.out.println();

            if (pilihan == 7) {
                System.out.println("Terima kasih telah menggunakan kalkulator. Keluar dari program ...");
                scan.close();
                play = false;
            }

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

                    switch (pilihanspl) {
                        case 1:
                            eliminasiGauss.main();
                            scan.close();
                        case 2:
                            eliminasiGaussJordan.main();
                            scan.close();
                        case 3:
                            scan.close(); // nanti
                        case 4:
                            scan.close(); // nanti
                    }

                    play = false;
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
                        System.out.println("Masukan tidak valid. Mohon hanya menginput bilangan 1-2.");
                        System.out.println("Masukkan pilihan menu: ");
                        pilihanspl = scan.nextInt();
                    }

                    switch (pilihandet) {
                        case 1:
                            scan.close(); // nanti
                        case 2:
                            scan.close(); // nanti
                    }

                    play = false;
                    break;
                case 3:
                    System.out.println("**************************************************************");
                    System.out.println("********************** MATRIKS BALIKAN ***********************");
                    System.out.println("**************************************************************");
                    play = false;
                    break;
                case 4:
                    System.out.println("**************************************************************");
                    System.out.println("******************** INTERPOLASI POLINOM *********************");
                    System.out.println("**************************************************************");
                    play = false;
                    break;
                case 5:
                    System.out.println("**************************************************************");
                    System.out.println("******************** INTERPOLASI BICUBIC *********************");
                    System.out.println("**************************************************************");
                    play = false;
                    break;
                case 6:
                    System.out.println("**************************************************************");
                    System.out.println("******************* REGRESI LINIER BERGANDA ******************");
                    System.out.println("**************************************************************");
                    play = false;
                    break;
            }
        }
        
    }
}