import java.util.ArrayList;
import java.util.List;

public class Individuaaltreening implements Treening {

    private String coachName;
    private List<Klient> clientList = new ArrayList<>(1);

    Individuaaltreening(String initCoachName){
        this.coachName = initCoachName;
    }

    @Override
    public boolean saabRegistreerida() {
        return (this.clientList.isEmpty()) ? true : false;
    }

    @Override
    public void registreeri(Klient klient) {
        if(saabRegistreerida()){
            this.clientList.add(klient);
        }
    }

    public String toString(){
        String info = "\nIndividuaaltreeningu treeneri nimi: " + coachName;
        if(!this.clientList.isEmpty()){
            info += "\nKliendi nimi: " + clientList.get(0).name;
        }
        return info;
    }

}
