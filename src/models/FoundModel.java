package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class FoundModel {

//    Singleton Initialization
    private final static FoundModel model = new FoundModel();
    public static FoundModel getModel(){
        return model;
    }

    private ObservableList<String> listItems = FXCollections.observableArrayList();

//    Bind alreadyFound panel's listview to the alreadyFound model's observable list
    public void linkList(ListView<String> view){
        view.setItems(listItems);
    }

    public void addWordFound(String item){
        listItems.add(item);
    }

    public void clear(){
        listItems.clear();
    }

    public boolean alreadyFound(String word){
        if(listItems.contains(word)){
            return true;
        } else {
            return false;
        }
    }
}
