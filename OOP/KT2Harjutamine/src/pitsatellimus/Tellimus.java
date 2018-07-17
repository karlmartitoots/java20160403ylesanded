package pitsatellimus;

import java.util.List;

public class Tellimus implements Comparable<Tellimus>{
    private String clientName;
    private List<Pitsa> pizzaList;
    private int waitingTimeMINUTES;

    public Tellimus(String clientName, List<Pitsa> pizzaList, int waitingTimeMINUTES) {
        this.clientName = clientName;
        this.pizzaList = pizzaList;
        this.waitingTimeMINUTES = waitingTimeMINUTES;
    }

    public double maksumus() throws LiigaPikkOotamineErind{
        double sum = 0;
        if(waitingTimeMINUTES > 30){
            try{
                throw new LiigaPikkOotamineErind(this);
            }catch (LiigaPikkOotamineErind e){
                System.out.println("Läks liiga kaua!");
                return 0;
            }
        }else{
            for (Pitsa pizza : pizzaList) {
                sum += pizza.getPizzaPrice();
            }
        }
        return sum;
    }

    public String getClientName() {
        return clientName;
    }

    public List<Pitsa> getPizzaList() {
        return pizzaList;
    }

    public int getWaitingTimeMINUTES() {
        return waitingTimeMINUTES;
    }

    @Override
    public int compareTo(Tellimus o) {
        return Integer.compare(this.waitingTimeMINUTES, o.getWaitingTimeMINUTES());
    }

    @Override
    public String toString() {
        return "Tellimus{" +
                "clientName='" + clientName + '\'' +
                ", pizzaList=" + pizzaList +
                ", waitingTimeMINUTES=" + waitingTimeMINUTES +
                '}';
    }
}
