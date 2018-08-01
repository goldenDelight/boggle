package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DicModel {

    private String[][] board = new String[4][4];
    private HashSet<String> masterDic = new HashSet<>();
    private TreeMap<String, Boolean> solutionSet = new TreeMap<>();

    Path masterDicPath = FileSystems.getDefault().getPath("words.txt");

    private final static DicModel model = new DicModel();
    public static DicModel getModel(){
        return model;
    }


    public void DicMaker() throws IOException {

        try {
            BufferedReader masterReader = Files.newBufferedReader(masterDicPath);
            System.out.println("reader made");

            while (masterReader.ready()) {
                masterDic.add(masterReader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBoardLetters(String[][] newLetters){

        solutionSet.clear();
        board = newLetters;

        try {
            boardWordsFinder();
            System.out.println("word finder activated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TreeMap<String, Boolean> getSolutionSet(){
        return solutionSet;
    }

    public void boardWordsFinder() throws Exception {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                wordFinder(r, c, "", masterDic);
            }
        }
    }

    public void wordFinder(int r, int c, String word, HashSet<String> parentDic) {

//        Update string with letter of current coordinates
        word = word + board[r][c];

//        Create a fresh "working" dic
        HashSet<String> childDic = new HashSet<String>();

//        Check parent dictionary for words prefixed w/current string
        for (String dicEntry : parentDic) {
            if (dicEntry.startsWith(word.toUpperCase())) {

//                Add words with matching prefix to an updated "working" dictionary
                childDic.add(dicEntry);
            }
        }

//        Check "working" dic for entries matching entire string
        if (childDic.contains(word.toUpperCase())) {

//            Remove found word from parent dic (prevents finding duplicates)
            parentDic.remove(word.toUpperCase());

//            Add matching word to list of found words
            solutionSet.put(word.toUpperCase(), false);

        }

//        Returns recursive call if "working" dic has no words w/string as prefix
        if (childDic.size() == 0){
            return;
        }


//        Check North (w/in bounds, lowercase)
        if (r > 0 && board[r - 1][c].equals(board[r - 1][c].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive Call: inputs North coordinates as current coordinates
            wordFinder(r - 1, c, word, childDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check NorthEast (w/in bounds, lowercase)
        if ((r > 0 && c < 3) && board[r - 1][c + 1].equals(board[r - 1][c + 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs NorthEast coordinates as current coordinates
            wordFinder(r - 1, c + 1, word, childDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check East (w/in bounds, lowercase)
        if (c < 3 && board[r][c + 1].equals(board[r][c + 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs East coordinates as current coordinates
            wordFinder(r, c + 1, word, childDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check SouthEast (w/in bounds, lowercase)
        if ((r < 3 && c < 3) && board[r + 1][c + 1].equals(board[r + 1][c + 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs SouthEast coordinates as current coordinates
            wordFinder(r + 1, c + 1, word, childDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check South (w/in bounds, lowercase)
        if (r < 3 && board[r + 1][c].equals(board[r + 1][c].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs South coordinates as current coordinates
            wordFinder(r + 1, c, word, childDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check SouthWest (w/in bounds, lowercase)
        if (r < 3 && c > 0 && board[r + 1][c - 1].equals(board[r + 1][c - 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs South coordinates as current coordinates
            wordFinder(r + 1, c - 1, word, childDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check West (w/in bounds, lowercase)
        if (c > 0 && board[r][c - 1].equals(board[r][c - 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs South coordinates as current coordinates
            wordFinder(r, c - 1, word, childDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check NorthWest (w/in bounds, lowercase)
        if (r > 0 && c > 0 && board[r - 1][c - 1].equals(board[r - 1][c - 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs South coordinates as current coordinates
            wordFinder(r - 1, c - 1, word, childDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }

        return;
    }

    public TreeMap<String, Boolean> getfound(){
        return solutionSet;
    }
}
