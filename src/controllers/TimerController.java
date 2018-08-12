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

    final private static FoundController FOUND_CONTROLLER = new FoundController();

    //    Global instance of my timer

    //    Declaring the fxml label as global
    @FXML
    Label timeLabel = new Label();


    private TimerModel timer = TimerModel.getModel();

    //    sets timer at time of initialization
    public void initialize(){
        addListeners();
        timer.refreshTimer();
    }

    public void refreshTimer() {
        timer.interruptThread();
        timer.refreshTimer();
    }

    public void stopTimer(ActionEvent iGiveUp){
        timer.interruptThread();
    }

    private void addListeners(){
        timer.timeProperty().addListener( (v, oldValue, newValue) -> {
            Platform.runLater(() -> timeLabel.setText(timer.getTime()));
            System.out.println(timer.getTime());
        });

        timer.completedProperty().addListener((observable, oldValue, newValue) ->  {
//            if (newValue) {
                FOUND_CONTROLLER.endRound(new ActionEvent());
//            }
        });
    }
}


