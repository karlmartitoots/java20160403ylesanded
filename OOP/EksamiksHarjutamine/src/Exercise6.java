import java.util.Random;
public class Exercise6 extends Random {
    double kallutaja;

    Exercise6(int protsente){
        kallutaja = protsente/100.0;
    }

    public boolean nextBoolean(){
        if (nextDouble() > kallutaja) return true;
        return false;
    }
}


