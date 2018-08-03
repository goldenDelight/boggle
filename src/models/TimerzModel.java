//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//
//public class TimerModel implements EventHandler<ActionEvent>, Runnable{
//    private final int START_TIME = 180;
//    private static int seconds;
//    private static boolean running;
//    private static Thread counter;
//
//    public TimerModel(){
//        seconds = START_TIME;
//        running = true;
//    }
//
//    public static void countdown() {
//        System.out.println("countdown initiated");
//        try {
//            System.out.println("?");
//            for (int i = seconds; i > 0; i--) {
//                if (!running) {
//                    Thread.currentThread().sleep(100000000);
//                } else {
//                    Thread.sleep(1000);
//                    System.out.println(i + " seconds");
//                    seconds = i;
//                }
//                if (seconds == 0) {
//                    System.out.println("DONE!");
//                }
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void run() {
//        System.out.println("BT run fired");
//        Thread counter = new Thread(TimerModel::countdown);
//        counter.start();
//    }
//
//    @Override
//    public void handle(ActionEvent event) {
//        Button button = (Button) event.getSource();
//
//        if (button.getText().equals("start")) {
//            System.out.println("BT 'start' event registered");
//            resume();
//        }
//        if (button.getText().equals("stop")) {
//            System.out.println("BT 'stop' event registered");
//            running = false;
//        }
//    }
//    public static void freeze() {
//        System.out.println("freezing timer?");
//        running = false;
//    }
//
//    public static void resume() {
//        System.out.println("resuming timer?");
//        running = true;
//        new Thread(() -> TimerModel.countdown());
//    }
//
//}

//    }
//}
//package tests;
//
//        import javafx.event.ActionEvent;
//        import javafx.event.Event;
//        import javafx.event.EventHandler;
//
//public class Countdown implements EventHandler{
//
//    @Override
//    public void handle (Event event) {
//        int seconds = 100;
//        boolean running = false;
//
//        for (seconds = seconds; seconds > 0; seconds--){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    public void startTimer(Event event) {
//        Event run = new ActionEvent();
//        this.handle(run);
//
//    }
//
//    public void stopTimer(Event event) {
//        Event pause = new ActionEvent();
//        this.handle(pause);
//    }
//
//}
//
//package modular;
//
//import javafx.beans.property.BooleanProperty;
//import javafx.beans.property.SimpleBooleanProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//
//public class Timer extends Marker {
//    private StringProperty time = new SimpleStringProperty(this, "time", "");
//    private BooleanProperty completed = new SimpleBooleanProperty(this, "completed", false);
//
//
//    @Override
//    public void run() {
////        System.out.println("Current Thread Name: " + currentThread().getName());
//        for (int minutes = 20000; minutes >= 0; minutes--) {
//            for (int seconds = 59; seconds >= 0; seconds--) {
//                try {
//                    Thread.sleep(1000);
//                    time.setValue(String.format(minutes + ":" + ("%02d"), seconds));
//
//                } catch (InterruptedException e) {
//                    return;
//                }
//            }
//        }
//
//        setCompleted(true);
//    }
//
//    public boolean getCompleted() {
//        return completedProperty().get();
//    }
//
//    public BooleanProperty completedProperty() {
//        return completed;
//    }
//
//    public void setCompleted(boolean completed) {
//        completedProperty().set(completed);
//    }
//
//    public StringProperty timeProperty() {
//        return time;
//    }
//
//    public String getTime() {
//        return time.get();
//    }
//}
