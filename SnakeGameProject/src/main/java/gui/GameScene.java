package gui;

import gamelogic.Direction;
import gamelogic.Fruit;
import gamelogic.SquareGrid;
import gamelogic.Snake;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import settings.GameSettings;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * The scene for the game.
 * TODO: add game pause option
 * TODO: add decorations to the main scene for effect
 * TODO: make resizable
 * TODO: (longterm) implement players, server/client communication and multiplayer game modes
 */
class GameScene extends Scene {
    private GameSettings gameSettings;
    private Snake snake;
    private SquareGrid fruitGarden;

    private int maximumTickLengthInMS;
    private int fruitEatenAmount = 0;
    private Direction currentDirectionOfMovement = Direction.RIGHT;

    /**
     * The constructor of gameScene sets up and conducts the play until the game is over.
     *
     * @param parentGroup The parent group of the gameScene.
     * @param primaryStage The stage for the game's play.
     * @param gameSettings The game's settings.
     */
    GameScene(Group parentGroup, Stage primaryStage, GameSettings gameSettings) {
        super(parentGroup);
        this.gameSettings = gameSettings;
        this.maximumTickLengthInMS = gameSettings.getMinimumGametickLengthInMS();

        final ScheduledExecutorService gameTickService = Executors.newScheduledThreadPool(1);
        //A tribute to Magic values: https://www.youtube.com/watch?v=Qtb11P1FWnc
        long initialGameTickLength = 800L;
        double stageXAdjust = 19;
        double stageYAdjust = 48;

        prepareFruitGarden(parentGroup);

        playGameTick(primaryStage, gameTickService, initialGameTickLength, parentGroup);

        listenForArrowKeys();

        primaryStage.setOnCloseRequest(event -> gameTickService.shutdown());

        playGameScene(primaryStage, stageXAdjust, stageYAdjust);
    }

    /**
     * Prepares the fruitgarden for the play.
     * @param parentGroup The parent group for the play.
     */
    private void prepareFruitGarden(Group parentGroup) {
        fruitGarden = new SquareGrid(this.gameSettings);
        fruitGarden.placeAllSquaresOnGroup(parentGroup);
        snake = fruitGarden.initiateSnake(fruitGarden);
        placeFirstFruit(parentGroup, fruitGarden);
    }

    /**
     * Sets the properties of the stage for the play and sets the play.
     * @param primaryStage The stage for the play.
     * @param stageXAdjust Horizontal pixel length adjustment for the stage size.
     * @param stageYAdjust Vertical pixel length adjustment for the stage size.
     */
    private void playGameScene(Stage primaryStage, double stageXAdjust, double stageYAdjust) {
        setStageSize(primaryStage, stageXAdjust, stageYAdjust);
        primaryStage.getIcons().clear();
        primaryStage.getIcons().add(new Image("snakeIcon.jpg"));
        String gameTitle = "Snake Beta 1.0";
        primaryStage.setTitle(gameTitle);
        primaryStage.setScene(this);
        primaryStage.show();
    }

    /**
     * Fixates the stages size to make the scene unresizable.
     * @param primaryStage The stage for the gameScene play.
     * @param stageXAdjust Horizontal pixel length adjustment for the stage size.
     * @param stageYAdjust Vertical pixel length adjustment for the stage size.
     */
    private void setStageSize(Stage primaryStage, double stageXAdjust, double stageYAdjust) {
        primaryStage.setMinWidth(gameSettings.getAmountOfSquaresInXDimension()* gameSettings.getSquareWidth() + stageXAdjust);
        primaryStage.setMaxWidth(gameSettings.getAmountOfSquaresInXDimension()* gameSettings.getSquareWidth() + stageXAdjust);
        primaryStage.setMinHeight(gameSettings.getAmountOfSquaresInYDimension()* gameSettings.getSquareHeight() + stageYAdjust);
        primaryStage.setMaxHeight(gameSettings.getAmountOfSquaresInYDimension()* gameSettings.getSquareHeight() + stageYAdjust);
    }

    /**
     * Sets up an eventListener that listenes for arrowkey presses. Also adds in the NUMPAD keys.
     * TODO: create a function for the cases to clean up repeating code
     */
    private void listenForArrowKeys() {
        setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if(keyCode.isArrowKey()){
                switch (keyCode){
                    case KP_LEFT:
                    case LEFT:
                        if(!currentDirectionOfMovement.equals(Direction.RIGHT)) {
                            snake.changeDirectionOfFacing(Direction.LEFT);
                        }
                        break;
                    case KP_RIGHT:
                    case RIGHT:
                        if(!currentDirectionOfMovement.equals(Direction.LEFT)) {
                            snake.changeDirectionOfFacing(Direction.RIGHT);
                        }
                        break;
                    case KP_UP:
                    case UP:
                        if(!currentDirectionOfMovement.equals(Direction.DOWN)) {
                            snake.changeDirectionOfFacing(Direction.UP);
                        }
                        break;
                    case KP_DOWN:
                    case DOWN:
                        if(!currentDirectionOfMovement.equals(Direction.UP)) {
                            snake.changeDirectionOfFacing(Direction.DOWN);
                        }
                        break;
                }
            }
        });
    }

    /**
     * Places the first fruit in the garden.
     * @param parentGroup The parent group for nodes.
     * @param fruitGarden The fruitgarden for the Snake.
     */
    private void placeFirstFruit(Group parentGroup, SquareGrid fruitGarden) {
        fruitGarden.placeFirstFruit();
        createNextFruitNode(parentGroup, fruitGarden);
    }

    /**
     * Plays a single gametick of the game.
     * @param primaryStage The stage for the gameScene play.
     * @param gameTickService The executorpool for the gametick thread.
     * @param gameTickLengthInMS Length of the gametick in milliseconds.
     * @param parentGroup The parent group of the nodes on the gameScene.
     */
    private void playGameTick(Stage primaryStage, ScheduledExecutorService gameTickService, long gameTickLengthInMS, Group parentGroup) {
        gameTickService.schedule((
                (Runnable) () ->
                        Platform.runLater(() ->
                                playNextGameTick(primaryStage, gameTickService, gameTickLengthInMS, parentGroup))),
                gameTickLengthInMS,
                MILLISECONDS);
    }

    /**
     * Plays the next gametick and speeds up if possible.
     * @param primaryStage The stage for the gameScene.
     * @param gameTickService The executorpool for the gametick thread.
     * @param initialGameTickLengthInMS The initial length of a gametick in milliseconds.
     * @param parentGroup The parent group that holds all the nodes in the gameScene.
     */
    private void playNextGameTick(Stage primaryStage, ScheduledExecutorService gameTickService, long initialGameTickLengthInMS, Group parentGroup) {
        if (snake.isAlive()) {
            moveSnake();
            changeSnakesDirectionOfMovement(snake.getDirectionOfFacing());
            if(snake.ateFruit()){
                resetFruit(parentGroup);
                playGameTick(primaryStage, gameTickService, speedUpIfPossible(initialGameTickLengthInMS), parentGroup);
            }else{
                playGameTick(primaryStage, gameTickService, initialGameTickLengthInMS, parentGroup);
            }
        } else {
            setDeathScene(primaryStage, gameTickService);
        }
    }

    /**
     * Resets the fruit. The method replaces the fruit node, digests the fruit and increments the amount of fruits eaten.
     * @param parentGroup The parent group of the gameScene.
     */
    private void resetFruit(Group parentGroup) {
        replaceFruitNode(parentGroup, fruitGarden);
        snake.digestFruit();
        fruitEatenAmount += 1;
    }

    /**
     * Sets the deathScene; triggers when Snake deadness is detected.
     * @param primaryStage The stage for this and the next scene.
     * @param gameTickService The gametick service, that will have to be shutdown after the scene ends.
     */
    private void setDeathScene(Stage primaryStage, ScheduledExecutorService gameTickService) {
        primaryStage.close();
        gameTickService.shutdown();
        primaryStage.setScene(new DeathScene(
                new Group(),
                primaryStage,
                fruitEatenAmount,
                maximumTickLengthInMS
                )
        );
    }

    /**
     * Removes the fruits node from the group.
     * @param parentGroup The parent group of all the nodes in the setupScene.
     * @param squareGrid The Snake's fruitgarden.
     */
    private void removeFruitNode(Group parentGroup, SquareGrid squareGrid){
        parentGroup.getChildren().remove(squareGrid.getFruit().getFruitImageView());
    }

    /**
     * Creates the next fruit node on the scene's group.
     * @param parentGroup The parent group of all the nodes in the setupScene.
     * @param squareGrid The Snake's fruitgarden.
     */
    private void createNextFruitNode(Group parentGroup, SquareGrid squareGrid){
        squareGrid.placeNextFruit();
        Fruit nextFruit = squareGrid.getFruit();
        ImageView fruitImageView = nextFruit.getFruitImageView();
        parentGroup.getChildren().add(fruitImageView);
        fruitImageView.setX(nextFruit.getxCoord()* gameSettings.getSquareWidth() + 1);
        fruitImageView.setY(nextFruit.getyCoord()* gameSettings.getSquareHeight() + 1);
    }

    /**
     * Replaces the fruis node in the scene's group.
     * @param parentGroup The parent group of all the nodes in the setupScene.
     * @param squareGrid The Snake's fruitgarden.
     */
    private void replaceFruitNode(Group parentGroup, SquareGrid squareGrid){
        removeFruitNode(parentGroup, squareGrid);
        createNextFruitNode(parentGroup, squareGrid);
    }

    /**
     * Runs a snakes moving interval.
     */
    private void moveSnake(){
        snake.snakeMoveInterval();
    }

    /**
     * Tries to speed up the snake's speed, if maximum speed has not been achieved yet.
     * @param currentTickLength The current gametick length in milliseconds.
     * @return Returns the new gametick length.
     */
    private long speedUpIfPossible(long currentTickLength){
        if(currentTickLength > maximumTickLengthInMS) {
            return Math.round(currentTickLength * (fruitEatenAmount + 1) / (fruitEatenAmount + 2));
        }else{
            return currentTickLength;
        }
    }

    /**
     * Changes the Snake's direction of movement.
     * @param newDirection The direction to change over to.
     */
    private void changeSnakesDirectionOfMovement(Direction newDirection){
        currentDirectionOfMovement = newDirection;
    }
}
