class TrükiD {

    TrükiD() {
        System.out.print("d");
    }
    TrükiD(boolean suur) {
        if (suur) {
            System.out.print("D");
        }
    }
}
class TrükiE extends TrükiD {
    TrükiE() {
        //kuna extendib trükid, siis tuleb siia super() ja trükitakse 'd'
        System.out.print("e");
    }
    TrükiE(boolean suur){
        super(suur);//if suur siis DE, if not, siis mitte midagi
        if (suur) {
            System.out.print("E");
        }
    }
}
class TrükiF extends TrükiE {
    TrükiF(boolean suur) {
        //super() ... if suur siis deF, if not siis de.
        if (suur) {
            System.out.print("F");
        }
    }
}
    //      korrektseid.
//    Ülesandeid käsitletakse üksteisest eraldiseisvatena. Näiteks üks vigane isendiloome ei takista
public class Exercise5 {

    public static void main(String[] args) {
        TrükiF trükif = new TrükiF(true);
        System.out.println();
        TrükiE trükiE = new TrükiE(true);
        System.out.println();
        TrükiD trükiD = new TrükiF(false);
        System.out.println();
    }
}
//    Ülesanne 5.1
//  Mis ilmub ekraanile, kui klassi TrükiF isend luuakse new TrükiF(true); abil? Selgitada.
/*
trükitakse deF
 */
//        Ülesanne 5.2
//      Mis ilmub ekraanile, kui klassi TrükiE isend luuakse new TrükiE(true); abil? Selgitada.
/*
trükitakse DE
 */
//    Ülesanne 5.3
//  Mis ilmub ekraanile, kui klassi TrükiD isend luuakse new TrükiF(false); abil? Selgitada.
/*
trükitakse de
 */