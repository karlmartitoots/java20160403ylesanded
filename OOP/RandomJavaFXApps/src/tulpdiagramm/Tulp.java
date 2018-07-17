package tulpdiagramm;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Tulp extends VBox{
    private Rectangle täissammas;
    private Label tulbanumber;
    private Paint värv;

    public Tulp(int randInt, double tulbalaius, Paint värv) {
        Rectangle tühisammas = new Rectangle(tulbalaius, (100 - randInt) * 2);
        tühisammas.setFill(Color.WHITESMOKE);
        this.täissammas = new Rectangle(tulbalaius, randInt*2);
        this.täissammas.setFill(värv);
        this.tulbanumber = new Label(String.valueOf(randInt));
        this.tulbanumber.setTextFill(värv);
        this.värv = värv;
        this.getChildren().addAll(tühisammas, täissammas, tulbanumber);
    }

    public Paint getVärv(){
        return this.värv;
    }

    void setVärv(Paint värv){
        this.täissammas.setFill(värv);
        this.tulbanumber.setTextFill(värv);
        this.värv = värv;
    }
}
