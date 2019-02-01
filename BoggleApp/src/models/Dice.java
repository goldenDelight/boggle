package models;

import java.util.*;
import java.util.Random;

public class Dice {

    private final Random RANDOM_DIE_SIDE = new Random();
    private String[] rolledLetters;
    private String[][] letterGrid;

    //    Model "Offical" Boggle models.Dice as String Arrays
    private final String[] DIE_0 = {"A", "A", "C", "I", "O", "T"};
    private final String[] DIE_1 = {"A", "B", "I", "L", "T", "Y"};
    private final String[] DIE_2 = {"A", "B", "J", "M", "O", "Qu"};
    private final String[] DIE_3 = {"A", "C", "E", "L", "R", "S"};
    private final String[] DIE_4 = {"A", "C", "E", "M", "P", "R"};
    private final String[] DIE_5 = {"A", "D", "E", "N", "V", "Z"};
    private final String[] DIE_6 = {"A", "H", "M", "O", "R", "S"};
    private final String[] DIE_7 = {"B", "F", "I", "O", "R", "X"};
    private final String[] DIE_8 = {"D", "E", "N", "O", "S", "W"};
    private final String[] DIE_9 = {"D", "K", "N", "O", "T", "U"};
    private final String[] DIE_10 = {"E", "E", "F", "H", "I", "Y"};
    private final String[] DIE_11 = {"E", "G", "I", "N", "T", "V"};
    private final String[] DIE_12 = {"E", "G", "K", "L", "U", "Y"};
    private final String[] DIE_13 = {"E", "H", "I", "N", "P", "S"};
    private final String[] DIE_14 = {"E", "L", "P", "S", "T", "U"};
    private final String[] DIE_15 = {"G", "I", "L", "R", "U", "W"};

    private final List<String[]> BOGGLE_DIE_SET = new ArrayList<>(Arrays.asList(DIE_0, DIE_1, DIE_2, DIE_3, DIE_4, DIE_5, DIE_6, DIE_7, DIE_8, DIE_9, DIE_10, DIE_11, DIE_12, DIE_13, DIE_14, DIE_15));


    public void generateLetterGrid() {
        Collections.shuffle(BOGGLE_DIE_SET);
        rolledLetters = new String[15];
        letterGrid = new String[3][3];

        for (String[] die : BOGGLE_DIE_SET) {
            String letter = die[RANDOM_DIE_SIDE.nextInt(6)];
        }
        int i = 0;
        int side;
        String[] die;

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {

                die = BOGGLE_DIE_SET.get(i);
                side = RANDOM_DIE_SIDE.nextInt(6);

                letterGrid[r][c] = die[side];

                i++;
            }
        }
        return;
    }

    public String[][] getLetterGrid() {
        return letterGrid;
    }
}


