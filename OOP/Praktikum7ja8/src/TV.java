import java.util.ArrayList;
import java.util.List;

public class TV implements BroadcastListener{

    final String ownerName;

    TV(String initOwnerName){
        ownerName = initOwnerName;
    }

    @Override
    public void listen(String str) {
        System.out.println(ownerName);
        System.out.println(str);
    }
}
