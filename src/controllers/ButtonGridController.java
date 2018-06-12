package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import models.BoggleButton;
import models.ButtonGridModel;
import models.DiceModel;

import java.util.List;
public class ButtonGridController {

    @FXML
    GridPane gameBoard;

    private static ButtonGridModel gridModel = ButtonGridModel.getModel();
    final private static DiceModel dice = DiceModel.getModel();

    final private static BoardController BOARD_CONTROLLER = new BoardController();
    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();
    final private static MenuController MENU_CONTROLLER = new MenuController();


    public void initialize(){
        gridModel.initialize(gameBoard);
        newRound();
    }

    public void newRound() {
        List<String> newLetters = dice.reRoll();
        gridModel.rollNewLetters(newLetters);
        MENU_CONTROLLER.updateDic(gridModel.getLetterCords());
    }

    public void letterClicked(BoggleButton b){
        if(gridModel.valid(b)){
            gridModel.updateBoard(b);
            b.setButtonBounds();
            b.offsetX();
            b.offsetY();
            BOARD_CONTROLLER.letterClicked(b);
        } else if(gridModel.first(b)){
            // trigger cancel action
            MENU_CONTROLLER.cancelButtonClicked(new ActionEvent());
        } else if(gridModel.last(b)){
            // trigger submit action
            MENU_CONTROLLER.submitButton(new ActionEvent());
        } else {
            return;
        }
    }

    public void resetButtons(){
        gridModel.resetButtons();
    }
}
