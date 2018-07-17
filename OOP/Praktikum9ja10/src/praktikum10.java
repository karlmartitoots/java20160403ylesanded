import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Karl on 10.03.2016.
 */
public class praktikum10 {

    public static void main(String[] args) {

        Runnable r = () -> {
            System.out.println("");
        };
        //dump threadist saab vaadata mida threadid teevad
        //kaht sama lõime ei saa korraga startida

        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        threadPool.submit(r);
        threadPool.shutdown();//muidu ei jää programm seisma

    }

}
