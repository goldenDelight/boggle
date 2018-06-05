package models;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.shape.StrokeType.INSIDE;

public class GraphicsModel {

    private final static GraphicsModel model = new GraphicsModel();

    public static GraphicsModel getModel(){
        return model;
    }

    Pane graphicsPane;
    List<Circle> circles;
    Group lines;
    private double lastX;
    private double lastY;

    public void initialize(Pane pane){
        graphicsPane = pane;

        graphicsPane.toFront();
        graphicsPane.setMouseTransparent(true);
        circles = new ArrayList();
        lines = new Group();
    }

    public void makeCircle(BoggleButton b){

//        System.out.println(b.getX());
//        System.out.println(b.getY());
//        System.out.println(b.getLayoutX());
//        System.out.println(b.getLayoutY());
//        b.setAlignment(Pos.CENTER);
        b.setButtonBounds();

        double xCord = b.getLayoutX() + b.getX();// + b.getX();
        double yCord = b.getLayoutY() + b.getY();// + b.getY();

        Circle circle = new Circle(xCord, yCord, 45, Color.TRANSPARENT);
        circle.setStrokeWidth(5);
        circle.setStrokeType(INSIDE);
        circle.setMouseTransparent(true);
//        circle.relocate(xCord, yCord);

        circles.add(circle);
        graphicsPane.getChildren().removeAll(circles);

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

        graphicsPane.getChildren().addAll(circles);
    }


    public void makeArrows(BoggleButton b){

    }

    public void updateLast(double x, double y){
        this.lastX = x;
        this.lastY = y;
    }


    public void clearCircles(){
        circles.clear();
        graphicsPane.getChildren().clear();
    }
}
