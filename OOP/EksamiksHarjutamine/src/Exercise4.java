public class Exercise4 {
    public static void main(String[] args) {
        int[] a = {1, 0};
        try {
            System.out.println(1/a[1]);//1/0... arithmeticexception
            System.out.println("Vahel");
            System.out.println(a[3]);
        } catch (ArithmeticException e) {
            System.out.println("Esimene püünis");
        } catch (RuntimeException e) {
            System.out.println("Teine püünis");
        } finally {
            System.out.println("Epiloog");
            System.out.println(a[3]);
        }
        System.out.println("Pärast");
    }
}
//Mis ilmub ekraanile? Selgitada.
//  Teavet
//  Klass ArithmeticException on klassi RuntimeException alamklass. Klass
//    ArrayIndexOutOfBoundsException on klassi RuntimeException (kaudne) alamklass.
/*
Esimene püünis
Epiloog
ja viskab arrayindexoutofbounds exceptioni
 */

