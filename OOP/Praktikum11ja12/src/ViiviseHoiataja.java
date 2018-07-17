import java.util.ArrayList;
import java.util.List;

public class ViiviseHoiataja implements Kontrollija{

    private double lubatudViivis;
    private List<String> paharetid = new ArrayList<>();

    ViiviseHoiataja(double initLubatudViivis){
        this.lubatudViivis = initLubatudViivis;
    }

    @Override
    public void salvestaViivis(String name, String teos, double thisGuysViivis) {
        if(thisGuysViivis > this.lubatudViivis && !paharetid.contains(name)){
            paharetid.add(name);
        }
    }

    public List<String> getHoiatatavadLaenutajad(){
        return this.paharetid;
    }

}
