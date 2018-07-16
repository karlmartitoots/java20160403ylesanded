package gamelogic;

import javafx.scene.paint.Color;
import settings.GameSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the snake object.
 */
public class Snake {
    private int initialSnakeLength;
    private SquareGrid fruitGarden;
    private List<Square> snakeSquares = new ArrayList<>();
    /**
     * The direction, where the snake is facing towards. Not to be confused with the direction that the snake is moving towards.
     */
    private Direction directionOfFacing;
    private GameSettings gameSettings;
    /**
     * Shows if the Snake is alive or not. Initially true.
     */
    private boolean isAlive = true;
    /**
     * Shows if the Snake has just eaten a fruit. Initially false.
     */
    private boolean ateFruit = false;

    /**
     * The constructor method for the Snake.
     *
     * @param gameSettings The settings for the game.
     * @param squareGrid The Snake's fruitgarden.
     */
    Snake(GameSettings gameSettings, SquareGrid squareGrid){
        this.gameSettings = gameSettings;
        this.initialSnakeLength = 7;
        this.fruitGarden = squareGrid;
        this.directionOfFacing = Direction.RIGHT;
        placeSnakeInMiddleOfFruitGarden(gameSettings, squareGrid);
    }

    /**
     * Calculates the coordinates and places the snake in the middle of the garden.
     * @param gameSettings Settings for the game.
     * @param squareGrid The Snake's fruitgarden.
     */
    private void placeSnakeInMiddleOfFruitGarden(GameSettings gameSettings, SquareGrid squareGrid) {
        int startX = (gameSettings.getAmountOfSquaresInXDimension() + initialSnakeLength)/2 - 1;
        int startY = (gameSettings.getAmountOfSquaresInYDimension())/2 - 1;
        List<Square> fruitGardenSquares = squareGrid.getFruitGarden();
        for (int i = 0; i < initialSnakeLength; i++) {
            Square snakeSquare = fruitGardenSquares.get(squareGrid.translate2DCoords1D(startX - i, startY));
            snakeSquares.add(snakeSquare);
            snakeSquare.setFill(Color.GRAY);
        }
    }

    /**
     * Changes the direction, that the Snake is facing towards.
     * @param newDirection The new direction to face.
     */
    public void changeDirectionOfFacing(Direction newDirection){
        this.directionOfFacing = newDirection;
    }

    /**
     * One Snake moving cycle. Tries to move the snake one square forwards and checks for a Fruit. If the snake hits it's body, the game is over.
     */
    public void snakeMoveInterval(){
        Square nextSquare = getNextSquare();
        checkIfTheresFruit(nextSquare);
        tryToMoveSnakeOrDieTrying(nextSquare);
    }

    /**
     * Checks if there is a fruit on the next square to move to. The Snake eats the fruit if it returns positive.
     * @param nextSquare The next square for the Snake to move to.
     */
    private void checkIfTheresFruit(Square nextSquare) {
        if(nextSquare.equals(fruitGarden.getFruit().getFruitSquare())){
            eatFruit();
        }
    }

    /**
     * Tries to move the Snake forwards or kills the Snake, if it hits it's body.
     * @param nextSquare The next square for the Snake to move to.
     */
    private void tryToMoveSnakeOrDieTrying(Square nextSquare) {
        if(canMove(nextSquare)){
            moveHead(nextSquare);
            tryToGrow();
        }else{
            this.squish();
        }
    }

    /**
     * Checks, if a Fruit has been eaten. Grows when the check returns positive.
     */
    private void tryToGrow() {
        if(!ateFruit){
            moveTail();
        }else{
            growInLength();
        }
    }

    /**
     * Tries to expand the snake to the next square.
     * @param nextSquare The next square for the Snake expand to.
     */
    private void moveHead(Square nextSquare) {
        snakeSquares.add(0, nextSquare);
        nextSquare.setFill(Color.GRAY);
    }

    /**
     * When a fruit was not eaten in the Snake's moving interval, it's tail will be shortened.
     */
    private void moveTail() {
        Square lastSquare = snakeSquares.remove(initialSnakeLength);
        lastSquare.setFill(Color.WHITE);
    }

    /**
     * Increments the Snake's length by one square length.
     */
    private void growInLength() {
        initialSnakeLength += 1;
    }

    /**
     * Checks, if the Snake can move to the specified square.
     * @param nextSquare The square for the Snake to move to next.
     * @return Returns positive, if the Snake's body isn't in the way - negative otherwise.
     */
    private boolean canMove(Square nextSquare){
        return !snakeSquares.contains(nextSquare);
    }

    /**
     * Sets the flag for having eating a Fruit.
     */
    private void eatFruit(){
        this.ateFruit = true;
    }

    /**
     * Uses the Snake head's position to scan for walls to warp through and retrieves the next square for the Snake to move to.
     * @return Returns the Snake's next square.
     */
    private Square getNextSquare(){
        Square snakeHead = snakeSquares.get(0);
        int headXCoord = snakeHead.getxCoord();
        int headYCoord = snakeHead.getyCoord();

        return scanForWalls(headXCoord, headYCoord);
    }

    /**
     * Checks if there's a wall to warp through and calculates the coordinates for the next square.
     * Holds the wall warping logic.
     * @param headXCoord The Snake head's x-coordinate.
     * @param headYCoord The Snake head's y-coordinate.
     * @return Returns the Snake's next square to move to.
     */
    private Square scanForWalls(int headXCoord, int headYCoord) {
        switch (directionOfFacing){
            case UP:
                if(headYCoord == 0){
                    return fruitGarden.getSquareByCoords(headXCoord, gameSettings.getAmountOfSquaresInYDimension() - 1);
                }else{
                    return fruitGarden.getSquareByCoords(headXCoord, headYCoord - 1);
                }
            case DOWN:
                if(headYCoord == (gameSettings.getAmountOfSquaresInYDimension() - 1)){
                    return fruitGarden.getSquareByCoords(headXCoord, 0);
                }else{
                    return fruitGarden.getSquareByCoords(headXCoord, headYCoord + 1);
                }
            case LEFT:
                if(headXCoord == 0){
                    return fruitGarden.getSquareByCoords(gameSettings.getAmountOfSquaresInXDimension() - 1, headYCoord);
                }else{
                    return fruitGarden.getSquareByCoords(headXCoord - 1, headYCoord);
                }
            case RIGHT:
                if(headXCoord == (gameSettings.getAmountOfSquaresInXDimension() - 1)){
                    return fruitGarden.getSquareByCoords(0, headYCoord);
                }else{
                    return fruitGarden.getSquareByCoords(headXCoord + 1, headYCoord);
                }
        }
        return null;
    }

    /**
     * Checks if the Snake is still alive.
     * @return Returns positive if the Snake's alive - negative otherwise.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Checks if the Snake has eaten a Fruit lately.
     * @return Returns positive if the Snake has eaten a Fruit - negative otherwise.
     */
    public boolean ateFruit() {
        return ateFruit;
    }

    /**
     * Gets a List of Snake body's squares in the fruitgarden.
     * @return Returns the Snake body's squares.
     */
    List<Square> getSnakeSquares() {
        return snakeSquares;
    }

    /**
     * Gets the direction, where the Snake is currently facing.
     * @return Returns the Snake's direction of facing.
     */
    public Direction getDirectionOfFacing() {
        return directionOfFacing;
    }

    /**
     * Kills the snake.
     */
    private void squish() {
        isAlive = false;
    }

    /**
     * Digests the eaten fruit, so that the Snake can eat again.
     */
    public void digestFruit() {
        this.ateFruit = false;
    }
}
