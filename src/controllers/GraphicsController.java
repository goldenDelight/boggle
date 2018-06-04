package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.BoggleButton;
import models.GraphicsModel;

import static javafx.scene.shape.StrokeType.INSIDE;

public class GraphicsController {

    private GraphicsModel graphics = GraphicsModel.getModel();

    final private static BoardController BOARD_CONTROLLER = new BoardController();
    final private static ButtonGridController BUTTON_GRID_CONTROLLER = new ButtonGridController();
    final private static MenuController MENU_CONTROLLER = new MenuController();

    @FXML
    Pane graphicsPanel;

    public void initialize(){
        graphics.initialize(graphicsPanel);
    }

    public void circleFactory(BoggleButton b){
        graphics.makeCircle(b);
    }

    public void arrowFactory(BoggleButton b){
        graphics.makeArrows(b);
    }

    public void clearCircles(){
        graphics.clearCircles();
    }
}
