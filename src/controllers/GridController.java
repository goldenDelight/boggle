package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import models.BoggleButton;
import models.DiceModel;
import models.GridModel;

import java.util.List;

public class GridController {

    @FXML
    GridPane gameBoard;

    private static GridModel gridModel = GridModel.getModel();
    final private static DiceModel dice = DiceModel.getModel();
    final private static WipController BOARD_CONTROLLER = new WipController();
    final private static MenuController MENU_CONTROLLER = new MenuController();

    private static boolean isOver = false;

    public void initialize(){
        gridModel.initialize(gameBoard);
        newRound();
    }

    public void newRound() {
        isOver = false;
        List<String> newLetters = dice.reRoll();
        gridModel.rollNewLetters(newLetters);
        MENU_CONTROLLER.updateDic(gridModel.getLetterCords());
    }

    public void letterClicked(BoggleButton b){
        if(isOver){
            return;
        }
        if(gridModel.valid(b)){
            gridModel.updateBoard(b);
            BOARD_CONTROLLER.letterClicked(b);
        } else if(gridModel.first(b)){
            // trigger cancel action
            MENU_CONTROLLER.cancelButtonClicked(new ActionEvent());
        } else if(gridModel.last(b)){
            // trigger submit action
            MENU_CONTROLLER.submitButton(new ActionEvent(), BOARD_CONTROLLER.getWIP());
        } else {
            return;
        }
    }

    public void resetButtons(){
        gridModel.resetButtons();
    }

    public void endRound(){
        isOver = true;
    }

}
