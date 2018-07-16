public class Hinnang {
    private Kontrolltöö kontrolltöö;
    private double hinne;
    private String kommentaar;

    public Hinnang(Kontrolltöö kontrolltöö, double hinne, String kommentaar) throws EbasobivHinneErind {
        this.kontrolltöö = kontrolltöö;
        setHinne(hinne);
        this.kommentaar = kommentaar;
    }

    public Kontrolltöö getKontrolltöö() {
        return kontrolltöö;
    }

    public String getKommentaar() {
        return kommentaar;
    }

    public double getHinne() {
        return hinne;
    }

    public void setHinne(double hinne) throws EbasobivHinneErind {
        if(hinne < 0 || hinne > 14){
            throw new EbasobivHinneErind("Exception");
        }else{
            this.hinne = hinne;
        }
    }

    @Override
    public String toString() {
        return kontrolltöö.toString() +
                "\nHinne: " + hinne +
                " ja kommentaar: " + kommentaar;
    }
}
