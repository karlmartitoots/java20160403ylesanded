import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Main extends Application {
    private ImageView gameFrame = new ImageView(new Image("GUI frame.jpg"));
    private int timerStartTime = 60;
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group mainGroup = new Group();
        mainGroup.getChildren().add(gameFrame);
        Text timerText = placeTimer(mainGroup);
        ScheduledFuture<?> timerControl = scheduler.scheduleAtFixedRate((Runnable) () ->
                reduceTimer(timerText),0L, 1L, SECONDS);
        Button endTurnButton = new Button("End turn");
        endTurnButton.relocate(100, 250);

        endTurnButton.setOnAction(event ->
        switchTurn(timerText));

        mainGroup.getChildren().add(endTurnButton);
        primaryStage.setScene(new Scene(mainGroup));
        primaryStage.show();
    }

    private Text placeTimer(Group root){
        Text timerText = new Text(10, 210, String.valueOf(timerStartTime));
        timerText.setFont(new Font(220));
        root.getChildren().add(timerText);
        return timerText;
    }

    private void reduceTimer(Text timerText){
        if(timerText.getText().equals("1")){
            switchTurn(timerText);
        }else {
            timerText.setText(String.valueOf(Integer.parseInt(timerText.getText()) - 1));
        }
    }

    private void resetTimer(Text timerText){
        timerText.setText(String.valueOf(timerStartTime));
    }

    private void switchTurn(Text timerText){
        resetTimer(timerText);
        System.out.println("Turn switched!");
    }
}
