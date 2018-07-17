public class Exercise7 {
}

interface Elatav {
    public int elanikeArv();
}

interface Mõõdetav { //6. lünk
    public int kõrgus();
}

//Hoone.java:
abstract class Hoone {
    public abstract String hooneOmanik(); //4. lünk
    public String toString() { //5. lünk
        return "Hoone - omanik: " + hooneOmanik();
    }
}

//Kortermaja.java:
class Kortermaja extends Hoone implements Elatav, Mõõdetav {
    //1., 2., 3. lünk
    private static int maksimumKõrgus;
    private int korteriteArv;

    @Override
    public int elanikeArv() {
        return 0;
    }

    @Override
    public String hooneOmanik() {
        return null;
    }

    @Override
    public int kõrgus() {
        return 0;
    }
}

//        Täita lüngad järgnevates lausetes. 4. ja 5. lauses valida sobiv sõna sulgudest.
//        1. Klass Kortermaja EXTENDS Hoone ___________.
//        2. Klass Kortermaja IMPLEMENTS Elatav ___________.
//        3. Klass Kortermaja IMPLEMENTS Mõõdetav ___________.
//        4. Muutuja maksimumKõrgus on klassi Kortermaja KLASSIMUUTUJA.
//        (isendimuutuja/klassimuutuja)
//        5. Muutuja korteriteArv on klassi Kortermaja ISENDIMUUTUJA.
//        (isendimuutuja/klassimuutuja)
//        6. Selleks, et kood kompileeruks, peavad klassis Kortermaja olema vähemalt meetodid
//        hooneOmanik(){}, elanikeArv(){}, kõrgus(){}