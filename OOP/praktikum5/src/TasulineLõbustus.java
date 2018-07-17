
public class TasulineLõbustus implements Lõbustus {

    double hind;
    Lõbustus delegaat;

    TasulineLõbustus(double initHind, Lõbustus initDelegaat){
        this.hind = initHind;
        this.delegaat = initDelegaat;
    }

    public void lõbusta(Külastaja külastaja){
        this.delegaat.lõbusta(külastaja);
        külastaja.lisaKulu(this.hind);
        külastaja.lisaKirjeldus("tasusin külastuse eest " + this.hind);
    }

}
