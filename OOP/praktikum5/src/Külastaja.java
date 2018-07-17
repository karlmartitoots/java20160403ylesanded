import java.util.ArrayList;
import java.util.List;

public class Külastaja {

    List<String> külastuseKirjeldused;
    int vanus;
    double kulud = 0;


    Külastaja(){
        this.külastuseKirjeldused = new ArrayList<String>();
    }

    Külastaja(int initVanus){
        this.vanus = initVanus;
        this.külastuseKirjeldused = new ArrayList<String>();
    }

    public void lisaKirjeldus(String sõne){
        this.külastuseKirjeldused.add(sõne);
    }

    public List<String> kõikKirjeldused(){
        return this.külastuseKirjeldused;
    }

    public int getVanus(){
        if(vanus == 0){
            System.out.println("Age has not been set");
            return -1;
        }else {
            return this.vanus;
        }
    }

    public void lisaKulu(double lisatavKulu){
        this.kulud += lisatavKulu;
    }

    public double kuludeSumma(){
        return this.kulud;
    }

}
