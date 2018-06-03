package controllers;

import javafx.event.ActionEvent;
import models.BottomModel;

public class BottomController {

    private BottomModel bottom = BottomModel.getModel();

    final private static FoundController foundController = new FoundController();
    final private static BoardController boardController = new BoardController();
    final private static ButtonGridController buttonGridController = new ButtonGridController();


    public void addButtonClicked(ActionEvent actionEvent) {
        foundController.addWord();
        boardController.clearWip();
        buttonGridController.resetButtons();
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        boardController.clearWip();
        buttonGridController.resetButtons();
    }

    public void rollButtonClicked(ActionEvent actionEvent) {
        boardController.clearWip();
        foundController.clearAll();
        buttonGridController.newRound();
    }

}
