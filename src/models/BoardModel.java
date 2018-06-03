package models;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class BoardModel {

//    Singleton Initialization
    private final static BoardModel model = new BoardModel();
    public static BoardModel getModel(){
        return model;
    }


    private String wip = "";
    private StringProperty wipProperty = new SimpleStringProperty("");
    private BoggleButton[][] boardLayout = new BoggleButton[4][4];

//    Bind text property of wipLabel to wipProperty
    public void linkWordProgress(Label wipLabel){
        wipLabel.textProperty().bind(wipProperty);
        wipLabel.setFont(Font.font(24));
    }


    public void appendWip(String letter){
        setWip(wip + letter);
        wipProperty.setValue(wip);
    }

    public String getWip(){
        return wip;
    }

    public void setWip(String newWip){
        this.wip = newWip;
    }

    public void clearWip(){
        wip = "";
        wipProperty.setValue(wip);
    }
}
