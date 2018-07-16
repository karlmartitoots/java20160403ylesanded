package gamelogic;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

class Square extends Rectangle{
    private final int xCoord;
    private final int yCoord;

    Square(double width, double height, Paint fill, int xCoord, int yCoord) {
        super(width, height, fill);
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.setStroke(Color.BLACK);
    }

    int getxCoord() {
        return xCoord;
    }

    int getyCoord() {
        return yCoord;
    }

    boolean equals(Square other){
        return ((this.xCoord == other.getxCoord()) && (this.yCoord == other.getyCoord()));
    }

    @Override
    public String toString() {
        return "Square{" +
                "xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                "} ";
    }
}
