public class Seikluspark {
    public static void main(String[] args ) {
        Rippsild[] sillad = { new Rippsild( 'A' , 10), new Rippsild( 'B' , 20)};
        Seikleja seikleja1 = new Seikleja( "Rasmus" , sillad , true );
        Seikleja seikleja2 = new Seikleja( "Grete" , sillad , false );
        Thread t1 = new Thread( seikleja1 );
        Thread t2 = new Thread( seikleja2 );
        t1 .start();
        t2 .start();
        System.out.println(1-0.9);
    }
}

