package strategy;

public class DecoyDuck extends Duck{
    public DecoyDuck() {
        quackBehavior = new MuteQuack();
        flyBehavior = new NoFly();
    }

    public void upgradeToRocketPowered(){
        this.setFlyBehavior(new RocketPoweredFly());
    }

    public void display() {
        System.out.println("I'm decoy.");
    }
}
