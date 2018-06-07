package models;

import javafx.scene.layout.Pane;

public class GraphicsModel {

    private final static GraphicsModel model = new GraphicsModel();
    public static GraphicsModel getModel(){
        return model;
    }

    private CircleModel circleModel = CircleModel.getModel();
    private LineModel lineModel = LineModel.getModel();



    Pane graphicsPane;
    private double lastX, lastY;
    final double PADDING_ADJUST = 50;

    public void initialize(Pane pane){
        graphicsPane = pane;
        lineModel = new LineModel();
        circleModel = new CircleModel();
        graphicsPane.toFront();
        graphicsPane.setMouseTransparent(true);
        lastX = 0;
        lastY = 0;
    }

    public void makeGraphics(BoggleButton b){

        double xCord = b.getLayoutX() + PADDING_ADJUST;
        double yCord = b.getLayoutY() + PADDING_ADJUST;

        graphicsPane.getChildren().clear();

        makeCircle(xCord, yCord);

        if(lastX != 0 && lastY != 0){
            makeLine(lastX, lastY, xCord, yCord);
        }

        lastX = xCord;
        lastY = yCord;
    }

    public void makeCircle(double xCord, double yCord){
        circleModel.makeCircle(xCord, yCord);
        graphicsPane.getChildren().addAll(circleModel.getCircles());
    }

    public void makeLine(double x1, double y1, double x2, double y2){
        lineModel.makeLine(x1, y1, x2, y2);
        graphicsPane.getChildren().addAll(lineModel.getLines());
    }

    public void clearGraphics(){
        circleModel.clearCircles();
        lineModel.clearLines();
        graphicsPane.getChildren().clear();

        lastX = 0;
        lastY = 0;
    }
}
