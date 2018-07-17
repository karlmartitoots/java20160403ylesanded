package strategy;

public class RubberDuck extends Duck {

    public RubberDuck() {
        quackBehavior = new Squeak();
        flyBehavior = new NoFly();
    }

    public void display() {
        System.out.println("I'm a rubber duck.");
    }
}
