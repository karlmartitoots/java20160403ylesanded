public abstract class Teos implements Comparable<Teos>{

    private String kirjeldus;
    private String tähis;
    private String laenutaja;
    private int päevadeArv;

    Teos(String initKirjeldus, String initTähis, String initLaenutaja, int initPäevadeArv ){
        this.kirjeldus = initKirjeldus;
        this.laenutaja = initLaenutaja;
        this.tähis = initTähis;
        this.päevadeArv = initPäevadeArv;
    }

    public abstract boolean kasHoidlast();

    public int laenutusaeg(){
        switch(this.tähis){
            case "roheline":
                return 1;
            case "puudub":
                return 14;
            case "kollane":
                return 30;
            case "sinine":
                return 60;
            default:
                return 0;
        }
    }

    public double päevaneViivis(){
        switch(this.tähis){
            case "roheline":
                return 2.0;
            case "puudub":
                return 0.15;
            case "kollane":
                return 0.05;
            case "sinine":
                return 0.05;
            default:
                return 0;
        }
    }

    public void arvutaViivis(Kontrollija kontrollija){
        if(this.päevadeArv > laenutusaeg()){
            kontrollija.salvestaViivis(this.laenutaja, this.kirjeldus, (this.päevadeArv - laenutusaeg())*päevaneViivis());
        }
    }

    public String toString(){
        return "Teose kirjeldus: " + this.kirjeldus +
                "\nLaenutusaeg: " + laenutusaeg() +
                "\nLaenutaja: " + this.laenutaja +
                "\nLaenutatud päevade arv: " + this.päevadeArv +
                "\nKas on vaja hoidlast tellida?: " + ((kasHoidlast())? "Jah" : "Ei");
    }

    public int compareTo(Teos teos1, Teos teos2){
        return teos1.kirjeldus.compareTo(teos2.kirjeldus);
    }

}
