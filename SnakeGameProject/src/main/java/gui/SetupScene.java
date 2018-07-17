package gui;

import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import settings.GameSettings;

import java.util.Arrays;

/**
 * The scene for the setup play.
 * TODO: add default settings option
 * TODO: add support for changing moving direction buttons
 * TODO: add a small tutorial region showing the arrow keys and numpads as options
 */
class SetupScene extends Scene {
    //pixel values for setup properties
    private double setupScreenWidth = 500;
    private double setupScreenHeight = 500;
    private double nodeWidth = 400;
    private double nodeHeight = 50;
    private Image popupIcon = new Image("errorPopupIcon.jpg");

    /**
     * The constructor of setupScene sets up and conducts the setup until the play game button is pressed.
     *
     * @param parentGroup The parent group that holds the play.
     * @param primaryStage The stage for the play.
     */
    SetupScene(Group parentGroup, Stage primaryStage) {
        super(parentGroup);

        setUpSetupScene(parentGroup, primaryStage);
        playSetupScene(primaryStage);
    }

    /**
     * Sets the stage ready for the setupScene and plays the scene.
     * @param primaryStage The stage for the setupScene.
     */
    private void playSetupScene(Stage primaryStage) {
        setStageSize(primaryStage);
        Image gameIcon = new Image("settingsIcon.png");
        primaryStage.getIcons().add(gameIcon);
        primaryStage.setTitle("Setup");
        primaryStage.setScene(this);
        primaryStage.show();
    }

    /**
     * Sets and fixates the stages sizes to make it unresizable.
     * @param primaryStage The stage for the setupScene play.
     */
    private void setStageSize(Stage primaryStage) {
        primaryStage.setMinWidth(setupScreenWidth);
        primaryStage.setMinHeight(setupScreenHeight);
        primaryStage.setMaxWidth(setupScreenWidth);
        primaryStage.setMaxHeight(setupScreenHeight);
    }

    /**
     * Sets all the nodes ready for the setupScene play.
     * @param parentGroup The parent group of the setupScene.
     * @param primaryStage The stage for the setupScene play.
     */
    private void setUpSetupScene(Group parentGroup, Stage primaryStage) {
        Label fruitGardenHeightLabel = createLabel("Squares horizontally(best is 10-80): ");
        TextField fruitGardenHeightChoice = createTextField();

        Label fruitGardenWidthLabel = createLabel("Squares vertically(best is 10-40): ");
        TextField fruitGardenWidthChoice = createTextField();

        Label choiceBoxLabel = createLabel("Choose maximum speed: ");
        ChoiceBox<String> snakeSpeedChoiceBox = new ChoiceBox<>();
        snakeSpeedChoiceBox.setPrefSize(nodeWidth, nodeHeight);
        snakeSpeedChoiceBox.setItems(FXCollections.observableList(Arrays.asList("Slow", "Medium", "Fast", "Sonic")));
        snakeSpeedChoiceBox.setValue("Medium");

        Button submitButton = createSubmitButton();

        VBox setupVBox = createSetupRows(
                fruitGardenHeightLabel,
                fruitGardenHeightChoice,
                fruitGardenWidthLabel,
                fruitGardenWidthChoice,
                choiceBoxLabel,
                snakeSpeedChoiceBox,
                submitButton);
        parentGroup.getChildren().add(setupVBox);

        listenButton(
                primaryStage,
                fruitGardenHeightChoice,
                fruitGardenWidthChoice,
                snakeSpeedChoiceBox,
                submitButton);
    }

    /**
     * Creates the button that starts the gameScene.
     * @return Returns the PLAY GAME button.
     */
    private Button createSubmitButton() {
        Button submitButton = new Button("PLAY GAME");
        submitButton.setPrefSize(nodeWidth, nodeHeight);
        return submitButton;
    }

    /**
     * Creates a vertical box that holds all the scene's nodes in a conformation of rows.
     * @param fruitGardenHeightLabel Label of the garden height choice textfield node.
     * @param fruitGardenHeightChoice The garden height choice textfield node.
     * @param fruitGardenWidthLabel Label of the garden width choice textfield node.
     * @param fruitGardenWidthChoice The garden width choice textfield node.
     * @param snakeSpeedChoiceBoxLabel The Snake's maximum speed settings' choicebox's label node.
     * @param snakeSpeedChoiceBox The Snake's maximum speed settings' choicebox node.
     * @param submitButton The node of the button that ends the setupScene and starts the gameScene.
     * @return Returns a VBox with all the nodes in it. The nodes are in the order that they are in the constructor.
     */
    private VBox createSetupRows(Label fruitGardenHeightLabel, TextField fruitGardenHeightChoice, Label fruitGardenWidthLabel, TextField fruitGardenWidthChoice, Label snakeSpeedChoiceBoxLabel, ChoiceBox<String> snakeSpeedChoiceBox, Button submitButton) {
        VBox setupVBox = new VBox(
                fruitGardenHeightLabel,
                fruitGardenHeightChoice,
                fruitGardenWidthLabel,
                fruitGardenWidthChoice,
                snakeSpeedChoiceBoxLabel,
                snakeSpeedChoiceBox,
                submitButton);
        setupVBox.setSpacing(10);
        setupVBox.setPrefSize(setupScreenWidth - 100, setupScreenHeight - 50);
        setupVBox.setLayoutX((setupScreenWidth - setupVBox.getPrefWidth())/2);
        setupVBox.setLayoutY((setupScreenHeight - setupVBox.getPrefHeight())/2);
        return setupVBox;
    }

    /**
     * Sets up the button press listener.
     * TODO: create a function to avoid repeated code
     * @param primaryStage The stage for the play.
     * @param fruitGardenHeightChoice The garden height choice textfield node.
     * @param fruitGardenWidthChoice The garden width choice textfield node.
     * @param snakeSpeedChoiceBox The Snake's maximum speed settings' choicebox node.
     * @param submitButton The node of the button that ends the setupScene and starts the gameScene.
     */
    private void listenButton(Stage primaryStage, TextField fruitGardenHeightChoice, TextField fruitGardenWidthChoice, ChoiceBox<String> snakeSpeedChoiceBox,  Button submitButton) {
        submitButton.setOnAction(event -> {
            boolean heightFits = checkFit(fruitGardenHeightChoice.getText());
            boolean widthFits = checkFit(fruitGardenWidthChoice.getText());
            if(!heightFits || !widthFits){
                showPopupDialog(primaryStage);
            }else{
                int maxPeriodInMS;
                switch(snakeSpeedChoiceBox.getValue()){
                    case "Slow":
                        maxPeriodInMS = 400;
                        break;
                    case "Medium":
                        maxPeriodInMS = 200;
                        break;
                    case "Fast":
                        maxPeriodInMS = 100;
                        break;
                    case "Sonic":
                        maxPeriodInMS = 75;
                        break;
                    default:
                        maxPeriodInMS = 200;
                }
                setGameScene(primaryStage, fruitGardenHeightChoice, fruitGardenWidthChoice, maxPeriodInMS);
            }
        });
    }

    /**
     * Sets the gameScene.
     * @param primaryStage The stage for this and the next scene.
     * @param fruitGardenHeightChoice The garden height choice textfield node.
     * @param fruitGardenWidthChoice The garden width choice textfield node.
     * @param minimumGametickLengthInMS The minimum gametick length in milliseconds.
     */
    private void setGameScene(Stage primaryStage, TextField fruitGardenHeightChoice, TextField fruitGardenWidthChoice, int minimumGametickLengthInMS) {
        primaryStage.close();
        primaryStage.setScene(new GameScene(
                new Group(),
                primaryStage,
                new GameSettings(
                        Math.round((int) Double.parseDouble(fruitGardenHeightChoice.getText())),
                        Math.round((int) Double.parseDouble(fruitGardenWidthChoice.getText())),
                        minimumGametickLengthInMS))
        );
    }

    /**
     * Creates a label node with the specified text and predefined font size and node size.
     * @param text Text to be displayed on the label.
     * @return Returns a label node.
     */
    private Label createLabel(String text){
        Label label = new Label(text);
        label.setFont(new Font(20));
        label.setPrefSize(nodeWidth, nodeHeight);
        return label;
    }

    /**
     * Creates a textField node with predefined input value, font size and node size.
     * @return Returns a textField node.
     */
    private TextField createTextField(){
        TextField textField = new TextField("10");
        textField.setFont(new Font(20));
        textField.setPrefSize(nodeWidth, nodeHeight);
        return textField;
    }

    /**
     * Returns true, if the textfields contain fit content for the fruitgarden's dimensional parameters - false otherwise.
     * @param text The text in the textfield.
     * @return Returns a result, stating, if the textfield's content is fit to set fruitgarden's parameters.
     */
    private boolean checkFit(String text) {
        double textDoubleValue;
        try{
            textDoubleValue = Double.parseDouble(text);
        }catch (NumberFormatException e){
            return false;
        }
        return !(textDoubleValue < 8 || textDoubleValue > 100);
    }

    /**
     * Creates a new popup messagebox that notifies the user of incorrect input in the setupScene.
     * @param primaryStage The stage for the scene.
     */
    private void showPopupDialog(Stage primaryStage) {
        final Stage dialogBox = new Stage();
        Group dialogBoxGroup = new Group();
        dialogBox.initModality(Modality.APPLICATION_MODAL);
        dialogBox.initOwner(primaryStage);
        Text dialog = new Text("Please choose appropriate integer values for square amounts!");
        dialog.setFont(new Font(25.0));
        dialog.relocate(25, 25);
        dialogBoxGroup.getChildren().add(dialog);
        Scene dialogScene = new Scene(dialogBoxGroup, 750, 100);
        dialogBox.setScene(dialogScene);
        dialogBox.getIcons().add(popupIcon);
        dialogBox.setTitle("ERROR");
        dialogBox.show();
    }
}
