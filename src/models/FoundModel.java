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
        solutionSet = new TreeSet<>();

        foundWords.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                view.scrollTo(foundWords.size()-1);
            }
        });
    }

    public void setSolution(Set<String> solution){
        solutionSet = solution;
        System.out.println("Words Possible: " + solution.size());
    }

    public Set<String> getSolution(){
        return solutionSet;
    }

    public void submit(String item){

        if(solutionSet.contains(item)) {
            String x = pointValue(item) + "\t";
            foundWords.add(x + item);
            score += pointValue(item);
            solutionSet.remove(item);

            count++;
            countLabel.setText("Found: " + count +
                                "\nScore: " + score);
        }
    }

    public void clear(){
        count = 0;
        countLabel.setText("Found: 0" + "\nScore: 0");
        foundWords.clear();
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

    public void compileFinalList(ListView<String> view){
        finalCount = count;
        finalScore = score;

        int maxScore = 0;
        int maxCount = 0;

        ObservableList<String> complete = FXCollections.observableArrayList();

        for(String word : solutionSet){
            Text x = new Text(pointValue(word) + "\t" + word);
            maxScore += pointValue(word);
            maxCount++;
            if(foundWords.contains(word)){
                x.setFont(Font.font(String.valueOf(FontWeight.BOLD)));
            }

            String y = x.getText();
            complete.add(y);

        }
        view.setItems(complete);

        countLabel.setText("Found: " + count + "/" + maxCount +
                "\nScore: " + score + "/" + maxScore);

    }

}
