package controllers;

import javafx.event.ActionEvent;
import models.BottomModel;

public class MenuController {

    private BottomModel bottom = BottomModel.getModel();

    final private static FoundController FOUND_CONTROLLER = new FoundController();
    final private static BoardController BOARD_CONTROLLER = new BoardController();
    final private static ButtonGridController BUTTON_GRID_CONTROLLER = new ButtonGridController();
    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();

    public void addButtonClicked(ActionEvent actionEvent) {
        if (!FOUND_CONTROLLER.alreadyFound(BOARD_CONTROLLER.getWip())) {
            FOUND_CONTROLLER.addWord();
            BOARD_CONTROLLER.clearWip();
            BUTTON_GRID_CONTROLLER.resetButtons();
            GRAPHICS_CONTROLLER.clearCircles();

        }
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        BOARD_CONTROLLER.clearWip();
        BUTTON_GRID_CONTROLLER.resetButtons();
        GRAPHICS_CONTROLLER.clearCircles();
    }

    public void rollButtonClicked(ActionEvent actionEvent) {
        BOARD_CONTROLLER.clearWip();
        FOUND_CONTROLLER.clearAll();
        GRAPHICS_CONTROLLER.clearCircles();
        BUTTON_GRID_CONTROLLER.newRound();
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }
}
