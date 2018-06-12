package controllers;

import javafx.event.ActionEvent;

public class MenuController {

    final private static FoundController FOUND_CONTROLLER = new FoundController();
    final private static BoardController BOARD_CONTROLLER = new BoardController();
    final private static GridController GRID_CONTROLLER = new GridController();
    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();

    public void submitButton(ActionEvent actionEvent) {
        FOUND_CONTROLLER.submitWIP();
        BOARD_CONTROLLER.clearWIP();
        GRID_CONTROLLER.resetButtons();
        GRAPHICS_CONTROLLER.clearCircles();
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        BOARD_CONTROLLER.clearWIP();
        GRID_CONTROLLER.resetButtons();
        GRAPHICS_CONTROLLER.clearCircles();
    }

    public void rollButtonClicked(ActionEvent actionEvent) {
        BOARD_CONTROLLER.clearWIP();
        FOUND_CONTROLLER.newGame();
        GRAPHICS_CONTROLLER.clearCircles();
        GRID_CONTROLLER.newRound();
    }

    public void updateDic(String[][] newLetters){

        FOUND_CONTROLLER.updateDic(newLetters);
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }
}
