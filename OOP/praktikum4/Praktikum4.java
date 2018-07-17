import java.util.ArrayList;

/**
 * Created by Karl on 18.02.2016.
 */
public class Praktikum4 {

    public static void main(String[] args){

        String a1 = "asd";
        String a2 = "asd";

        //a1 == a2: true

        ArrayList<String> list = new ArrayList<>();
        list.add("asd");

        String getString = list.get(0);

        list.add("gwbgr");
        list.add("fdsbr");

        for (String s : list) {
            System.out.println(s);
        }



    }


}
