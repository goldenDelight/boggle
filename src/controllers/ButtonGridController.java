package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import models.BoggleButton;
import models.ButtonGridModel;

import java.util.List;
public class ButtonGridController {

    @FXML
    GridPane gameBoard;

    private static ButtonGridModel gridModel = ButtonGridModel.getModel();

    final private static BoardController BOARD_CONTROLLER = new BoardController();
    final private static DiceController DICE_CONTROLLER = new DiceController();
    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();
    final private static MenuController MENU_CONTROLLER = new MenuController();

    public void initialize(){
        gridModel.initialize(gameBoard);
        gridModel.rollNewLetters(DICE_CONTROLLER.rollDice());
    }

    public void newRound() {
        List<String> newLetters = DICE_CONTROLLER.rollDice();
        gridModel.rollNewLetters(newLetters);
    }

    public void letterClicked(BoggleButton b){
        if(gridModel.valid(b)){
            gridModel.updateBoard(b);
            b.setButtonBounds();
            b.offsetX();
            b.offsetY();
            BOARD_CONTROLLER.letterClicked(b);
//            Circle circle = GRAPHICS_CONTROLLER.makeGraphics(b);
//            gridModel.addCircle(circle, b.getR(), b.getC());
        } else if(gridModel.first(b)){
            // trigger cancel action
            MENU_CONTROLLER.cancelButtonClicked(new ActionEvent());
        } else if(gridModel.last(b)){
            // trigger submit action
            MENU_CONTROLLER.addButtonClicked(new ActionEvent());
        } else {
            return;
        }
    }

    public void resetButtons(){
        gridModel.resetButtons();
    }
}
