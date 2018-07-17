package staticfactorymethods;

public class RandomIntGeneratorConst {
    private final int min;
    private final int max;

    public RandomIntGeneratorConst(int min, int max) {
        this.min = min;
        this.max = max;
    }
    //can't name differently...
    //can't make a constructor with the same signature

    public RandomIntGeneratorConst(int min) {
        this.min = min;
        this.max = Integer.MAX_VALUE;
    }
/*
    public RandomIntGeneratorConst(int max) {
        this.max = max;
        this.min = Integer.MIN_VALUE;
    }
*/
    public int next() {return -1;}
}
