public class Rippsild {
    private char sillaTähis;
    private int läbimiseAeg;
    public Rippsild( char sillaTähis , int läbimiseAeg ) {
        this . läbimiseAeg = läbimiseAeg;
        this . sillaTähis = sillaTähis ;
    }
    //public void ületa(Seikleja seikleja) throws InterruptedException {
 public synchronized void ületa ( Seikleja seikleja ) throws InterruptedException {
        System. out .println( seikleja .getNimi() + " astus sillale " + sillaTähis + " "
                + ((System. currentTimeMillis () - seikleja.getAlgusAeg()) / 1000));
        Thread. sleep (1000 * läbimiseAeg );
        System. out .println( seikleja .getNimi() + " lahkus sillalt " + sillaTähis + " "
                + (System. currentTimeMillis () - seikleja.getAlgusAeg()) / 1000);
    }
}

