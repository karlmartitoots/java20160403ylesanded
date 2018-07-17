public class SuurimaViiviseLeidja implements Kontrollija{

    String biggestViivisGuy = "Nobody";
    String biggestViivisGuysTeos = "Nothing";

    public void saadaHoiatus(){
        System.out.println(biggestViivisGuy);
        System.out.println(biggestViivisGuysTeos);
    }

    @Override
    public void salvestaViivis(String name, String teos, double thisGuysViivis) {
        double biggestViivis = 0;
        if(thisGuysViivis > biggestViivis){
            biggestViivisGuy = name;
            biggestViivisGuysTeos = teos;
        }
    }
}
