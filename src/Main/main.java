package src.Main;
import java.util.Scanner;

public class main {
    public static Scanner scan;
    public static void main (String[] args) {

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
        System.out.println("***************************************************");
        System.out.println("***************** MENU KALKULATOR *****************");
        System.out.println("***************************************************");
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
            }

            switch (pilihan) {
                case 1:
                    System.out.println("*********************************");
                    System.out.println("**** SISTEM PERSAMAAN LINIER ****");
                    System.out.println("*********************************");
                    // bikin menu di file SPL utk apa" aja SPLnya
                    break;
                case 2:
                    System.out.println("*********************************");
                    System.out.println("*********** DETERMINAN **********");
                    System.out.println("*********************************");
                    break;
                case 3:
                    System.out.println("*********************************");
                    System.out.println("******** MATRIKS BALIKAN ********");
                    System.out.println("*********************************");
                    break;
                case 4:
                    System.out.println("*********************************");
                    System.out.println("****** INTERPOLASI POLINOM ******");
                    System.out.println("*********************************");
                    break;
                case 5:
                    System.out.println("*********************************");
                    System.out.println("****** INTERPOLASI BICUBIC ******");
                    System.out.println("*********************************");
                    break;
                case 6:
                    System.out.println("*********************************");
                    System.out.println("**** REGRESI LINIER BERGANDA ****");
                    System.out.println("*********************************");
                    break;
            }
        }
        
    }
}