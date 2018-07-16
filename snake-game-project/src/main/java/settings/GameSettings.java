package settings;

/**
 * A class that holds and passes along the game settings.
 * TODO: add settings for changing colour themes
 * TODO: (longterm) add new gamemodes
 */
public class GameSettings {
    private final int amountOfSquaresInXDimension;
    private final int amountOfSquaresInYDimension;
    private final double squareWidth = 20;
    private final double squareHeight = 20;
    private final int minimumGametickLengthInMS;

    /**
     * A constructor method to create a Settings object. The Settings object holds different setting for the game.
     *
     * @param amountOfSquaresInXDimension The amount of squares in the x-dimension of the fruitgarden.
     * @param amountOfSquaresInYDimension The amount of squares in the y-dimension of the fruitgarden.
     * @param minimumGametickLengthInMS The minimum length of a gametick in milliseconds time.
     */
    public GameSettings(int amountOfSquaresInXDimension, int amountOfSquaresInYDimension, int minimumGametickLengthInMS) {
        this.amountOfSquaresInXDimension = amountOfSquaresInXDimension;
        this.amountOfSquaresInYDimension = amountOfSquaresInYDimension;
        this.minimumGametickLengthInMS = minimumGametickLengthInMS;
    }

    /**
     * Getter method for getting the minimum gametick length in milliseconds.
     * @return Returns the minimum gametick length.
     */
    public int getMinimumGametickLengthInMS() {
        return minimumGametickLengthInMS;
    }

    /**
     * Getter method for get an integral square's width in pixel length values.
     * @return Returns the squares width in pixels.
     */
    public double getSquareWidth() {
        return squareWidth;
    }

    /**
     * Getter method for get an integral square's height in pixel length values.
     * @return Returns the squares height in pixels.
     */
    public double getSquareHeight() {
        return squareHeight;
    }

    /**
     * Getter method for retrieving the grids horizontal length in unit square side lengths.
     * @return Returns the amount of squares in the x dimension
     */
    public int getAmountOfSquaresInXDimension() {
        return amountOfSquaresInXDimension;
    }

    /**
     * Getter method for retrieving the grids vertical length in unit square side lengths.
     * @return Returns the amount of squares in the y dimension
     */
    public int getAmountOfSquaresInYDimension() {
        return amountOfSquaresInYDimension;
    }
}
