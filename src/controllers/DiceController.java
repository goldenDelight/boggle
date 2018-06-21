package controllers;

import models.DiceModel;
import java.util.List;

public class DiceController {

    final private DiceModel dice = DiceModel.getModel();
//    final private static ButtonGridController boardController = new ButtonGridController();
//    final private static FoundController foundController = new FoundController();


    public List<String> rollDice() {
        return dice.reRoll();
    }
}
