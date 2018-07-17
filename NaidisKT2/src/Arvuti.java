import java.time.LocalDateTime;

import static java.lang.Math.floor;

class Arvuti{
    protected String tootja;
    private boolean tööliik;
    private LocalDateTime registreerimiseAeg;
    protected Double arveSumma = null;

    Arvuti(String tootja, boolean tööliik){
        this.tootja = tootja;
        this.tööliik = tööliik;
        this.registreerimiseAeg = LocalDateTime.now();
    }

    Arvuti(String tootja, boolean tööliik, LocalDateTime registreerimiseAeg){
        this.tootja = tootja;
        this.tööliik = tööliik;
        this.registreerimiseAeg = registreerimiseAeg;
    }

    public void lõpetaTöö(double baasHind){
        if(onKiirtöö()){
            arveSumma = floor((baasHind + 12)*100)/100;
        }else{
            arveSumma = floor((baasHind + 2)*100)/100;
        }
    }

    public boolean onKiirtöö() {
        return tööliik;
    }

    public String getTootja() {
        return tootja;
    }

    public LocalDateTime getRegistreerimiseAeg() {
        return registreerimiseAeg;
    }

    public Double getArveSumma() {
        return arveSumma;
    }

    @Override
    public String toString(){
        String tööliik = (onKiirtöö()) ? "kiirtöö" : "tavatöö";
        return tootja +
                ";" +
                tööliik +
                "|" +
                getRegistreerimiseAeg().toString();
    }

}
