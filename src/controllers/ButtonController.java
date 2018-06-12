package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import models.BoggleButton;
import models.ButtonGridModel;

public class ButtonController {

    @FXML
    GridPane gameBoard;

    private static ButtonGridModel gridModel = ButtonGridModel.getModel();

    final private static BoardController BOARD_CONTROLLER = new BoardController();
    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();
    final private static MenuController MENU_CONTROLLER = new MenuController();

    public void initialize() {
        gridModel.initialize(gameBoard);
    }

    public void letterClicked(BoggleButton b) {
        if (gridModel.first(b)) {
            MENU_CONTROLLER.cancelButtonClicked(new ActionEvent());
        } else if (gridModel.last(b)) {
                MENU_CONTROLLER.submitButton(new ActionEvent());
        }
    }
}
