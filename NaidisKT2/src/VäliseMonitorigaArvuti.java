import java.time.LocalDateTime;

import static java.lang.Math.floor;

class VäliseMonitorigaArvuti extends Arvuti{
    VäliseMonitorigaArvuti(String tootja, boolean tööliik){
        super(tootja, tööliik);
    }

    VäliseMonitorigaArvuti(String tootja, boolean tööliik, LocalDateTime registreerimiseAeg) {
        super(tootja, tööliik, registreerimiseAeg);
    }

    @Override
    public void lõpetaTöö(double baasHind){
        if(onKiirtöö()){
            arveSumma = floor((baasHind + 13)*100)/100;
        }else{
            arveSumma = floor((baasHind + 3)*100)/100;
        }
    }

    @Override
    public String toString(){
        String tööliik = (onKiirtöö()) ? "kiirtöö" : "tavatöö";
        return tootja +
                ";" +
                tööliik +
                ";monitoriga" +
                "|" +
                getRegistreerimiseAeg().toString();
    }
}
