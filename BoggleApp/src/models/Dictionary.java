package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Dictionary {

    private TreeSet<String> solutionSet;
    private String[][] letterTileGrid;

    private final HashSet<String> COMPLETE_DICTIONARY_SET = new HashSet<>();
    private final Path DICTIONARY_FILE_PATH = FileSystems.getDefault().getPath("words.txt");


    public int pointValue(String word){
        if(word.length() >= 8){
            return 11;
        } else if(word.length() == 7){
            return 5;
        } else if(word.length() == 6){
            return 3;
        } else if(word.length() == 5){
            return 2;
        } else {
            return 1;
        }
    }

    public void initializeDictionary() throws IOException {
        try {
            BufferedReader masterReader = Files.newBufferedReader(DICTIONARY_FILE_PATH);
//            System.out.println("reader made");
            while (masterReader.ready()) {
                COMPLETE_DICTIONARY_SET.add(masterReader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TreeSet<String> solutionGenerator(String[][] newLetterGrid) throws Exception {
        letterTileGrid = newLetterGrid;
        solutionSet = new TreeSet<>();

            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    recursiveWordFinder(r, c, "", COMPLETE_DICTIONARY_SET);
                }
            }
            System.out.println("Words Possible: " + solutionSet.size());
        return solutionSet;
    }
    private HashSet<String> dicEntryParser(HashSet<String> dictionary, String wordStem){
        for (String dicEntry : dictionary) {
            if (dictionary.contains(wordStem.toUpperCase())) {         // change to check by entry
                dictionary.remove(wordStem.toUpperCase());             // instead of using built-in
                solutionSet.add(wordStem.toUpperCase());        // collections method?
            }
            else if (dicEntry.startsWith(wordStem.toUpperCase())) {
                dictionary.add(dicEntry);
            }
            else {
                dictionary.remove(dicEntry);
            }
        }
        return dictionary;
    }
    public void recursiveWordFinder(int r, int c, String wordStem, HashSet<String> rawDictionary) {

        //        Update string with next letter
        wordStem = wordStem + letterTileGrid[r][c];


        if (rawDictionary == COMPLETE_DICTIONARY_SET) {
            rawDictionary = new HashSet<String>();
        }
        else {
            rawDictionary = dicEntryParser(rawDictionary, wordStem); //Parse dic for found words, keeps only pertinent words
        }

        if (rawDictionary.size() == 0) {
            return;
        }

//        Recursive call through letter grid.
//          Checks: grid edge bounds then valid adjacent tiles N-NW clockwise

        if (r > 0 && letterTileGrid[r - 1][c].equals(letterTileGrid[r - 1][c].toLowerCase())) {
            letterTileGrid[r][c] = letterTileGrid[r][c].toUpperCase();
            recursiveWordFinder(r - 1, c, wordStem, rawDictionary);
            letterTileGrid[r][c] = letterTileGrid[r][c].toLowerCase();
        }
        if ((r > 0 && c < 3) && letterTileGrid[r - 1][c + 1].equals(letterTileGrid[r - 1][c + 1].toLowerCase())) {
            letterTileGrid[r][c] = letterTileGrid[r][c].toUpperCase();
            recursiveWordFinder(r - 1, c + 1, wordStem, rawDictionary);
            letterTileGrid[r][c] = letterTileGrid[r][c].toLowerCase();
        }
        if (c < 3 && letterTileGrid[r][c + 1].equals(letterTileGrid[r][c + 1].toLowerCase())) {
            letterTileGrid[r][c] = letterTileGrid[r][c].toUpperCase();
            recursiveWordFinder(r, c + 1, wordStem, rawDictionary);
            letterTileGrid[r][c] = letterTileGrid[r][c].toLowerCase();
        }
        if ((r < 3 && c < 3) && letterTileGrid[r + 1][c + 1].equals(letterTileGrid[r + 1][c + 1].toLowerCase())) {
            letterTileGrid[r][c] = letterTileGrid[r][c].toUpperCase();
            recursiveWordFinder(r + 1, c + 1, wordStem, rawDictionary);
            letterTileGrid[r][c] = letterTileGrid[r][c].toLowerCase();
        }
        if (r < 3 && letterTileGrid[r + 1][c].equals(letterTileGrid[r + 1][c].toLowerCase())) {
            letterTileGrid[r][c] = letterTileGrid[r][c].toUpperCase();
            recursiveWordFinder(r + 1, c, wordStem, rawDictionary);
            letterTileGrid[r][c] = letterTileGrid[r][c].toLowerCase();
        }
        if (r < 3 && c > 0 && letterTileGrid[r + 1][c - 1].equals(letterTileGrid[r + 1][c - 1].toLowerCase())) {
            letterTileGrid[r][c] = letterTileGrid[r][c].toUpperCase();
            recursiveWordFinder(r + 1, c - 1, wordStem, rawDictionary);
            letterTileGrid[r][c] = letterTileGrid[r][c].toLowerCase();
        }
        if (c > 0 && letterTileGrid[r][c - 1].equals(letterTileGrid[r][c - 1].toLowerCase())) {
            letterTileGrid[r][c] = letterTileGrid[r][c].toUpperCase();
            recursiveWordFinder(r, c - 1, wordStem, rawDictionary);
            letterTileGrid[r][c] = letterTileGrid[r][c].toLowerCase();
        }
        if (r > 0 && c > 0 && letterTileGrid[r - 1][c - 1].equals(letterTileGrid[r - 1][c - 1].toLowerCase())) {
            letterTileGrid[r][c] = letterTileGrid[r][c].toUpperCase();
            recursiveWordFinder(r - 1, c - 1, wordStem, rawDictionary);

//            No more words possible, steps back 1 tile
            letterTileGrid[r][c] = letterTileGrid[r][c].toLowerCase();
        }
        return;
    }
}
