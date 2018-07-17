import java.util.ArrayList;
import java.util.List;

public class TVStation extends PropagandaStation{

    List<String> listOfNews;

    TVStation(List<String> initNewsList){
        listOfNews = initNewsList;
    }

    @Override
    void broadcastNow() {
        for (String str : listOfNews) {
            broadcast(str);
        }
    }
}

