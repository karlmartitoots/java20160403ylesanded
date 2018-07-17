import java.util.ArrayList;
import java.util.List;

public class IsikukoodiRegister {

    private List<String> isikukoodid = new ArrayList<>();

    void registreeri(String isikukood){
        if(!this.isikukoodid.contains(isikukood)){
            synchronized (this.isikukoodid) {
                this.isikukoodid.add(isikukood);
            }
        }
    }

    int järjekorranumber(String isikukood){
        if(this.isikukoodid.contains(isikukood)){
            synchronized (this.isikukoodid) {
                return this.isikukoodid.indexOf(isikukood);
            }
        }else{
            return -1;
        }
    }

}
