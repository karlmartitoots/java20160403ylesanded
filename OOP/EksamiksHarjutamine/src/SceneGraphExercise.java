import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SceneGraphExercise extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage st) {
        GridPane p1 = new GridPane();
        BorderPane p2 = new BorderPane();
        Button n1 = new Button("N1");
        Button n2 = new Button("N2");
        Circle k1 = new Circle(100, 30, 10, Color.RED);

        Rectangle k2 = new Rectangle(50, 50, 100, 100);
        p2.setLeft(n1);
        p2.setCenter(n2);

        p1.add(p2, 1, 1);
        p1.add(k1, 1, 2);
        p1.add(k2, 2, 2);

        Scene s1 = new Scene(p1, 400, 300);
        st.setTitle("Eksam");
        st.setScene(s1);
        st.show();

        n1.setOnKeyPressed(event -> n1.setText("N1 -> 2"));
        n2.setOnMouseClicked(event -> k1.setFill(Color.PINK));

        st.heightProperty().addListener(new ChangeListener<Object>(){
            public void changed(ObservableValue<?> o, Object vana, Object uus){
                if((Double)uus > 500) k1.setEffect(new Shadow());
            }
        });
        k2.heightProperty().bind(st.widthProperty().divide(3));
    }

}
