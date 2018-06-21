package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.WipModel;
import models.BoggleButton;

import java.io.IOException;

public class WipController {

    private WipModel wip = WipModel.getModel();

    final private static GraphicsController GRAPHICS_CONTROLLER = new GraphicsController();

    @FXML
    Label wipLabel;

    public void initialize() throws IOException {
        wip.linkWordProgress(wipLabel);
    }

    public void letterClicked(BoggleButton b){
        wip.appendWip(b.getLetter());
        GRAPHICS_CONTROLLER.graphicsFactory(b);
    }

    public void clearWIP(){
        wip.clearWip();
    }

    public String getWIP(){
        return wip.getWip();
    }
}
