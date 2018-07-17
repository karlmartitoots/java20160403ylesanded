
public class LõbustavKloun implements Lõbustus{

    Kloun kloun;

    LõbustavKloun(Kloun initKloun){
        this.kloun = initKloun;
    }

    public void lõbusta(Külastaja külastaja){
        this.kloun.esine(külastaja);
    }

}
