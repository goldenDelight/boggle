package models;

import controllers.ButtonGridController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.shape.StrokeType.INSIDE;

public class ButtonGridModel {

    private final static ButtonGridModel model = new ButtonGridModel();
    public static ButtonGridModel getModel(){
        return model;
    }

    GridPane board;
    List<BoggleButton> listo = new ArrayList<>();
    private List<BoggleButton> used = new ArrayList<>();
    private static BoggleButton[][] buttonCoords = new BoggleButton[4][4];

    private static ButtonGridController buttonGridController = new ButtonGridController();


    public void initialize(GridPane gameBoard){

        board = gameBoard;

        double x1, x2, x3, x4, y1, y2, y3, y4;


        for(int i=0, c=0; c<4; c++){
            for(int r=0; r<4; r++){
                BoggleButton bb = makeButton(i++, r, c);
                buttonCoords[r][c] = bb;
                listo.add(bb);
                board.add(bb,r,c);
//                bb.setAlignment(Pos.CENTER);
                bb.setButtonBounds();
            }
        }
    }
    public void rollNewLetters(List<String> newLetters){
        this.resetButtons();
        int i=0;
        for(BoggleButton b : listo){
            b.setLetter(newLetters.get(i++));
        }
    }
    public void resetButtons(){
        used.clear();
        for(BoggleButton b : listo){
            b.reset();
        }
    }
    public void updateBoard(BoggleButton b) {
        int x = -1, y = -1, r, c;

        for (r = 0; r < 4; r++) {
            for (c = 0; c < 4; c++) {
                buttonCoords[r][c].setValid(false);
                if (b.equals(buttonCoords[r][c])) {
                    x = r;
                    y = c;
                }
            }
        }
//        ensures valid bounds for iteration through the 2-D boggle button array
//        sets (unused) boggle buttons adjacent to the last letter clicked as valid
        for (r = (x == 0 ? 0 : x - 1); r <= x + 1 && r < 4; r++) {
            for (c = (y == 0 ? 0 : y - 1); c <= y + 1 && c < 4; c++) {
                if (!used.contains(buttonCoords[r][c])) {
                    buttonCoords[r][c].reset();
                }
            }
        }

        used.add(b);
    }
    private BoggleButton makeButton(int i, int r, int c){

        BoggleButton bb = new BoggleButton(i, r, c);
        bb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BoggleButton buttonClicked = (BoggleButton) event.getSource();
                buttonGridController.letterClicked(buttonClicked);
            }
        });

        return bb;
    }
    public boolean valid(BoggleButton b) {
        if (b.isValid() && !used.contains(b)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean first(BoggleButton b){
        if (b.equals(used.get(0))){
            return true;
        }
        return false;
    }
    public boolean last(BoggleButton b){
        if(used.size() > 2 && b == used.get((used.size())-1)){
            return true;
        }
        return false;
    }

    public void addCircle(Circle circle, int c, int r){
        circle.setStrokeWidth(5);
        circle.setStrokeType(INSIDE);
        circle.setStroke(Color.STEELBLUE);
        circle.setMouseTransparent(true);
        board.add(circle, c, r);
    }
}
