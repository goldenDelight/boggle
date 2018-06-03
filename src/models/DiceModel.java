package models;

import java.util.*;

public class DiceModel {

//    Singleton Initialization
    private final static DiceModel dice = new DiceModel();
    public static DiceModel getModel(){
        return dice;
    }

//    Model "Offical" Boggle Dice as String Arrays
    final private String[] DIE_0 =  {"A", "A", "C", "I", "O", "T"};
    final private String[] DIE_1 =  {"A", "B", "I", "L", "T", "Y"};
    final private String[] DIE_2 =  {"A", "B", "J", "M", "O", "Qu"};
    final private String[] DIE_3 =  {"A", "C", "E", "L", "R", "S"};
    final private String[] DIE_4 =  {"A", "C", "E", "M", "P", "R"};
    final private String[] DIE_5 =  {"A", "D", "E", "N", "V", "Z"};
    final private String[] DIE_6 =  {"A", "H", "M", "O", "R", "S"};
    final private String[] DIE_7 =  {"B", "F", "I", "O", "R", "X"};
    final private String[] DIE_8 =  {"D", "E", "N", "O", "S", "W"};
    final private String[] DIE_9 =  {"D", "K", "N", "O", "T", "U"};
    final private String[] DIE_10 = {"E", "E", "F", "H", "I", "Y"};
    final private String[] DIE_11 = {"E", "G", "I", "N", "T", "V"};
    final private String[] DIE_12 = {"E", "G", "K", "L", "U", "Y"};
    final private String[] DIE_13 = {"E", "H", "I", "N", "P", "S"};
    final private String[] DIE_14 = {"E", "L", "P", "S", "T", "U"};
    final private String[] DIE_15 = {"G", "I", "L", "R", "U", "W"};

    List<String[]> dieSet = new ArrayList<>(Arrays.asList(DIE_0, DIE_1, DIE_2,DIE_3,DIE_4,DIE_5,DIE_6,DIE_7,DIE_8,DIE_9,DIE_10,DIE_11,DIE_12,DIE_13,DIE_14,DIE_15));
    List<String> dieRolls = new ArrayList<>();


//    Simulate "rolling" dice
//    first shuffe order of dice in the set
    public List<String> reRoll() {
        Random randy = new Random();
        Collections.shuffle(dieSet);
        dieRolls.clear();

//        generate random 0-5 number for each die. Array index represents "side" facing up
//        add each "rolled" letter to an ArrayList
        for (String[] die: dieSet) {
            String letter = die[randy.nextInt(6)];
            dieRolls.add(letter);
        }

        return dieRolls;
    }
}
