import java.util.ArrayList;
import java.util.List;

public class DigiTV extends TV{

    List<String> recordedNews = new ArrayList<>();

    DigiTV(String initOwnerName) {
        super(initOwnerName);
    }

    void replayAll(){
        for (String str : recordedNews) {
            System.out.println(str);
        }
    }

    @Override
    public void listen(String str) {
        recordedNews.add(str);
        System.out.println(ownerName);
        System.out.println(str);
    }

}

