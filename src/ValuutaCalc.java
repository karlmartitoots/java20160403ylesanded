import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class ValuutaCalc extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane layoutGrid = new GridPane();
        layoutGrid.setVgap(10);
        layoutGrid.setHgap(10);
        layoutGrid.setPadding(new Insets(5, 5, 5, 5));

        //canvas
        Canvas logo = new Canvas(300, 50);
        GraphicsContext graphicsContext = logo.getGraphicsContext2D();
        drawLogo(graphicsContext);
        layoutGrid.add(logo, 0, 0, 2, 1);

        layoutGrid.add(new Label("EUR"), 1, 1);

        TextField eurosTextField = new TextField("0.0");
        layoutGrid.add(eurosTextField, 2, 1);

        ChoiceBox<String> currencyChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(
                "USD",
                "GBP",
                "SEK",
                "RUB"
        ));
        currencyChoiceBox.setValue("USD");
        layoutGrid.add(currencyChoiceBox, 1, 2);

        TextField otherCurrencyTextField = new TextField();
        layoutGrid.add(otherCurrencyTextField, 2, 2);

        //events
        eurosTextField.setOnKeyReleased(event -> {
            try {
                if(eurosTextField.getText().equals("")){
                    otherCurrencyTextField.setText("0");
                }else {
                    otherCurrencyTextField.setText(String.valueOf((Double.parseDouble(eurosTextField.getText())) * currencyCoef(currencyChoiceBox.getValue())));
                }
            }catch(Exception e){
                otherCurrencyTextField.setText("ERROR");
            }
        });

        Scene scene = new Scene(layoutGrid);

        primaryStage.setTitle("EuroCalc");
        primaryStage.setResizable(true);
        primaryStage.setMaxHeight(200);
        primaryStage.setMaxWidth(300);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private static double currencyCoef(String currency){
        Map<String, Double> coefs = new HashMap<>();
        coefs.put("USD",1.1268);
        coefs.put("GBP",0.77866077);
        coefs.put("SEK",9.27392142);
        coefs.put("RUB",76.3518092);
        return coefs.get(currency);
    }

    private void drawLogo(GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.AQUA);
        graphicsContext.fillOval(10, 10, 30, 30);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(50, 10, 30, 30);
        graphicsContext.setFill(Color.CHOCOLATE);
        graphicsContext.fillRoundRect(90, 10, 30, 30, 10, 10);
    }

    public static void main(String[] args) {
        launch(args);
    }

}