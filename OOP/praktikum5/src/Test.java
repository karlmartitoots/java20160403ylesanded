
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args){

        Lõbustus vaateratas = new Vaateratas();
        Lõbustus lasketiir = new Lasketiir();
        Kloun kloun = new Kloun("arlekiin");
        Lõbustus lõbustavKloun = new LõbustavKloun(kloun);
        TasulineLõbustus tasulineVaateratas = new TasulineLõbustus(2.25, vaateratas);
        TasulineLõbustus tasulineLasketiir = new TasulineLõbustus(1.5, lasketiir);
        VanuseKontrollija lasketiiruVanuseKontrollija = new VanuseKontrollija(10, tasulineLasketiir);
        //kui TasulineLõbustus delegeeriks VanuseKontrollijale, lisanduks külastajale kulu, kuid lõbustada ta ei saaks

        List<Lõbustus> lõbustusteList = new ArrayList<Lõbustus>();
        lõbustusteList.add(tasulineVaateratas);
        lõbustusteList.add(lasketiiruVanuseKontrollija);
        lõbustusteList.add(lõbustavKloun);

        Lõbustuspark lõbustuspark = new Lõbustuspark(lõbustusteList);
        Külastaja külastaja1 = new Külastaja(9);
        Külastaja külastaja2 = new Külastaja(11);

        lõbustuspark.alustaSeiklust(külastaja1);
        lõbustuspark.alustaSeiklust(külastaja2);


    }

}
