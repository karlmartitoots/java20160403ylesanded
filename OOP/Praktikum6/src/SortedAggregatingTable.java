import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Karl on 25.02.2016.
 */
public class SortedAggregatingTable extends AggregatingTable{

    @Override
    List<String> getElements() {

        List<String> target = new ArrayList<>();
        target.addAll(super.getElements());
        Collections.sort(target);

        return target;
    }
}
