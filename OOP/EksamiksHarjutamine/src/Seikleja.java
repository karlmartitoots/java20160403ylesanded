public class Seikleja implements Runnable {
    private String nimi ;
    private boolean kasAlgusest ;
    private Rippsild[] rippsillad ;
    private long algusAeg ;
    public Seikleja(String nimi , Rippsild[] rippsillad , boolean kasAlgusest ) {
        this.nimi = nimi ;
        this.rippsillad = rippsillad ;
        this.kasAlgusest = kasAlgusest ;
    }
    public String getNimi() {
        return nimi ;
    }
    public long getAlgusAeg() {
        return algusAeg ;
    }
    public void run() {
        this.algusAeg = System.currentTimeMillis ();
        try {
            if ( kasAlgusest ) {
                for ( int i = 0; i < rippsillad . length ; i ++)
                rippsillad [ i ].ületa( this );
            } else {
                for ( int i = rippsillad . length - 1; i >= 0; i --)
                rippsillad [ i ].ületa( this );
            }
        } catch (InterruptedException e ) {
            throw new RuntimeException( e );
        }
    }
}
