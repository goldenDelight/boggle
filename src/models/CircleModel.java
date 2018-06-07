package models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.shape.StrokeType.INSIDE;

public class CircleModel {

    private final static CircleModel model = new CircleModel();
    public static CircleModel getModel(){
        return model;
    }


    private List<Circle> circles = new ArrayList<>();

    public void makeCircle(double x, double y){

        Circle circle = new Circle(x, y, 40, Color.TRANSPARENT);
        circle.setStrokeWidth(7);
        circle.setStrokeType(INSIDE);
        circle.setMouseTransparent(true);

        circles.add(circle);

        this.colorCircles();
    }

    public void colorCircles(){

        for(Circle c : circles) {

            c.setStroke(Color.STEELBLUE);

            if (c.equals(circles.get(0))) {
                c.setStroke(Color.TOMATO);
            } else if (c.equals(circles.get(circles.size() - 1))) {
                c.setStroke(Color.SPRINGGREEN);
            } else {
                c.setStroke(Color.STEELBLUE);
            }
        }
    }

    public List<Circle> getCircles(){
        return circles;
    }

    public void clearCircles(){
        circles.clear();
    }

}
