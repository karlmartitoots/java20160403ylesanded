package pitsatellimus;

public class Pitsa {
    private String pizzaName;
    private double pizzaPrice;

    public Pitsa(String pizzaName, double pizzaPrice) {
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    @Override
    public String toString() {
        return "Pitsa{" +
                "pizzaName='" + pizzaName + '\'' +
                ", pizzaPrice=" + pizzaPrice +
                '}';
    }
}
