package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import models.BoggleButton;
import models.GridModel;

public class ButtonController {

    @FXML
    GridPane gameBoard;

    private static GridModel gridModel = GridModel.getModel();

    final private static BoardController BOARD_CONTROLLER = new BoardController();
    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();
    final private static MenuController MENU_CONTROLLER = new MenuController();

    private static boolean isOver = false;

    public void initialize() {
//        System.out.println("Button Controller Initialized");
    }

    public void letterClicked(BoggleButton b) {

        if(isOver){
            return;
        }
        if (gridModel.first(b)) {
            MENU_CONTROLLER.cancelButtonClicked(new ActionEvent());
        } else if (gridModel.last(b)) {
                MENU_CONTROLLER.submitButton(new ActionEvent(), BOARD_CONTROLLER.getWIP());
        }
    }
}
