package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FoundModel {

//    Singleton Initialization
    private final static FoundModel model = new FoundModel();
    public static FoundModel getModel(){
        return model;
    }

    private ObservableList<String> listItems = FXCollections.observableArrayList();
    private int count;
    private Label countLabel;
    private List<String> foundWords;
    private Set<String> solutionSet;

//    Bind alreadyFound panel's listview to the alreadyFound model's observable list
    public void linkList(ListView<String> view, Label count){
        this.count = 0;
        view.setItems(listItems);
        countLabel = count;
        countLabel.setText("Words Found: 0");
        foundWords = new ArrayList<>();
        solutionSet = new TreeSet<>();
    }

    public void setSolution(Set<String> solution){
        solutionSet = solution;
        System.out.println("Words Possible: " + solution.size());
    }

    public Set<String> getSolution(){
        return solutionSet;
    }

    public void addWordFound(String item){
        listItems.add(item);
        foundWords.add(item);
        count++;
        countLabel.setText("Words Found: " + count);
    }

    public void clear(){
        listItems.clear();
        count = 0;
        countLabel.setText("Words Found: 0");
    }

    public boolean alreadyFound(String word){
        if(listItems.contains(word)){
            return true;
        } else {
            return false;
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {

        return count;
    }
}
