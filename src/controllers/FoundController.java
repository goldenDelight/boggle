package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import models.FoundModel;

public class FoundController {

    private FoundModel found = FoundModel.getModel();
    private static BoardController boardController = new BoardController();

    @FXML
    ListView<String> foundListView;

//    Binds ListView of foundView and ObservableList of foundModel
    public void initialize(){
        found.linkList(foundListView);

    }

    public void addWord() {
        found.addWordFound(boardController.getWip());
    }

    public void clearAll() {
        found.clear();
        boardController.clearWip();
    }

    public boolean alreadyFound(String word){
        if(found.alreadyFound(word)){
            return true;
        } else {
            return false;
        }
    }
}