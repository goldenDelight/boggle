package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class FoundModel {

//    Singleton Initialization
    private final static FoundModel model = new FoundModel();
    public static FoundModel getModel(){
        return model;
    }

    private ObservableList<String> foundWords = FXCollections.observableArrayList();
    private int count;
    private Label countLabel;
    private Set<String> solutionSet;

//    Bind alreadyFound panel's listview to the alreadyFound model's observable list
    public void linkList(ListView<String> view, Label count){
        this.count = 0;
        view.setItems(foundWords);
        countLabel = count;
        countLabel.setText("Words Found: 0");
        solutionSet = new TreeSet<>();
    }

    public void setSolution(Set<String> solution){
        solutionSet = solution;
        System.out.println("Words Possible: " + solution.size());
    }

    public Set<String> getSolution(){
        return solutionSet;
    }

    public void submitWIP(String item){

        if(solutionSet.contains(item)) {
            foundWords.add(item);
            solutionSet.remove(item);

            count++;
            countLabel.setText("Words Found: " + count);
        }
    }

    public void clear(){
        count = 0;
        countLabel.setText("Words Found: 0");
        foundWords.clear();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {

        return count;
    }
}
