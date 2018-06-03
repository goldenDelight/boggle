package models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static javafx.scene.shape.StrokeType.INSIDE;

public class CircleModel {

    private final static CircleModel model = new CircleModel();
    public static CircleModel getModel(){
        return model;
    }


    private void drawCircle(double x, double y, Color color) {
        Circle circle = new Circle(x,y,45, Color.TRANSPARENT);
        circle.setStrokeWidth(5);
        circle.setStrokeType(INSIDE);
        circle.setStroke(color);
        circle.setMouseTransparent(true);
//        graphics.add(circle);

    }

}
