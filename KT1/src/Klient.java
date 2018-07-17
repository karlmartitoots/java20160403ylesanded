public class Klient implements Comparable<Klient> {

    protected String name;
    protected double bill;

    Klient(String initName){
        this.name = initName;
    }

    public void suurendaArvet(double extraBill){
        this.bill += extraBill;
    }

    public String toString(){
        return "\nKlient: " + name +
                "\nMaksmisele kuuluv summa: " + bill;
    }

    @Override
    public int compareTo(Klient o) {
        if(this.bill != o.bill){
            return (this.bill >= o.bill) ? -1 : 1;
        } else {
            return 0;
        }
    }
}
