package models;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.atomic.AtomicBoolean;

public class TimerModel implements Runnable{

    private final static TimerModel model = new TimerModel();
    public static TimerModel getModel(){
        return model;
    }

//    private final static AtomicBoolean running = new AtomicBoolean(false);
    private StringProperty time = new SimpleStringProperty(this, "time", "3:00");
    private BooleanProperty completed = new SimpleBooleanProperty(this, "completed", false);
    private Thread t;

    public void run() {

        for (int minutes = 2; minutes >= 0; minutes--) {
            for (int seconds = 59; seconds >= 0; seconds--) {

                try {
                    Thread.sleep(1000);
                    time.setValue(String.format(minutes + ":" + ("%02d"), seconds));
                } catch (InterruptedException e) {
                    time.setValue("3:00");
                    System.out.println("timer interrupted");
                    return;
                }
            }
        }
        Platform.runLater(() -> completed.setValue(true));
    }

    public void refreshTimer(){
        completed.setValue(false);
        time.setValue("3:00");
        t = new Thread(this);
        t.start();
    }

    public boolean getCompleted() {
        return completedProperty().get();
    }

    public BooleanProperty completedProperty() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        completedProperty().set(completed);
    }

    public void interruptThread(){
        t.interrupt();
    }

    public void stopThread(){
//        running.set(false);
//        t.interrupt();
    }

    public boolean checkComplete(){
        return completed.getValue();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public String getTime() {
        return time.get();
    }

    public void bindWith(StringProperty tm){
        time = tm;
    }
}