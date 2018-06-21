package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import models.BoardModel;
import models.BoggleButton;

import java.io.IOException;

public class WipController {

    private BoardModel board = BoardModel.getModel();

    final private static GridController BUTTON_GRID_CONTROLLER = new GridController();
    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();
    final private static FoundController FOUND_CONTROLLER = new FoundController();

    @FXML
    Label wipLabel;

    public void initialize() throws IOException {

        System.out.println("Board Controller Initialized");

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
