package controllers;

import javafx.event.ActionEvent;
import models.BottomModel;

import java.util.List;

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

    public void updateDic(String[][] newLetters){

//        System.out.println("FOUND_CONTROLLER.updateDic(String[][] newLetters) hit");
//
//        for(int r = 0; r < 4; r++){
//            for(int c = 0; c < 4; c++){
//                System.out.println(newLetters[r][c]);
//            }
//        }
        FOUND_CONTROLLER.updateDic(newLetters);
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }
}
