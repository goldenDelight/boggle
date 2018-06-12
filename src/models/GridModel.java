package models;

import controllers.GridController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class GridModel {

    private final static GridModel model = new GridModel();
    public static GridModel getModel(){
        return model;
    }

    GridPane board;
    List<BoggleButton> listo = new ArrayList<>();
    private List<BoggleButton> used = new ArrayList<>();
    private static BoggleButton[][] buttonCoords = new BoggleButton[4][4];
    private static String[][] letterCords = new String[4][4];
    private static GridController gridController = new GridController();


    public void initialize(GridPane gameBoard){

        board = gameBoard;

//        double x1, x2, x3, x4, y1, y2, y3, y4;

        for(int i=0, c=0; c<4; c++){
            for(int r=0; r<4; r++){
                BoggleButton bb = makeButton(i++, r, c);
                buttonCoords[r][c] = bb;
                letterCords[r][c] = bb.getLetter();
                listo.add(bb);
                board.add(bb,r,c);
                bb.setButtonBounds();
            }
        }
    }

    public void rollNewLetters(List<String> newLetters){
        this.resetButtons();

        for(int i = 0, r = 0; r < 4; r++){
            for(int c = 0; c< 4; c++){
                buttonCoords[r][c].setLetter(newLetters.get(i));
                letterCords[r][c] = newLetters.get(i++).toLowerCase();
            }
        }
    }

    public String[][] getLetterCords(){
        return letterCords;
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
                gridController.letterClicked(buttonClicked);
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

//    public void addCircle(Circle circle, int c, int r){
//        circle.setStrokeWidth(5);
//        circle.setStrokeType(INSIDE);
//        circle.setStroke(Color.STEELBLUE);
//        circle.setMouseTransparent(true);
//        board.add(circle, c, r);
//    }
}
