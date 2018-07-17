import java.util.ArrayList;
import java.util.List;

public class TVTest{

    public static void main(String[] args) {
        DigiTV listener1 = new DigiTV("Kim Jong-un");
        DigiTV listener2 = new DigiTV("Kim Jong-nam");
        BroadcastListener listener3 = new TV("Pak Pon-ju");
        List<String> FoxNews = new ArrayList<>();
        FoxNews.add("FoxNews");

        PropagandaStation nkNationalNews = new PropagandaStation();
        TVStation foxNews = new TVStation(FoxNews);
        PirateStation nkWorldNews = new PirateStation(FoxNews);

        Broadcaster broadcaster = new Broadcaster();

        broadcaster.subscribe(listener1);
        broadcaster.subscribe(listener2);
        broadcaster.subscribe(listener3);

        nkNationalNews.broadcastNow();
        System.out.println();
        foxNews.broadcastNow();
        System.out.println();
        nkWorldNews.broadcastNow();
        System.out.println();
        listener1.replayAll();
    }

}
