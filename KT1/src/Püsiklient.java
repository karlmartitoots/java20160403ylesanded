public class Püsiklient extends Klient {

    private int discount;

    Püsiklient(String initName, int initDiscount) {
        super(initName);
        this.discount = initDiscount;
    }

    @Override
    public void suurendaArvet(double extraBill){
        this.bill += extraBill * (1 - this.discount/100);
    }



}
