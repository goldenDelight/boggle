package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import models.BoardModel;
import models.BoggleButton;

import java.io.IOException;
import java.util.Set;

public class BoardController {

    private BoardModel board = BoardModel.getModel();

    final private static ButtonGridController BUTTON_GRID_CONTROLLER = new ButtonGridController();
    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();
    final private static FoundController FOUND_CONTROLLER = new FoundController();

    @FXML
    Label wipLabel;

    @FXML
    StackPane stack;

    public void initialize() throws IOException {
        board.linkWordProgress(wipLabel);
    }

    public void letterClicked(BoggleButton b){
        board.appendWip(b.getLetter());
        GRAPHICS_CONTROLLER.graphicsFactory(b);
    }
    public void clearWIP(){
        board.clearWip();
    }

    public String getWIP(){
        return board.getWip();
    }

}
