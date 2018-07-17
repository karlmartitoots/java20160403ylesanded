import java.util.Arrays;

/**
 * Created by Karl on 11.02.2016.
 */
public class PraktikumiUlesanne1 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(uhildatudMassiiv(new int[]{-10, 17, 21}, new int[]{-2, 5, 17, 28})));
    }

    public static int[] uhildatudMassiiv(int[] a, int[] b) {

        int massiiviPikkus = a.length + b.length;
        int[] massiiv = new int[massiiviPikkus];
        int j = 0;
        int k = 0;
        boolean aOtsas = false;
        boolean bOtsas = false;

        for (int i = 0; i < massiiviPikkus; i++) {

            if (aOtsas) {
                massiiv[i] = b[k];
                k++;
            } else if (bOtsas) {
                massiiv[i] = a[j];
                j++;
            } else if (a[j] <= b[k]) {
                massiiv[i] = a[j];
                j++;
            } else {
                massiiv[i] = b[k];
                k++;
            }
            if (a.length == j) {
                aOtsas = true;
            }
            if (b.length == k) {
                bOtsas = true;
            }
        }

        return massiiv;

    }

}
