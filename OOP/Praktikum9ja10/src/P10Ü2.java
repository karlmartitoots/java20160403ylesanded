import java.util.ArrayList;
import java.util.List;

public class P10Ü2 {

    public static void main(String[] args) {

        Object monitor = new Object();
        List<Integer> arvud = new ArrayList<>();
        Runnable r1 = () -> {
            {
                for (int i = 0; i < 100000; i++) {
                    arvud.add(i);
                }
            }
        };

        new Thread(r1).start();
        new Thread(r1).start();

    }

}
