import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Exercise10 {
}

class Inimene {
    String nimi;
    Inimene eelmine;
    public Inimene(String nimi) {
        this.nimi = nimi;
    }
    public String toString() {
        return nimi;
    }
}

class KassaJärjekord {
    private Inimene algus;
    private Inimene lõpp;
    private int nr = 1;
    KassaJärjekord(){}
    KassaJärjekord(KassaJärjekord k){
        algus = k.algus;
        lõpp = k.lõpp;
        nr = k.nr;
    }
    void lisa(Inimene i){
        if (algus == null){
            algus = i;
            lõpp = algus;
            System.out.println("Esimene kassajärjekorras: " + i);
        }
        else { if (nr % 3 == 0)
            System.out.println("Läks teise kassa juurde: " + i);
            else {
            i.eelmine = lõpp;
            lõpp = i;
            System.out.println("Järgmisena tulnud: " + i);
        }
        }
        nr++;
    }
    public String toString(){
        StringBuilder välja = new StringBuilder("Kassajärjekord: ");
        Inimene i = lõpp;
        while (i != null) {
            välja = välja.append(i + " -> ");
            i = i.eelmine;
        }
        return välja.substring(0, välja.length()-3);
    }
    Inimene meetod(Inimene i){
        //Mart -> Jevgeni -> Kadri -> Margus
        Inimene i1 = lõpp;
        //i1 == Mart
        i.eelmine = lõpp.eelmine.eelmine.eelmine;
        //Luisa.eelmine = Margus
        lõpp.eelmine.eelmine.eelmine = i;
        //i = Luisa
        //Kadri eelmine oli Margus, nüüd on Luisa
        //Mart -> Jevgeni -> Kadri -> Luisa -> Margus
        lõpp = lõpp.eelmine;
        //lõpp.eelmine = Jevgeni
        //lõpp on nüüd Jevgeni, tagastatakse Jevgeni
        //Jevgeni -> Kadri -> Luisa -> Margus
        return i1;
    }
}

//Peaklass on järgmine.
class KassaPeaklass {
    public static void main(String[] args) {
        Inimene i1 = new Inimene("Margus");
        Inimene i2 = new Inimene("Kadri");
        Inimene i3 = new Inimene("Taavi");
        Inimene i4 = new Inimene("Jevgeni");
        List<Inimene> inimesed = new ArrayList<Inimene>(Arrays.asList(i1, i2, i3,
                i4));
        KassaJärjekord k = new KassaJärjekord();
        for (Inimene i : inimesed)
            k.lisa(i);
        System.out.println(k);
        System.out.println("==========");
        KassaJärjekord k2 = new KassaJärjekord(k);
        k2.lisa(new Inimene("Mart"));
        k2.lisa(new Inimene("Andres"));
        System.out.println(k2);
        System.out.println("==========");
        System.out.println(k.meetod(new Inimene("Luisa")));
        System.out.println(k);
    }
}

//   Mis väljastatakse ekraanile? Selgitada!
/*
Esimene kassajärjekorras: Margus
Järgmisena tulnud: Kadri
Läks teise kassa juurde: Taavi
Järgmisena tulnud: Jevgeni
Kassajärjekord: Jevgeni -> Kadri -> Margus
==========
Järgmisena tulnud: Mart
Läks teise kassa juurde: Andres
Kassajärjekord: Mart -> Jevgeni -> Kadri -> Margus
==========
Jevgeni
Kassajärjekord: Jevgeni -> Kadri -> Luisa -> Mar
 */