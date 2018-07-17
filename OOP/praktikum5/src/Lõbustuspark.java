
import java.util.List;

public class Lõbustuspark {

    List<Lõbustus> lõbustused;

    Lõbustuspark(List<Lõbustus> initLõbustus){
        this.lõbustused = initLõbustus;
    }

    public void alustaSeiklust(Külastaja külastaja){
        System.out.println("alustan seiklust");
        for (Lõbustus lõbustus : lõbustused) {
            lõbustus.lõbusta(külastaja);
            System.out.println("lõbutsetud on summa eest: " + külastaja.kuludeSumma());
        }
        System.out.println(külastaja.kõikKirjeldused());
    }

}
