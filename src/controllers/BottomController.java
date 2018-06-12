package controllers;

import javafx.event.ActionEvent;
import models.BottomModel;

public class BottomController {

    private BottomModel bottom = BottomModel.getModel();

    final private static FoundController foundController = new FoundController();
    final private static BoardController boardController = new BoardController();
    final private static ButtonGridController buttonGridController = new ButtonGridController();


    public void addButtonClicked(ActionEvent actionEvent) {
        foundController.submitWIP();
        boardController.clearWIP();
        buttonGridController.resetButtons();
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        boardController.clearWIP();
        buttonGridController.resetButtons();
    }

    public void rollButtonClicked(ActionEvent actionEvent) {
        boardController.clearWIP();
        foundController.newGame();
        buttonGridController.newRound();
    }

}
