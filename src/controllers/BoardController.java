package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import models.BoardModel;
import models.BoggleButton;
import models.DiceModel;
import models.GridModel;

import java.io.IOException;
import java.util.List;
public class BoardController {


    private BoardModel board = BoardModel.getModel();
    private static GridModel gridModel = GridModel.getModel();
    final private static DiceModel dice = DiceModel.getModel();

    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();
    final private static MenuController MENU_CONTROLLER = new MenuController();
    final private static FoundController FOUND_CONTROLLER = new FoundController();

    private static boolean isOver = false;

    @FXML
    GridPane gameBoard;

    @FXML
    Label wipLabel;


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
            b.setButtonBounds();
            b.offsetX();
            b.offsetY();
            board.appendWip(b.getLetter());
            GRAPHICS_CONTROLLER.graphicsFactory(b);
        } else if(gridModel.first(b)){
            // trigger cancel action
            MENU_CONTROLLER.cancelButtonClicked(new ActionEvent());
        } else if(gridModel.last(b)){
            // trigger submit action
            MENU_CONTROLLER.submitButton(new ActionEvent(), this.getWIP());
        } else {
            return;
        }
    }

    public void resetButtons(){
        gridModel.resetButtons();
    }

    public void clearWIP(){
        board.clearWip();
    }

    public String getWIP(){
        return board.getWip();
    }

    public void endRound(){
        isOver = true;
    }
}
