package tulpdiagramm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Tulpdiagramm extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<Integer> tulpadeKõrgused = looKõrgused();
        List<Tulp> tulbad = new ArrayList<>(10);

        AnchorPane ap = new AnchorPane();
        Scene scene = new Scene(ap);

        for (Integer integer : tulpadeKõrgused) {
            Paint värv = getPaint(integer);
            Tulp tulp = new Tulp(integer, 10, värv);
            tulbad.add(tulp);
        }

        HBox tulbaHBox = new HBox(5);
        tulbaHBox.getChildren().addAll(tulbad);
        ap.getChildren().add(tulbaHBox);

        tulbaHBox.setLayoutX((400 - tulbaHBox.getWidth())/2);
        tulbaHBox.setLayoutY((400 - tulbaHBox.getHeight())/2);

        kuulaLaiust(ap, scene, tulbaHBox);
        kuulaKõrgust(ap, scene, tulbaHBox);
        kuulaKlikke(tulbad, ap);

        primaryStage.setTitle("Tulpdiagramm");
        primaryStage.getIcons().add(new Image("barchartlogo.jpg"));
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(400);
        primaryStage.show();
    }

    private void kuulaKlikke(List<Tulp> tulbad, AnchorPane ap) {
        ap.setOnMouseClicked(event -> {
            for (int i = 0; i < 10; i++){
                Paint värv = invertColor(tulbad.get(i).getVärv());
                tulbad.get(i).setVärv(värv);
            }
        });
    }

    private void kuulaLaiust(AnchorPane ap, Scene scene, HBox tulbaHBox) {
        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            tulbaHBox.setLayoutX((ap.getWidth() - tulbaHBox.getWidth())/2);
        });
    }

    private void kuulaKõrgust(AnchorPane ap, Scene scene, HBox tulbaHBox) {
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            tulbaHBox.setLayoutY((ap.getHeight() - tulbaHBox.getHeight())/2);
        });
    }

    private Paint invertColor(Paint paint) {
        if(paint.equals(Color.RED)){
            return Color.BLACK;
        } else if(paint.equals(Color.BLUE)){
            return Color.GREEN;
        }else if(paint.equals(Color.BLACK)){
            return Color.RED;
        }else{
            return Color.BLUE;
        }
    }

    private static List<Integer> looKõrgused() {
        List<Integer> tulpadeKõrgused = new ArrayList<>(10);
        for (int i = 0; i < 10; i++){
            tulpadeKõrgused.add((int) Math.round(Math.random()*100));
        }
        return tulpadeKõrgused;
    }

    private static Paint getPaint(int integer){
        Paint paint;
        if(integer > 50){
            paint = Color.RED;
        }else{
            paint = Color.BLUE;
        }
        return paint;
    }
}
