package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.DicModel;
import models.FoundModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FoundController {
    private DicModel dictionary = DicModel.getModel();
    private FoundModel found = FoundModel.getModel();
    private static BoardController boardController = new BoardController();

    @FXML
    ListView<String> foundListView;

    @FXML
    Label foundCount = new Label();

//    Binds ListView of foundView and ObservableList of foundModel
    public void initialize(){

        System.out.println("Found Controller Initialized");


        found.linkList(foundListView, foundCount);
        found.setCount(0);

        try {
            dictionary.DicMaker();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void giveUp(){
//        List<String> foundList = new ArrayList<String>(dictionary.getSolutionSet());

        found.compileFinalList(foundListView);

//        System.out.println(foundList);
    }

    public void updateDic(String[][] newLetters){

        for(int i = 0,  r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                System.out.print(newLetters[r][c]);;
            }
        }
        dictionary.updateBoardLetters(newLetters);
        this.setSolution();
    }

    public void setSolution() {
        found.setSolution(dictionary.getfound());
        System.out.println(dictionary.getSolutionSet().size() + " words possible");
    }

    public void submitWIP(String wip) {
        found.submit(wip);
        foundCount.setText("Found Words: " + found.getCount());
    }

    public void newGame() {
        found.clear();
    }
}