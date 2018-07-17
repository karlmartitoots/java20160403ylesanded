import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Karl on 25.02.2016.
 */
public class ReverseSortedAggregatingTable extends SortedAggregatingTable {

    List<String> getElements(){

        List<String> target = new ArrayList<>();
        target.addAll(super.getElements());
        Collections.reverse(target);

        return target;

    }

}
