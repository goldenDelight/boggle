package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    final private static FoundController FOUND_CONTROLLER = new FoundController();
    final private static BoardController BOARD_CONTROLLER = new BoardController();
    final private static GridController GRID_CONTROLLER = new GridController();
    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();
    final private static TimerController TIMER_CONTROLLER = new TimerController();
    private static boolean isOver = false;

    public void submitButton(ActionEvent actionEvent, String wip) {

        if(isOver){
            return;
        }
        FOUND_CONTROLLER.submitWIP(wip);
        BOARD_CONTROLLER.clearWIP();
        GRID_CONTROLLER.resetButtons();
        GRAPHICS_CONTROLLER.clearCircles();
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {

        if(isOver){
            return;
        }
        BOARD_CONTROLLER.clearWIP();
        FOUND_CONTROLLER.clearWIP();
        GRID_CONTROLLER.resetButtons();
        GRAPHICS_CONTROLLER.clearCircles();
    }

    public void rollButtonClicked(ActionEvent actionEvent) {
        isOver = false;
        TIMER_CONTROLLER.refreshTimer();
        BOARD_CONTROLLER.clearWIP();
        FOUND_CONTROLLER.newGame();
        GRAPHICS_CONTROLLER.clearCircles();
        GRID_CONTROLLER.newRound();
        FOUND_CONTROLLER.clearWIP();

    }

    public void endRound(ActionEvent roundOver){
        isOver = true;
        GRID_CONTROLLER.endRound();
        BOARD_CONTROLLER.endRound();
        FOUND_CONTROLLER.endRound();
        TIMER_CONTROLLER.stopTimer();
    }

    public void updateDic(String[][] newLetters){
        FOUND_CONTROLLER.updateDic(newLetters);
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }
}
