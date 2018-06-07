package models;

import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class LineModel {

    private final static LineModel model = new LineModel();
    public static LineModel getModel(){
        return model;
    }


    private List<Line> lines = new ArrayList<>();

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
