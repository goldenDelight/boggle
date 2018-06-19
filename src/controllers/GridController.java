package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import models.BoggleButton;
import models.GridModel;
import models.DiceModel;

import java.util.List;
public class GridController {

    @FXML
    GridPane gameBoard;

    private static GridModel gridModel = GridModel.getModel();
    final private static DiceModel dice = DiceModel.getModel();

    final private static BoardController BOARD_CONTROLLER = new BoardController();
    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();
    final private static MenuController MENU_CONTROLLER = new MenuController();


    public void initialize(){

        System.out.println("Grid Controller Initialized");


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
            MENU_CONTROLLER.submitButton(new ActionEvent(), BOARD_CONTROLLER.getWIP());
        } else {
            return;
        }
    }

    public void resetButtons(){
        gridModel.resetButtons();
    }
}
