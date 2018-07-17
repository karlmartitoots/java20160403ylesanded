import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karl on 25.02.2016.
 */
public class AggregatingTable extends Table{

    private List<String> extraData = new ArrayList<String>();

    @Override
    List<String> getElements(){
        List<String> target = new ArrayList<String>();
        target.addAll(super.getElements());
        target.addAll(extraData);

        return target;
    }

    void addExtraData(String newExtraData){
        this.extraData.add(newExtraData);
    }

}
