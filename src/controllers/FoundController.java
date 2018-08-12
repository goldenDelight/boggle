package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.DicModel;
import models.FoundModel;

import javax.swing.*;
import java.io.IOException;

public class FoundController {
    private DicModel dictionary = DicModel.getModel();
    private FoundModel found = FoundModel.getModel();
    private static WipController wipController = new WipController();
    private static final TimerController TIMER_CONTROLLER = new TimerController();

    @FXML
    ListView<String> foundListView;

    @FXML
    Label foundCount = new Label();

//    Binds ListView of foundView and ObservableList of foundModel
    public void initialize(){

//        System.out.println("Found Controller Initialized");

        found.linkList(foundListView, foundCount);
        found.setCount(0);

        try {
            dictionary.DicMaker();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endRound(ActionEvent timeUp){
        found.timeUp();
    }

    public void giveUp(){
        TIMER_CONTROLLER.stopTimer(new ActionEvent());
        found.timeUp();
    }

    public void updateDic(String[][] newLetters){

//        for(int i = 0,  r = 0; r < 4; r++){
//            for(int c = 0; c < 4; c++){
//                System.out.print(newLetters[r][c]);;
//            }
//        }
        dictionary.updateBoardLetters(newLetters);
        this.setSolution();
    }

    public void setSolution() {
        found.setSolution(dictionary.getfound());
//        System.out.println(dictionary.getSolutionSet().size() + " words possible");
    }

    public void submitWIP(String wip) {
        found.submit(wip);
        foundCount.setText("Found Words: " + found.getCount());
        this.clearWIP();
    }

    public void clearWIP(){
        wipController.clearWIP();
    }

    public void newGame() {
        found.clear(foundListView);
        wipController.clearWIP();
    }

}