
public class Test1 {

    public static void main(String[] args) {

        System.out.println(args);
        int[] massiiv = new int[10];
        int[] massiiv2 = new int[massiiv.length + 1];
        System.arraycopy(massiiv, 0, massiiv2, 0, massiiv.length);
        massiiv2[massiiv.length] = 11;
        //for (int el : massiiv2) System.out.println(el);
        int[][] kahemootmeline;
        kahemootmeline = new int[3][4];
        int[] array = new int[args.length];

        //ctrl alt m - teeb meetodiks
        //ctrl+q java - doc

        //System.arraycopy(mida copin, kust alustan, kuhu copyn, kuhu alustan, kui pika osa copyn);

    }
}
