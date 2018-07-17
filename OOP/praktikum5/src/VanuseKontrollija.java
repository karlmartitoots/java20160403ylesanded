
public class VanuseKontrollija implements Lõbustus{

    int nõutudVanus;
    Lõbustus delegaat;

    VanuseKontrollija(int initNõutudVanus, Lõbustus initDelegaat){
        this.nõutudVanus = initNõutudVanus;
        this.delegaat = initDelegaat;
    }

    public void lõbusta(Külastaja külastaja){
        if(külastaja.getVanus() < nõutudVanus){
            külastaja.lisaKirjeldus("külastaja ei läbinud vanusekontrolli");
        } else {
            this.delegaat.lõbusta(külastaja);
        }
    }

}
