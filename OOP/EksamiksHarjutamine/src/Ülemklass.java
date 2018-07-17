public class Ülemklass extends Object{
    void doStuff(){
        System.out.println("Ülemstuff");
    }
}

class Alamklass extends Ülemklass{
    @Override
    void doStuff(){
        System.out.println("Alamstuff");
    }
}

class Erindivärk extends Exception{
}

class ÜlemAlamTest{
    public static void main(String[] args) throws Erindivärk {
        Ülemklass alam1 = new Alamklass();
        Alamklass alam2 = new Alamklass();
        alam1.doStuff();
        alam2.doStuff();
        throw new Erindivärk();
    }
}
class ErindiVärkTeine extends RuntimeException{
    private int suurus;
    public ErindiVärkTeine(int suurus) {
        super();
        this.suurus = suurus;

    }
    public String toString(){
        return null;
    }
}
