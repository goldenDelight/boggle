package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DicModel {

    private String[][] board = new String[4][4];
    private HashSet<String> rawDic = new HashSet<>();
    private BufferedReader reader;
    private TreeSet<String> foundWords = new TreeSet<>();
//    private String word;

    Path rawDicPath = FileSystems.getDefault().getPath("words.txt");

    private final static DicModel model = new DicModel();
    public static DicModel getModel(){
        return model;
    }


    public void DicMaker() throws IOException {

        try {
            BufferedReader rawReader = Files.newBufferedReader(rawDicPath);
            String line;

            while ((line = rawReader.readLine()) != null) {
                rawDic.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("dic is " + rawDic.size() + " words long");
    }

    public void updateBoardLetters(String[][] newLetters){
        foundWords.clear();
//        System.out.println("update bopard letters");
        board = newLetters;

        try {
//            System.out.println("try success");
            boardWordsFinder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<String> getSolution(){
        return foundWords;
    }

    public void boardWordsFinder() throws Exception {
//        System.out.println("boardwordsfinder");
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
//                System.out.println("r=" + r + " c=" + c);
                wordFinder(r, c, "", rawDic);
            }
        }

        System.out.println(foundWords.size() + " words found");
    }

    public void wordFinder(int r, int c, String word, HashSet<String> rawDic) {

//        Update string with letter of current coordinates
        word = word + board[r][c];

        System.out.println("New String = " + word);

//        Create a fresh "working" dic
        HashSet<String> abridgedDic = new HashSet<String>();


//        Check parent dictionary for words prefixed w/current string
        for (String dicEntry : rawDic) {
            if (dicEntry.startsWith(word.toUpperCase())) {

//                Add words with matching prefix to an updated "working" dictionary
                abridgedDic.add(dicEntry);
            }
        }

        System.out.println("working dic size: " + abridgedDic.size());


//        Check "working" dic for entries matching entire string
        if (abridgedDic.contains(word.toUpperCase())) {

//            Remove found word from parent dic (prevents finding duplicates)
            rawDic.remove(word.toUpperCase());

//            Add matching word to list of found words
            foundWords.add(word.toUpperCase());
            System.out.println(word.toUpperCase() + " added to found list");
        }

        System.out.println("");


//        Returns recursive call if "working" dic has no words w/string as prefix
        if (abridgedDic.size() == 0){
            return;
        }


//            Check North (w/in bounds, lowercase)
        if (r > 0 && board[r - 1][c].equals(board[r - 1][c].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive Call: inputs North coordinates as current coordinates
            wordFinder(r - 1, c, word, abridgedDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check NorthEast (w/in bounds, lowercase)
        if ((r > 0 && c < 3) && board[r - 1][c + 1].equals(board[r - 1][c + 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs NorthEast coordinates as current coordinates
            wordFinder(r - 1, c + 1, word, abridgedDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check East (w/in bounds, lowercase)
        if (c < 3 && board[r][c + 1].equals(board[r][c + 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs East coordinates as current coordinates
            wordFinder(r, c + 1, word, abridgedDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check SouthEast (w/in bounds, lowercase)
        if ((r < 3 && c < 3) && board[r + 1][c + 1].equals(board[r + 1][c + 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs SouthEast coordinates as current coordinates
            wordFinder(r + 1, c + 1, word, abridgedDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check South (w/in bounds, lowercase)
        if (r < 3 && board[r + 1][c].equals(board[r + 1][c].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs South coordinates as current coordinates
            wordFinder(r + 1, c, word, abridgedDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check SouthWest (w/in bounds, lowercase)
        if (r < 3 && c > 0 && board[r + 1][c - 1].equals(board[r + 1][c - 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs South coordinates as current coordinates
            wordFinder(r + 1, c - 1, word, abridgedDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check West (w/in bounds, lowercase)
        if (c > 0 && board[r][c - 1].equals(board[r][c - 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs South coordinates as current coordinates
            wordFinder(r, c - 1, word, abridgedDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }


//        Check NorthWest (w/in bounds, lowercase)
        if (r > 0 && c > 0 && board[r - 1][c - 1].equals(board[r - 1][c - 1].toLowerCase())) {

//            Capitalize current letter (denotes visited)
            board[r][c] = board[r][c].toUpperCase();

//            Recursive call: inputs South coordinates as current coordinates
            wordFinder(r - 1, c - 1, word, abridgedDic);

//            No more words possible with this string, "undo" adding this letter to string
            board[r][c] = board[r][c].toLowerCase();
        }

        return;
    }

    public Set<String> getfound(){
        return foundWords;
    }
}
