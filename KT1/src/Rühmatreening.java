import java.util.ArrayList;
import java.util.List;

public class Rühmatreening implements Treening {

    private String trainingProgramName;
    private int maximumNumberOfClients;
    private double priceOfProgram;
    private List<Klient> clientList = new ArrayList<>();
    private Klient currentApplyingClient;

    public Rühmatreening(String trainingProgramName, int maximumNumberOfClients, double priceOfProgram) {
        this.trainingProgramName = trainingProgramName;
        this.maximumNumberOfClients = maximumNumberOfClients;
        this.priceOfProgram = priceOfProgram;
    }

    @Override
    public boolean saabRegistreerida() {
        return (this.clientList.size() < this.maximumNumberOfClients)? true : false;
    }

    @Override
    public void registreeri(Klient klient) {
        this.currentApplyingClient = klient;
        if(saabRegistreerida() && !this.clientList.contains(klient)){
            this.currentApplyingClient.suurendaArvet(priceOfProgram);
            this.clientList.add(this.currentApplyingClient);
        }
    }

    public String toString(){
        return "\nRühmatreeningu nimi: " + trainingProgramName +
                "\nPiirarv: " + this.maximumNumberOfClients +
                "\nHind: " + this.priceOfProgram +
                "\nRegistreeritud klientide arv: " + this.clientList.size();
    }

}
