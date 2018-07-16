package gamelogic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 *Fruit in the fruitgarden.
 * TODO:add properties to create different fruits for different awards
 */
public class Fruit {
    /**
     * Node for the Fruit image.
     */
    private ImageView fruitImageView;
    private int xCoord;
    private int yCoord;
    /**
     * The square that Fruit lies on top of.
     */
    private Square fruitSquare;
    /**
     * All forms(images) of Fruit.
     */
    private static List<Image> allFruitImages = new ArrayList<>(3);
    static{
        allFruitImages.add(new Image("banana.jpg"));
        allFruitImages.add(new Image("apple.jpg"));
        allFruitImages.add(new Image("orange.png"));
    }

    /**
     * Constructor for the Fruit object. A fruit holds it's imageview, coordinates, square and all the images it can have.
     *
     * @param xCoord X-coordinate of the Fruit in the fruitgarden.
     * @param yCoord Y-coordinate of the Fruit in the fruitgarden.
     * @param squareGrid The fruitgarden, that will hold the fruit.
     */
    Fruit(int xCoord, int yCoord, SquareGrid squareGrid){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.fruitSquare = squareGrid.getSquareByCoords(xCoord, yCoord);
        this.fruitImageView = new ImageView(chooseRandomImage());
    }

    /**
     * Getter method for the X-coordinate of Fruit.
     * @return Returns an integer value representing the Fruit X-coordinate in the fruitgarden.
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * Getter method for the Y-coordinate of Fruit.
     * @return Returns an integer value representing the Fruit Y-coordinate in the fruitgarden.
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     * Getter method for the square, that the Fruit lies on.
     * @return Returns the Fruit's square.
     */
    Square getFruitSquare() {
        return fruitSquare;
    }

    /**
     * Getter method for getting the Fruit's imageview node.
     * @return Returns the Fruit's imageview node.
     */
    public ImageView getFruitImageView() {
        return fruitImageView;
    }

    /**
     * Chooses a random form(image) from all the forms(images) of Fruit.
     * @return Returns a randomly chosen Fruit image.
     */
    private Image chooseRandomImage() {
        return allFruitImages.get((int) Math.round(Math.random()*2));
    }
}
