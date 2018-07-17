import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Exercise3 {
    public static void main(String[] args) {
        HashSet<Integer> hulk1 = new HashSet<Integer>();//automatic sort when tostringing
        hulk1.add(3);
        hulk1.add(1);
        hulk1.add(1);
        List<Integer> list1 = new ArrayList<Integer>();//ei muutu sest List on liides mida arraylist realiseerib
        //new List ei kompileeru
        list1.add(1);
        list1.add(2);
        list1.add(1, 1);//index ... element
        System.out.println(list1);
        System.out.println(hulk1);
        System.out.println(hulk1.addAll(list1));
        System.out.println(hulk1.addAll(list1));
        System.out.println(hulk1);
    }
}
