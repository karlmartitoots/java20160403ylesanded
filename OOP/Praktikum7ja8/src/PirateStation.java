import java.util.ArrayList;
import java.util.List;

public class PirateStation extends TVStation implements BroadcastListener{

    List<String> listenersList = new ArrayList<>();

    PirateStation(List<String> initNewsList) {
        super(initNewsList);
    }

    @Override
    public void listen(String str) {
        broadcast("Pirate News");
        broadcast(str);
    }
}
