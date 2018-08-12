package controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import models.TimerModel;

public class TimerController {
    final private static MenuController MENU_CONTROLLER = new MenuController();
//    final private static FoundController FOUND_CONTROLLER = new FoundController();

//  create reference to visible timer in game window
    @FXML
    Label timeLabel = new Label();

//  create reference to the singleton timer
    private TimerModel timer = TimerModel.getModel();

//    adds listeners to timer's properties, and starts countdown
    public void initialize(){
        addListeners();
        timer.refreshTimer();
    }

    public void refreshTimer() {
        timer.interruptThread();
        timer.refreshTimer();
    }

    public void endRound(){

    }

    public void stopTimer(){
        timer.interruptThread();
    }

    private void addListeners(){
//        changeListener updates timeLabel when timeProperty changes
        timer.timeProperty().addListener( (v, oldValue, newValue) -> {
            Platform.runLater(() -> timeLabel.setText(timer.getTime()));
//            System.out.println(timer.getTime());
        });

//        changeListener triggers end of round when completedProperty becomes true
        timer.completedProperty().addListener((observable, oldValue, newValue) ->  {
            MENU_CONTROLLER.endRound(new ActionEvent());
        });
    }
}


