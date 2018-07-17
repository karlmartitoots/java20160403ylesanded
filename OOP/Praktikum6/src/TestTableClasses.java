/**
 * Created by Karl on 25.02.2016.
 */
public class TestTableClasses {

    public static void main(String[] args) {

        Table table = new Table();
        AggregatingTable aggregatingTable = new AggregatingTable();
        SortedAggregatingTable sortedAggregatingTable = new SortedAggregatingTable();
        ReverseSortedAggregatingTable reverseSortedAggregatingTable = new ReverseSortedAggregatingTable();
        table.addData("data1");
        table.addData("data2");
        table.addData("data3");
        table.addData("data4");
        aggregatingTable.addExtraData("extradata1");
        aggregatingTable.addExtraData("extradata2");
        System.out.println("1" + table.linesToString());
        System.out.println("2"+aggregatingTable.linesToString());
        System.out.println("3"+sortedAggregatingTable.linesToString());
        System.out.println("4"+reverseSortedAggregatingTable.linesToString());


    }

}
