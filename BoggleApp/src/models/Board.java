package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.shape.StrokeType.INSIDE;

public class Board {

    private Dice dice = new Dice();

/////////////////////////////////GAME MECHANICS////////////////////////////////////////////
    public void submit(String item){

        if(solutionSet.get(item) == null){
            System.out.println("\"" + item + "\"" + "...seriously?");
        } else if(solutionSet.get(item) == true) {
            System.out.println("maybe try new words? ");
        } else {
            String x = pointValue(item) + "\t";
            foundWords.add(x + item);
            score += pointValue(item);
            solutionSet.replace(item, true);
            count++;
//            countLabel.setText("Found: " + count +
//                                "\nScore: " + score);
        }
    }
    public void clear(ListView<String> view){
        count = 0;
        countLabel.setText("Found: 0" + "\nScore: 0");
        foundWords.clear();
        solutionSet.clear();
        score = 0;
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
//            String y = x.getText();
//            complete.add(y);
        }
        countLabel.setText("Found: " + count + "/" + maxCount +
                "\nScore: " + score + "/" + maxScore);
        return complete;
    }
    public void timeUp(){
        int maxScore = 0;
        int maxCount = 0;
        foundWords.clear();
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

///////////////////////////////LETTER GRID MODEL///////////////////////////////////////
//    List<LetterTile> listo = new ArrayList<>();
    private List<LetterTile> lettersUsedList = new ArrayList<>();
    private static LetterTile[][] tileGridArray = new LetterTile[4][4];
    private static String[][] letterGridArray = new String[4][4];

    public void createLetterTileGrid(GridPane boardGridPane){
//        System.out.println("Grid Model Initialized");

        for(int i=0, c=0; c<4; c++){
            for(int r=0; r<4; r++){
                LetterTile bb = makeLetterTile(i++, r, c);
                tileGridArray[r][c] = bb;
                letterGridArray[r][c] = bb.getLetter();
//                listo.add(bb);
                boardGridPane.add(bb,r,c);
                bb.setButtonGraphicsPosition();
            }
        }
    }
    public void rollNewLetters(List<String> newLetters){
        this.refreshTiles();

        for(int i = 0, r = 0; r < 4; r++){
            for(int c = 0; c< 4; c++){
                tileGridArray[r][c].setLetter(newLetters.get(i));
                letterGridArray[r][c] = newLetters.get(i++).toLowerCase();
            }
        }
    }
    public String[][] getLetterGridArray(){
        return letterGridArray;
    }

    public void refreshTiles(){
        lettersUsedList.clear();
        for(LetterTile b : listo){
            b.refreshTile();
        }
    }
    public void updateBoard(LetterTile b) {
        int x = -1, y = -1, r, c;

        for (r = 0; r < 4; r++) {
            for (c = 0; c < 4; c++) {
                tileGridArray[r][c].setValid(false);
                if (b.equals(tileGridArray[r][c])) {
                    x = r;
                    y = c;
                }
            }
        }
//        ensures checkForValid bounds for iteration through the 2-D boggle button array
//        sets (unused) boggle buttons adjacent to the checkForSubmit letter clicked as checkForValid
        for (r = (x == 0 ? 0 : x - 1); r <= x + 1 && r < 4; r++) {
            for (c = (y == 0 ? 0 : y - 1); c <= y + 1 && c < 4; c++) {
                if (!lettersUsedList.contains(tileGridArray[r][c])) {
                    tileGridArray[r][c].refreshTile();
                }
            }
        }

        lettersUsedList.add(b);
    }
    private LetterTile makeLetterTile(int i, int r, int c){
        LetterTile bb = new LetterTile(i, r, c);
        bb.setOnAction(event -> gridController.letterClicked(bb));


        return bb;

    }
    public boolean checkForValid(LetterTile buttonClicked) {
        if (buttonClicked.isValid() && !lettersUsedList.contains(buttonClicked)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkForCancel(LetterTile buttonClicked){
        if (buttonClicked.equals(lettersUsedList.get(0))){
            return true;
        }
        return false;
    }
    public boolean checkForSubmit(LetterTile buttonClicked){
        if(lettersUsedList.size() > 2 && buttonClicked == lettersUsedList.get((lettersUsedList.size())-1)){
            return true;
        }
        return false;
    }

/////////////////////////////////GRAPHICS MODEL////////////////////////////////////////
    private double lastX, lastY;
    final double PADDING_ADJUST = 50;

    public void initialize(Pane pane){
        graphicsPane = pane;
        lineModel = new LineModel();
        circleModel = new CircleModel();
        graphicsPane.toFront();
        graphicsPane.setMouseTransparent(true);
        lastX = 0;
        lastY = 0;
    }
    public void makeGraphics(LetterTile b){

        double xCord = b.getLayoutX() + PADDING_ADJUST;
        double yCord = b.getLayoutY() + PADDING_ADJUST;

        graphicsPane.getChildren().clear();

        makeCircle(xCord, yCord);

        if(lastX != 0 && lastY != 0){
            makeLine(lastX, lastY, xCord, yCord);
        }

        lastX = xCord;
        lastY = yCord;
    }
    public void makeCircle(double xCord, double yCord){
        circleModel.makeCircle(xCord, yCord);
        graphicsPane.getChildren().addAll(circleModel.getCircles());
    }
    public void makeLine(double x1, double y1, double x2, double y2){
        lineModel.makeLine(x1, y1, x2, y2);
        graphicsPane.getChildren().addAll(lineModel.getLines());
    }
    public void clearGraphics(){
        circleModel.clearCircles();
        lineModel.clearLines();
        graphicsPane.getChildren().clear();

        lastX = 0;
        lastY = 0;
    }

    //////////////CIRCLES/////////////////////////////////
    private List<Circle> circles = new ArrayList<>();
    public void makeCircle(double x, double y){

        Circle circle = new Circle(x, y, 40, Color.TRANSPARENT);
        circle.setStrokeWidth(7);
        circle.setStrokeType(INSIDE);
        circle.setMouseTransparent(true);

        circles.add(circle);

        this.colorCircles();
    }
    public void colorCircles(){

        for(Circle c : circles) {

            c.setStroke(Color.STEELBLUE);

            if (c.equals(circles.get(0))) {
                c.setStroke(Color.TOMATO);
            } else if (c.equals(circles.get(circles.size() - 1))) {
                c.setStroke(Color.SPRINGGREEN);
            } else {
                c.setStroke(Color.STEELBLUE);
            }
        }
    }
    public List<Circle> getCircles(){
        return circles;
    }
    public void clearCircles(){
        circles.clear();
    }

    //////////////LINES/////////////////////////////////
    public void makeLine(double x1, double y1, double x2, double y2) {

        if (x2 == x1 || y2 == y1) {
            if (x2 > x1) {
                x1 += 40;
                x2 -= 40;
            } else if (x2 < x1) {
                x1 -= 40;
                x2 += 40;
            }

            if (y2 > y1) {
                y1 += 40;
                y2 -= 40;
            } else if (y2 < y1) {
                y1 -= 40;
                y2 += 40;
            }
        } else {
            if (x1 > x2) {
                if (y1 > y2) {
                    x1 -= 28.28;
                    y1 -= 28.28;
                    x2 += 28.28;
                    y2 += 28.28;
                } else {
                    x1 -= 28.28;
                    y1 += 28.28;
                    x2 += 28.28;
                    y2 -= 28.28;
                }
            } else {
                if (y1 > y2) {
                    x1 += 28.28;
                    y1 -= 28.28;
                    x2 -= 28.28;
                    y2 += 28.28;
                } else {
                    x1 += 28.28;
                    y1 += 28.28;
                    x2 -= 28.28;
                    y2 -= 28.28;
                }
            }
        }

        lines.add(new Line(x1,y1,x2,y2));
    }
    public List<Line> getLines(){
        return lines;
    }
    public void clearLines(){
        lines.clear();
    }
}