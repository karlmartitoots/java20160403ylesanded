package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * The Application subclass for initiating the first scene.
 */
public class StartApplication extends Application{
    /**
     * The Main class of the Application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start method for the game Application.
     * @param primaryStage The stage for all the scenes in the Application.
     * @throws Exception Exception to throw in case of failure when launched.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new SetupScene(new Group(), primaryStage));
    }
}
