package gamelogic;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import settings.GameSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that holds the fruitgarden, snake and the fruit.
 * TODO: add logic to fix a bug, where if all the squares are filled, the game is not over and a free square for the Fruit is still being tried to find.
 */
public class SquareGrid {
    private List<Square> allGridSquares = new ArrayList<>();
    private final GameSettings gameSettings;
    private Fruit fruit;
    private Snake snake;

    /**
     * A constructor method for the fruitgarden.
     *
     * @param gameSettings The settings for the game.
     */
    public SquareGrid(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        createAllSquares();
    }

    /**
     * Creates all the squares in the fruitgarden.
     */
    private void createAllSquares() {
        for (int Y = 0; Y < gameSettings.getAmountOfSquaresInYDimension(); Y++) {
            for (int X = 0; X < gameSettings.getAmountOfSquaresInXDimension(); X++) {
                Square newSquare = new Square(gameSettings.getSquareWidth(),
                        gameSettings.getSquareHeight(),
                        Color.WHITE,
                        X, Y);
                newSquare.relocate(X* gameSettings.getSquareWidth(), Y* gameSettings.getSquareHeight());
                allGridSquares.add(newSquare);
            }
        }
    }

    /**
     * Places all the squares on their Parent Group for the first time.
     * @param parentGroup The Parent group of the squares.
     */
    public void placeAllSquaresOnGroup(Group parentGroup){
        parentGroup.getChildren().addAll(allGridSquares);
    }

    /**
     * Simplifies getting a specific square in the fruitgarden by using it's coordinates.
     * @param squareXCoord The squares x-coordinate value.
     * @param squareYCoord The squares y-coordinate value.
     * @return Returns the Square object that has the specific coordinates.
     */
    Square getSquareByCoords(int squareXCoord, int squareYCoord){
        return this.allGridSquares.get(translate2DCoords1D(squareXCoord, squareYCoord));
    }

    /**
     * Translates the two dimensional coordinates into one dimension, as they are set in the squareGrid List of squares.
     * @param squareXCoordValue The squares x-coordinate value.
     * @param squareYCoordValue The squares y-coordinate value.
     * @return Returns the integer value for the position of the square in squareGrids List of squares with the specified coordinates.
     */
    int translate2DCoords1D(int squareXCoordValue, int squareYCoordValue){
        return (squareYCoordValue* gameSettings.getAmountOfSquaresInXDimension() + squareXCoordValue);
    }

    /**
     * Retrives a x-coordinate on the squareGrid from a 1D coordinate.
     * @param square1DCoordinate The square's one dimensional coordinate.
     * @return Returns the integer value of the x-coordinate.
     */
    private int translate1DCoordX(int square1DCoordinate){
        return (square1DCoordinate%gameSettings.getAmountOfSquaresInXDimension());
    }

    /**
     * Retrives a y-coordinate on the squareGrid from a 1D coordinate.
     * @param square1DCoordinate The squares one dimensional coordinate.
     * @return Returns the integer value of the y-coordinate.
     */
    private int translate1DCoordY(int square1DCoordinate){
        return (square1DCoordinate/gameSettings.getAmountOfSquaresInXDimension());
    }

    /**
     * Creates a Snake object and returns it.
     * @param squareGrid The fruitgarden to pass along to the Snake's constructor method.
     * @return Returns the freshly created Snake.
     */
    public Snake initiateSnake(SquareGrid squareGrid) {
        return this.snake = new Snake(gameSettings, squareGrid);
    }

    /**
     * Generates a random Fruit position and checks if the Fruit can be placed there. If the Fruit can be placed, then creates
     * that new Fruit. If the position is occupied, then generates a new random position and tries again.
     */
    public void placeNextFruit(){
        int fruit1DCoordinate = generateRandom1DFruitCoordinate(gameSettings.getAmountOfSquaresInXDimension(), gameSettings.getAmountOfSquaresInYDimension());
        if(fruitCanBePlaced(allGridSquares.get(fruit1DCoordinate))){
            this.fruit = new Fruit(translate1DCoordX(fruit1DCoordinate), translate1DCoordY(fruit1DCoordinate), this);
        }else{
            placeNextFruit();
        }
    }

    /**
     * Same method as placing any next fruit. Places a fruit on a square that is not occupied by the Snake.
     */
    public void placeFirstFruit(){
        placeNextFruit();
    }

    /**
     * Generates a random one-dimensional coordinate for placing a Fruit.
     * @param amountOfSquaresInX Amount of squares on the grid horizontally.
     * @param amountOfSquaresInY Amount of squares on the grid vertically.
     * @return Returns random one-dimensional Fruit coordinates.
     */
    private int generateRandom1DFruitCoordinate(int amountOfSquaresInX, int amountOfSquaresInY){
        return (int) Math.round((Math.random()*(amountOfSquaresInX*amountOfSquaresInY - 1)));
    }

    /**
     * Checks if the Snake's body is occupying the square that the Fruit could be placed on.
     * @param squareToCheck The square to check.
     * @return Returns true, if the square is Snake's body part - false otherwise.
     */
    private boolean fruitCanBePlaced(Square squareToCheck){
        return !snake.getSnakeSquares().contains(squareToCheck);
    }

    /**
     * Getter method for getting the current Fruit object.
     * @return Returns the current Fruit.
     */
    public Fruit getFruit() {
        return fruit;
    }

    /**
     * Getter method for getting the squareGrid's List of squares.
     * @return Returns the Snake's fruitgarden.
     */
    List<Square> getFruitGarden() {
        return allGridSquares;
    }
}
