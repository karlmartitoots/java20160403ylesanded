import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class EuroCalc extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("EUR"), 1, 1);
        TextField euros = new TextField("0.0");
        grid.add(euros, 2, 1);
        ChoiceBox<String> cb = new ChoiceBox(FXCollections.observableArrayList(
                "USD",
                "GBP",
                "SEK",
                "RUB"
        ));
        cb.setValue("USD");
        grid.add(cb, 1, 2);
        TextField otherUnit = new TextField();
        grid.add(otherUnit, 2, 2);

        //events
        euros.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    if(euros.getText().equals("")){
                        otherUnit.setText("0");
                    }else {
                        otherUnit.setText(String.valueOf((Double.parseDouble(euros.getText())) * currencyCoef(cb.getValue())));
                    }
                }catch(Exception e){
                    otherUnit.setText("ERROR");
                }
            }
        });

        Scene scene = new Scene(grid);

        primaryStage.setTitle("EuroCalc");
        primaryStage.setResizable(true);
        primaryStage.setMaxHeight(200);
        primaryStage.setMaxWidth(300);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static double currencyCoef(String currency){
        Map<String, Double> coefs = new HashMap<>();
        coefs.put("USD",1.1268);
        coefs.put("GBP",0.77866077);
        coefs.put("SEK",9.27392142);
        coefs.put("RUB",76.3518092);
        return coefs.get(currency);
    }

    public static void main(String[] args) {
        launch(args);
    }

}