package models;

import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class BoggleButton extends Button {

    private boolean valid = true;
    private int r,c;
    private String letter = "";
    private Bounds screenBounds = this.localToScene(this.getBoundsInLocal());

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

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
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


    public Double getX(){
        return (screenBounds.getMinX() + screenBounds.getMaxX())/2;
    }
    public Double getY(){
        return (screenBounds.getMinY() + screenBounds.getMaxY())/2;
    }

}


