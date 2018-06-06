package models;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;
import models.Arrowhead.*;

import static javafx.scene.shape.StrokeType.INSIDE;

public class GraphicsModel {

    private final static GraphicsModel model = new GraphicsModel();

    public static GraphicsModel getModel(){
        return model;
    }

    Pane graphicsPane;
    List<Circle> circles;
    List<Line> lines;
    private double lastX;
    private double lastY;

    public void initialize(Pane pane){
        graphicsPane = pane;

        graphicsPane.toFront();
        graphicsPane.setMouseTransparent(true);
        circles = new ArrayList();
        lines = new ArrayList<>();
    }

    public void makeGraphics(BoggleButton b){

        final double PADDING_ADJUST = 50;

        double xCord = b.getLayoutX() + PADDING_ADJUST;
        double yCord = b.getLayoutY() + PADDING_ADJUST;

        Circle circle = new Circle(xCord, yCord, 40, Color.TRANSPARENT);
        circle.setStrokeWidth(7);
        circle.setStrokeType(INSIDE);
        circle.setMouseTransparent(true);

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


        if(lastX != 0 && lastY != 0){
            graphicsPane.getChildren().removeAll(lines);
            lines.add(new Line(lastX, lastY, circle.getCenterX(), circle.getCenterY()));
            graphicsPane.getChildren().addAll(lines);
        }

        lastX = circle.getCenterX();
        lastY = circle.getCenterY();
    }


    public void makeLine(){

        double x1 = circles.get(circles.size()-2).getLayoutX();
        double y1 = circles.get(circles.size()-2).getLayoutY();

        double x2 = circles.get(circles.size()-1).getLayoutX();
        double y2 = circles.get(circles.size()-1).getLayoutY();

        lines.addAll(orientation(x1,y1,x2,y2));


        graphicsPane.getChildren().removeAll(lines);
        graphicsPane.getChildren().addAll(lines);
    }

    public void clearGraphics(){
        circles.clear();
        lines.clear();
        graphicsPane.getChildren().clear();

        lastX = 0;
        lastY = 0;
    }


    public List<Line> orientation(double x1, double y1, double x2, double y2){

        double dX = x2-x1;

        double dY = y2-y1;
        double startX = 0.25 * dX;

        double startY = 0.25 * dY;
        double endX = 0.75 * dX;

        double endY = 0.75 * dY;
        double leftX;

        double leftY;
        double rightX;

        double rightY;

        List<Line> pointLines = new ArrayList<>();

        // calc start/end cords for line

        if(dX == 0 || dY == 0){
            double topX = endX;
            double topY = endY - 10;

            double lX = endX - 10;
            double lY = endY;

            double rX = endX + 10;
            double rY = endY;

            double bottomX = endX;
            double bottomY = endY + 10;

            pointLines.add(new Line (startX,startY,endX,endY));
            pointLines.add(new Line(topX,topY,endX,endY));
            pointLines.add(new Line(lX,lY,endX,endY));
            pointLines.add(new Line(rX,rY,endX,endY));
            pointLines.add(new Line(bottomX,bottomY,endX,endY));

        } else {
            double tlX = (endX + endX - 10)/2;
            double tlY = (endY + endY - 10)/2;

            double blX = (endX + endX - 10)/2;
            double blY = (endY + endY + 10)/2;

            double trX = (endX + endX + 10)/2;
            double trY = (endY + endY - 10)/2;

            double brX = (endX + endX + 10)/2;
            double brY = (endY + endY + 10)/2;

            pointLines.add(new Line(tlX,tlY,endX,endY));
            pointLines.add(new Line(blX,blY,endX,endY));
            pointLines.add(new Line(trX,trY,endX,endY));
            pointLines.add(new Line(brX,brY,endX,endY));
        }

        return pointLines;

    }

}
