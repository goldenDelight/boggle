package models;

import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class BoggleButton extends Button {

    private boolean valid = true;
    private int r,c;
    private String letter = "";
    private Bounds buttonBounds;
    private double X;
    private double Y;

    public void setLetter(String letter) {
        this.letter = letter;
        this.setText(getLetter());
    }

    public BoggleButton(int i, int r, int c){
        this.setId("b" + i);
        this.r = r;
        this.c = c;
        this.letter = "X";
        this.setPrefWidth(150);
        this.setPrefHeight(150);
        this.setFont(Font.font(24));
    }

    public String getLetter() {
            return letter;
        }

    public void reset() {
        this.valid = true;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setButtonBounds(){
        buttonBounds = this.localToScene(this.getBoundsInLocal());
    }

    public void offsetX(){
        this.X = (this.buttonBounds.getMaxX() - (this.buttonBounds.getMinX()))/2.0;
    }
    public void offsetY(){
        this.Y = (this.buttonBounds.getMaxY() - (this.buttonBounds.getMinY()))/2.0;
    }
}


