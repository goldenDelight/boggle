package models;

import javafx.scene.Group;
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

    Pane graphicsPane;
    Group circles;
    Group arrows;
    private double lastX;
    private double lastY;

    public void initialize(Pane pane){
        graphicsPane = pane;
        graphicsPane.toFront();
        graphicsPane.setMouseTransparent(true);
        circles = new Group();
    }

    public void makeCircle(BoggleButton b){

        System.out.println(b.getX());
        System.out.println(b.getY());
        System.out.println(b.getLayoutX());
        System.out.println(b.getLayoutY());

        Circle circle1 = new Circle(35, Color.TRANSPARENT);
        circle1.setStrokeWidth(5);
        circle1.setStrokeType(INSIDE);
        circle1.setStroke(Color.STEELBLUE);
        circle1.setMouseTransparent(true);
        circle1.relocate(b.getLayoutX(), b.getLayoutY());
        circles.getChildren().add(circle1);
        graphicsPane.getChildren().removeAll(circles);
        graphicsPane.getChildren().addAll(circles);


        Circle circle2 = new Circle(35, Color.TRANSPARENT);
        circle2.setStrokeWidth(5);
        circle2.setStrokeType(INSIDE);
        circle2.setStroke(Color.RED);
        circle2.setMouseTransparent(true);
        circle2.relocate(b.getX(), b.getY());
        circles.getChildren().add(circle2);
        graphicsPane.getChildren().removeAll(circles);
        graphicsPane.getChildren().addAll(circles);

    }

    public void makeArrows(BoggleButton b){

    }

    public void updateLast(double x, double y){
        this.lastX = x;
        this.lastY = y;
    }


    public void clearCircles(){
        circles.getChildren().clear();
    }
}
