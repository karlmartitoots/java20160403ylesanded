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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ValuutaCalc extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //creates the interface layout grid
        GridPane layoutGrid = new GridPane();
        layoutGrid.setVgap(10);
        layoutGrid.setHgap(10);
        layoutGrid.setPadding(new Insets(5, 5, 5, 5));

        //adds logo in column 1 row 1, spanning two columns
        createLogo(layoutGrid);

        //adds "EUR" Label in column 1 row 2
        layoutGrid.add(new Label("EUR"), 0, 1);

        //adds a text field for getting input in EUR currency in column 2 row 2
        TextField eurosTextField = new TextField("0.0");
        layoutGrid.add(eurosTextField, 1, 1);

        //adds a choicebox in column 1 row 3 for choosing output currency
        ChoiceBox<String> currencyChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(
                "USD",
                "GBP",
                "SEK",
                "RUB"
        ));
        currencyChoiceBox.setValue("USD");
        layoutGrid.add(currencyChoiceBox, 0, 2);

        //adds a text field for showing the converted value in column 2 row 3
        TextField otherCurrencyTextField = new TextField();
        layoutGrid.add(otherCurrencyTextField, 1, 2);

        //event that triggers when output currency text field is changed
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

        //sets the scene to the created layout grid
        Scene scene = new Scene(layoutGrid);

        //sets stage specifications and shows the stage
        primaryStage.setTitle("EuroCalc");
        primaryStage.setResizable(true);
        primaryStage.setMaxHeight(200);
        primaryStage.setMaxWidth(300);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void createLogo(GridPane layoutGrid) {
        Canvas logo = new Canvas(300, 50);
        GraphicsContext graphicsContext = logo.getGraphicsContext2D();
        drawLogo(graphicsContext);
        layoutGrid.add(logo, 0, 0, 2, 1);
    }

    private void drawLogo(GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.AQUA);
        graphicsContext.fillOval(10, 10, 30, 30);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(50, 10, 30, 30);
        graphicsContext.setFill(Color.CHOCOLATE);
        graphicsContext.fillRoundRect(90, 10, 30, 30, 10, 10);
    }

    private static double currencyCoef(String currency) throws FileNotFoundException {
        Map<String, Double> coefs = new HashMap<>();
        try(
                Scanner fileScanner = new Scanner(new File("currencies.txt"))
                ){
            String line;
            while(fileScanner.hasNextLine()){
                line = fileScanner.nextLine();
                String[] parts = line.split(";");
                coefs.put(parts[0], Double.parseDouble(parts[1].replace("\n", "")));
            }
        }catch(RuntimeException e) {
            coefs.put("USD", 1.1268);
            coefs.put("GBP", 0.77866077);
            coefs.put("SEK", 9.27392142);
            coefs.put("RUB", 76.3518092);
            throw new RuntimeException("Ran into an error: " + e + "\n Getting the default currency converting coefficient.");
        }
        return coefs.get(currency);
    }

}