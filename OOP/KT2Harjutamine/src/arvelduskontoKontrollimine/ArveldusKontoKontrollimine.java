package arvelduskontoKontrollimine;

import java.util.Scanner;

class ArveldusKontoKontrollimine{
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            Konto konto1 = createAccount(sc);
            boolean end = false;
            while(!end){
                System.out.print("Kas panete juurde (+), ostate (-) või lõpetate? (X)?: ");
                String choice = sc.nextLine();
                switch(choice){
                    case "+":
                        increaseBalance(konto1, sc);
                        break;
                    case "-":
                        decreaseBalance(konto1, sc);
                        break;
                    case "X":
                        System.out.println("Töö lõpp.");
                        end = true;
                        break;
                }

            }
        }
    }

    private static Konto createAccount(Scanner sc) {
        Konto konto1 = null;
        boolean loodud = false;
        while(!loodud){

            System.out.println("Konto loomine:");

            System.out.print("\tSisestage konto omanik: ");
            String accountOwnerName = sc.nextLine();

            System.out.print("\tSisestage kontol olev summa: ");
            double accountBalance = Double.parseDouble(sc.nextLine());

            try {
                konto1 = new Konto(accountOwnerName, accountBalance);
                loodud = true;
                System.out.println("Konto loodud!\n");
            }catch (NegatiivneSummaErind e){
                System.out.println("Vigased andmed: kontol olev raha ei saa olla " + e.getIncorrectValue());
            }
        }
        return konto1;
    }

    private static void decreaseBalance(Konto konto1, Scanner sc) {
        while(true) {
            System.out.print("Kui suure summa eest ostate?: ");
            try {
                konto1.osta(Double.parseDouble(sc.nextLine()));
                break;
            } catch (RahaEiJätkuErind e) {
                System.out.println("Raha ei jätku! Kontol on " + e.getInsuficientBalance());
            }
        }
        System.out.println("Ost oli edukas!\n");
    }

    private static void increaseBalance(Konto konto1, Scanner sc) {
        System.out.print("Palju juurde panete?: ");
        konto1.paneJuurde(Double.parseDouble(sc.nextLine()));
        System.out.println("Kontole lisamine oli edukas!\n");
    }

}
