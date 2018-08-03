package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.TimerModel;

public class TimerController {

    @FXML
    Label timerLabel = new Label();

    private static final TimerModel timer = new TimerModel();


}
