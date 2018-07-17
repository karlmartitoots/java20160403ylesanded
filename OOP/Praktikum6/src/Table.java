import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karl on 25.02.2016.
 */
public class Table {

    private List<String> data = new ArrayList<String>();

    final String linesToString(){

        String target = "";
        for (String s : getElements()) {
            target += s + "\n";
        }
        return target;
    }

    void addData(String newData){
        this.data.add(newData);
    }

    List<String> getElements(){
        return this.data;
    }

}
