package controls;
import javafx.application.Platform;
import views.BoardView;
import javafx.event.ActionEvent;
import models.*;
import models.Board;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class Controller {

    private Board board;
    private BoardView boardView;
    private Dice dice;
    private Timer timer;
    private Dictionary dictionary;
    private LetterTile letterTile;
    private Data data;

    public Controller(Board boardModel,
                      BoardView boardView,
                      Dice dice,
                      Timer timer,
                      Data data,
                      Dictionary dictionary,
                      LetterTile letterTile){

        this.board = boardModel;
        this.boardView = boardView;
        this.dice = dice;
        this.timer = timer;
        this.dictionary = dictionary;
        this.letterTile = letterTile;
        this.data = data;
    }

///////////////////////GAME CONTROLS///////////////////////////////////

public void initializeComponents() {

        boardView.addCurrentListener(data.getCurrentWordProperty());
        boardView.addFoundListener(data.getFoundWordsList());

        data.importSolutionSet( dictionary.solutionGenerator(   ));
}

    ////////////////////////////DATA///////////////////////////////////////
    public void letterClicked(LetterTile selectedTile){
        if(isOver){
            return;
        }
        data.setCurrentWord(selectedTile.getLetter());
        GRAPHICS_CONTROLLER.graphicsFactory(selectedTile);
    }

    public void setCurrentWord(String word){
        board.setCurrentWord(word);
    }
    public String getCurrentWord(){
        return board.getCurrentWord();
    }
    public void clearCurrentWord(){
        board.clearCurrentWord();
    }
    public String submitCurrentWord(){
        return board.getCurrentWord();
    }


    ////////////////////////////DICTIONARY///////////////////////////////////////
    public TreeMap<String, Boolean> solutionSet;


    public void createSolutionSet() throws Exception {

        String[][] newTileGrid;
        dictionary.solutionGenerator(newTileGrid);
        return;
    }

    ////////////////////////////BOARD CONTROLS///////////////////////////////////////
    public void syncBoardView(){
        board.linkWordProgress(boardView.getCurrentWordLabel());
    }
    public void resetButtons(){
        letterGrid.resetButtons();
    }
    public void letterInput(LetterTile tile){
        if(gameOver){
            return;
        }
        if(letterGrid.valid(tile)){
            letterGrid.updateBoard(tile);
            tile.setButtonGraphicsPosition();
            tile.offsetX();
            tile.offsetY();
            board.addLetter(tile.getLetter());
            GRAPHICS_CONTROLLER.graphicsFactory(tile);
        } else if(letterGrid.first(tile)){
            // trigger cancel action
            MENU_CONTROLLER.cancelButtonClicked(new ActionEvent());
        } else if(letterGrid.last(tile)){
            // trigger submit action
            MENU_CONTROLLER.submitButton(new ActionEvent(), this.submitCurrentWord());
        } else {
            return;
        }
    }

    ////////////////////////////GRID CONTROLS///////////////////////////////////////
    public void letterClicked(LetterTile b){
        if(isOver){
            return;
        }
        if(letterGrid.valid(b)){
            letterGrid.updateBoard(b);
            BOARD_CONTROLLER.letterClicked(b);
        } else if(letterGrid.first(b)){
            // trigger cancel action
            MENU_CONTROLLER.cancelButtonClicked(new ActionEvent());
        } else if(letterGrid.last(b)){
            // trigger submit action
            MENU_CONTROLLER.submitButton(new ActionEvent(), BOARD_CONTROLLER.getWIP());
        } else {
            return;
        }
    }
    public void resetButtons(){
        letterGrid.resetButtons();
    }

    ////////////////////////////TIMER CONTROLS///////////////////////////////////////
    public void refreshTimer() {
        timer.interruptThread();
        timer.refreshTimer();
    }
    public void stopTimer(){
        timer.interruptThread();
    }
    private void addListeners(){
//        changeListener updates timeLabel when timeProperty changes
        timer.timeProperty().addListener( (v, oldValue, newValue) -> {
            Platform.runLater(() -> timeLabel.setText(timer.getTime()));
//            System.out.println(timer.getTime());
        });
//        changeListener triggers end of round when completedProperty becomes true
        timer.completedProperty().addListener((observable, oldValue, newValue) ->  {
            MENU_CONTROLLER.endRound(new ActionEvent());
        });
    }

    ////////////////////////////TILE CONTROLS///////////////////////////////////////
    public void letterClicked(LetterTile b) {
        if(isOver){
            return;
        }
        if (letterGrid.first(b)) {
            MENU_CONTROLLER.cancelButtonClicked(new ActionEvent());
        } else if (letterGrid.last(b)) {
            MENU_CONTROLLER.submitButton(new ActionEvent(), BOARD_CONTROLLER.submitCurrentWord());
        }
    }

    ////////////////////////////GRAPHICS CONTROLS///////////////////////////////////////
    public void graphicsFactory(LetterTile b){
        graphics.makeGraphics(b);
    }
    public void clearCircles(){
        graphics.clearGraphics();
    }
}
