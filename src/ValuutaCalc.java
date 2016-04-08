import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ValuutaCalc extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        //logo


        //EUR tekst
        Text euroText = new Text("EUR:");
        grid.add(euroText, 1, 1);

        //tekstiväli üks
        TextField euros = new TextField("0.0");
        grid.add(euros, 2, 1);

        //tekstiväli kaks
        TextField convertedUnit = new TextField("0.0");
        grid.add(convertedUnit, 2, 2);

        //drop-down menüü
        ChoiceBox units = new ChoiceBox(ObservableList<String>
                "GBP",
                "RUB",
                "USD");


        Scene scene = new Scene(grid);

    }
}
