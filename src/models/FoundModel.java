package models;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Set;
import java.util.TreeMap;
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
    private TreeMap<String, Boolean> solutionSet;
    private int score;
    private int finalScore;
    private int finalCount;


//    Bind alreadyFound panel's listview to the alreadyFound model's observable list
    public void linkList(ListView<String> view, Label count){
        this.count = 0;
        score = 0;
        view.setItems(foundWords);
        countLabel = count;
        countLabel.setText("Found: 0" + "\nScore: 0");
        solutionSet = new TreeMap<>();

        foundWords.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                view.scrollTo(foundWords.size()-1);
            }
        });
    }

    public void setSolution(TreeMap<String, Boolean> solution){
        solutionSet = solution;
        System.out.println("Words Possible: " + solution.size());
    }

    public TreeMap<String, Boolean> getSolution(){
        return solutionSet;
    }

    public void submit(String item){

        if(solutionSet.get(item) == false) {
            String x = pointValue(item) + "\t";
            foundWords.add(x + item);
            score += pointValue(item);
            solutionSet.replace(item, true);

            count++;
            countLabel.setText("Found: " + count +
                                "\nScore: " + score);
        }
    }

    public void clear(ListView<String> view){
        count = 0;
        countLabel.setText("Found: 0" + "\nScore: 0");
        foundWords.clear();
        solutionSet.clear();
//        view.setItems(foundWords);
        score = 0;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {

        return count;
    }

    public int pointValue(String word){
        if(word.length() >= 8){
            return 11;
        } else if(word.length() == 7){
            return 5;
        } else if(word.length() == 6){
            return 3;
        } else if(word.length() == 5){
            return 2;
        } else {
            return 1;
        }
    }

    public ObservableList<String> compileFinalList(){
        finalCount = count;
        finalScore = score;

        int maxScore = 0;
        int maxCount = 0;

        ObservableList<String> complete = FXCollections.observableArrayList();

        for(String word : solutionSet.keySet()){
            Text x = new Text(pointValue(word) + "\t" + word);
            maxScore += pointValue(word);
            maxCount++;

//            complete.add(pointValue(word) + "\t" + word);

//            if(solutionSet.get(word)){
//                x.setFont(Font.font(String.valueOf(FontWeight.BOLD)));
//            }
//
//            String y = x.getText();
//            complete.add(y);
        }

        countLabel.setText("Found: " + count + "/" + maxCount +
                "\nScore: " + score + "/" + maxScore);

        return complete;

    }

    public void giveUp(){

        int maxScore = 0;
        int maxCount = 0;

        for(String word : solutionSet.keySet()) {
            maxScore += pointValue(word);
            maxCount++;

            if(solutionSet.get(word) == false) {
                String x = pointValue(word) + "\t";
                foundWords.add(x + word);
            } else if(solutionSet.get(word) == true) {
                String x = pointValue(word) + "\t";
                foundWords.add(x + word);
            }
        }

        countLabel.setText("Found: " + count + "/" + maxCount +
                "\nScore: " + score + "/" + maxScore);


    }

}
