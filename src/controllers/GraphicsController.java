package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import models.BoggleButton;
import models.GraphicsModel;

public class GraphicsController {

    private GraphicsModel graphics = GraphicsModel.getModel();

    final private static WipController BOARD_CONTROLLER = new WipController();
    final private static GridController BUTTON_GRID_CONTROLLER = new GridController();
    final private static MenuController MENU_CONTROLLER = new MenuController();

    @FXML
    Pane graphicsPanel;

    public void initialize(){
        graphics.initialize(graphicsPanel);
    }

    public void graphicsFactory(BoggleButton b){
        graphics.makeGraphics(b);
    }

    public void clearCircles(){
        graphics.clearGraphics();
    }
}
