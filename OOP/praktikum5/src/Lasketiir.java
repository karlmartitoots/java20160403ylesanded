
import static java.lang.Math.round;

public class Lasketiir implements Lõbustus{

    public void lõbusta(Külastaja külastaja){
        double randomNumber = Math.random()*20;
        külastaja.lisaKirjeldus("tabasin lasketiirus " + round(randomNumber) + " sihtmärki");
    }


}
