import java.util.Scanner;

class Klaviatuurilt {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta täisarv");
        int taisarv = scan.nextInt();
        System.out.println("Sisesta reaalarv");
        double ujukomaArv = scan.nextDouble();
        System.out.println("Sisestasid täisarvu: " + taisarv);
        System.out.println(" ja reaalarvu: " + ujukomaArv);
    }

}