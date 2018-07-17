import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Exercise9 {
}

class A implements Comparable<A> {
    private int a;
    public A(int a) {
        this.a = a;
    }
    public int compareTo(A a) {
        return this.a - a.a;
    }
    public String toString() {
        return Integer.toString(a);
    }
}

class B implements Comparable<B> {
    private int b;
    public B(int b) {
        this.b = b;
    }
    public int compareTo(B b) {
        return b.b - this.b;
    }
    public String toString() {
        return Integer.toString(b);
    }
}

class TestAB {
    public static void main(String[] args) {
        Queue<A> a = new LinkedList<>();
        a.add(new A(3));
        a.add(new A(5));
        a.add(new A(2));
        väljasta(a);
        Queue<B> b = new PriorityQueue<>();
        b.add(new B(3));
        b.add(new B(5));
        b.add(new B(2));
        väljasta(b);
        PriorityQueue<A> a2 = new PriorityQueue<A>(a);
        väljasta(a2);
    }
    public static <T> void väljasta(Queue<T> c) {
        while (!c.isEmpty()) {
            System.out.println(c.poll());
        }
    }
}

//    Mis väljastatakse ekraanile? Selgitada!
/*
3
5
2
5
3
2

 */
//        Mis juhtub, kui meetod poll() asendada meetodiga peek()?
/*
väljasta meetod läheks lõpmatusse tsüklisse ja jääkski 3 väljastama
 */
//        Mis juhtub, kui esialgses (meetodiga poll()) programmis rida väljasta(a)välja kommenteerida?
/*
5
3
2
2
3
5
 */
//        Mis juhtub, kui esialgses programmis muutuja a2 väärtustataks
//        PriorityQueue<A> a2 = new PriorityQueue<A>(a);?
/*
Midagi ei muutuks, sest Queue on liides, mille PriorityQueue realiseerib.
 */
