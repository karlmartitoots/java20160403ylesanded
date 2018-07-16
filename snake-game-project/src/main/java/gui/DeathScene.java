package gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The scene for when the game ends.
 * TODO: add option for replaying with same options
 * TODO: add leaderboards and personal highscores
 */
class DeathScene extends Scene {
    private double deathSceneWidth = 500;
    private double deathSceneHeight = 400;
    private double nodeWidth = 400;
    private double nodeHeight = 50;

    /**
     * A constructor method that creates the scene when the game is over.
     *
     * @param parentGroup The main container of the scene.
     * @param primaryStage Stage for the scene.
     * @param amountOfFruitEaten The amount of Fruit eaten in the last game.
     * @param minimumGametickLengthInMS The minimum amount of milliseconds time in a gametick.
     */
    DeathScene(Group parentGroup, Stage primaryStage, int amountOfFruitEaten, int minimumGametickLengthInMS) {
        super(parentGroup);

        setupDeathScene(parentGroup, primaryStage, amountOfFruitEaten, minimumGametickLengthInMS);
        playDeathScene(primaryStage);
    }

    /**
     * Sets up the scene, when the game is over.
     * @param parentGroup The main container of the scene.
     * @param primaryStage Stage for the scene.
     * @param amountOfFruitEaten The amount of Fruit eaten in the last game.
     * @param maxPeriod The speed settings for the last game.
     */
    private void setupDeathScene(Group parentGroup, Stage primaryStage, int amountOfFruitEaten, int maxPeriod) {
        Label gameOverLabel = createLabel("GAMEOVER", 40);
        gameOverLabel.setTextFill(Color.RED);

        Label fruitEatenLabel = createLabel("You ate " + String.valueOf(amountOfFruitEaten) + " fruits!", 20);

        int speedMultiplier = 400/maxPeriod;
        Label scoreLabel = createLabel("Score = speedMultiplier X fruitEaten: \n\t" + speedMultiplier + " X " + amountOfFruitEaten + " = " + speedMultiplier*amountOfFruitEaten, 20);
        scoreLabel.setPrefSize(nodeWidth, 2*nodeHeight);

        Button replayButton = new Button("TRY AGAIN");
        replayButton.setPrefSize(nodeWidth, nodeHeight);

        placeScreenRows(parentGroup, gameOverLabel, fruitEatenLabel, scoreLabel, replayButton);

        listenButtonForReplay(primaryStage, replayButton);
    }

    /**
     * Sets an eventlistener on button press. When the button os pressed, SetupScene loads in and DeathScene closes.
     * @param primaryStage The stage for the scene.
     * @param replayButton The button, that triggers the change of scene when pressed.
     */
    private void listenButtonForReplay(Stage primaryStage, Button replayButton) {
        replayButton.setOnAction(event -> {
            primaryStage.close();
            primaryStage.setScene(new SetupScene(new Group(), primaryStage));
        });
    }

    /**
     * Places a vertical box node containing all the nodes on the parent Group in a conformation of rows. Sets the VBox's size and position.
     * @param parentGroup The parent Group of the VBox placed.
     * @param gameOverLabel Label for displaying the gameover message.
     * @param fruitEatenLabel Label for displaying the fruit eaten in the last game.
     * @param scoreLabel Label for displaying the score in the last game.
     * @param replayButton A button for reloading the SetupScene.
     */
    private void placeScreenRows(Group parentGroup, Label gameOverLabel, Label fruitEatenLabel, Label scoreLabel, Button replayButton) {
        VBox screenVBox = new VBox(gameOverLabel, fruitEatenLabel,scoreLabel, replayButton);
        parentGroup.getChildren().add(screenVBox);
        screenVBox.setSpacing(10);
        screenVBox.setPrefSize(deathSceneWidth - 100, deathSceneHeight - 50);
        screenVBox.setLayoutX((deathSceneWidth - screenVBox.getPrefWidth())/2);
        screenVBox.setLayoutY((deathSceneHeight - screenVBox.getPrefHeight())/2);
    }

    /**
     * Creates a label with the specified text and font values.
     * @param text Text value on the label.
     * @param font Font size value of the label's text.
     * @return Returns the created label.
     */
    private Label createLabel(String text, int font){
        Label label = new Label(text);
        label.setFont(new Font(font));
        label.setPrefSize(nodeWidth, nodeHeight);
        return label;
    }

    /**
     * Sets up the stage for the play and plays the scene.
     * @param primaryStage The stage for the play.
     */
    private void playDeathScene(Stage primaryStage) {
        setStageSize(primaryStage);
        Image deathIcon = new Image("deathIcon.jpg");
        primaryStage.getIcons().clear();
        primaryStage.getIcons().add(deathIcon);
        primaryStage.setTitle("Death");
        primaryStage.setScene(this);
        primaryStage.show();
    }

    /**
     * Sets the stage's size properties.
     * @param primaryStage The stage for the DeathScene play.
     */
    private void setStageSize(Stage primaryStage) {
        primaryStage.setMinWidth(deathSceneWidth);
        primaryStage.setMinHeight(deathSceneHeight);
        primaryStage.setMaxWidth(deathSceneWidth);
        primaryStage.setMaxHeight(deathSceneHeight);
    }
}
