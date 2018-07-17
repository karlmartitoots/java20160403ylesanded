import java.util.ArrayList;
import java.util.List;

public class Broadcaster{

    List<BroadcastListener> listenersList = new ArrayList<>();

    void subscribe(BroadcastListener broadcastListener){
        listenersList.add(broadcastListener);
    }

    void broadcast(String str){
        for (BroadcastListener broadcastListener : listenersList) {
            broadcastListener.listen(str);
        }
    }

}

