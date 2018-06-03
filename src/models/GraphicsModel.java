package models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

import static javafx.scene.shape.StrokeType.INSIDE;

public class GraphicsModel {

    private final static GraphicsModel model = new GraphicsModel();

    public static GraphicsModel getModel(){
        return model;
    }

    Pane graphicPane;

    public void initialize(Pane pane){
        graphicPane = pane;

    }

    public Circle makeCircle(BoggleButton b){

        Circle circle = new Circle(b.getX(), b.getY(),45, Color.TRANSPARENT);
        circle.setStrokeWidth(5);
        circle.setStrokeType(INSIDE);
        circle.setStroke(Color.STEELBLUE);
        circle.setMouseTransparent(true);
        return circle;
    }


    public void addCircle(Circle circle){
        graphicPane.getChildren().add(circle);
    }
}
