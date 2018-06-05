package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import models.BoardModel;
import models.BoggleButton;

import java.util.EventListener;

public class BoardController {

    private BoardModel board = BoardModel.getModel();

    final private static ButtonGridController BUTTON_GRID_CONTROLLER = new ButtonGridController();
    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();
    final private static FoundController FOUND_CONTROLLER = new FoundController();

    @FXML
    Label wipLabel;

    @FXML
    StackPane stack;

    public void initialize(){
        board.linkWordProgress(wipLabel);
    }

    public void letterClicked(BoggleButton b){
        board.appendWip(b.getLetter());
        GRAPHICS_CONTROLLER.circleFactory(b);
    }
    public void clearWip(){
        board.clearWip();
    }

    public String getWip(){
        return board.getWip();
    }

    private boolean validClick(){
        return false;
    }
}
