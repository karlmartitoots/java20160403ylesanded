package strategy;

public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public void setFlyBehavior(FlyBehavior fb){
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb){
        quackBehavior = qb;
    }

    public void performQuack(){
        quackBehavior.quack();
    };
    public void swim(){
        System.out.println("Floating...");
    };
    public abstract void display();
    public void performFly(){
        flyBehavior.fly();
    };
}
