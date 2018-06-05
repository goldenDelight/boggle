package models;

import javafx.geometry.Pos;
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

//        b.setButtonBounds();
        b.setAlignment(Pos.CENTER);
        final double PADDING_ADJUST = 50;

        double xCord = b.getLayoutX() + b.getX();
        double yCord = b.getLayoutY() + b.getY();

        Circle circle = new Circle(b.getLayoutX() + PADDING_ADJUST, b.getLayoutY() + PADDING_ADJUST, 40, Color.TRANSPARENT);
        circle.setStrokeWidth(7);
        circle.setStrokeType(INSIDE);
        circle.setMouseTransparent(true);
//        circle.relocate(b.getLayoutX(), b.getLayoutY());

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
