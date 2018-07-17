import java.util.Random;

public class Ex6Test{
    public static void main(String[] args) {
        int i, trueCounter = 0;
        Exercise6 juh1 = new Exercise6(40);//60% tõenäosus trueks
        for(i = 0; i < 1000; i++) {
            if(juh1.nextBoolean()) trueCounter++;
        }
        System.out.println("For juh1: " + trueCounter + " trues out of 1000.");
        Random juh2 = new Exercise6(40);//60%tõenäosus trueks
        trueCounter = 0;
        for(i = 0; i < 1000; i++) {
            if(juh2.nextBoolean()) trueCounter++;
        }
        System.out.println("For juh2: " + trueCounter + " trues out of 1000.");
        Random juh3 = new Random(40);//50% tõenäosus trueks
        trueCounter = 0;
        for(i = 0; i < 10000; i++) {
            if(juh3.nextBoolean()) trueCounter++;
        }
        System.out.println("For juh2: " + trueCounter + " trues out of 10000.");
        //Exercise6 juh4 = new Random(40);
        //System.out.println(juh4.nextBoolean());
    }
}

//    Peaklassis on püütud erineval moel saada juhuslikke tõeväärtusi.
//        KallutatudJuhuslik juh1 = new KallutatudJuhuslik(40);
//        System.out.println(juh1.nextBoolean());
//        Random juh2 = new KallutatudJuhuslik(40);
//        System.out.println(juh2.nextBoolean());
//        Random juh3 = new Random(40);
//        System.out.println(juh3.nextBoolean());
//        KallutatudJuhuslik juh4 = new Random(40);
//        System.out.println(juh4.nextBoolean());
//        Kas see kood kompileerub? Kui ei, siis mis põhjusel. Kui (vajadusel vigased read välja kommenteerides)
//        programm käivitada, siis millise tõenäosusega ilmub igal erineval juhul ekraanile true. Selgitada!