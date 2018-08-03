package models;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TimerModel implements EventHandler<ActionEvent>, Runnable {

    private final int START_TIME = 180;
    private static int seconds;
    private static boolean running;
    private static Thread counter;

    public TimerModel(){
        seconds = START_TIME;
        running = true;
    }

    public static void countdown() {
        System.out.println("countdown initiated");
        try {
            System.out.println("?");
            for (int i = seconds; i > 0; i--) {
                if (!running) {
                    Thread.currentThread().sleep(100000000);
                } else {
                    Thread.sleep(1000);
                    System.out.println(i + " seconds");
                    seconds = i;
                }
                if (seconds == 0) {
                    System.out.println("DONE!");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("BT run fired");
        Thread counter = new Thread(TimerModel::countdown);
        counter.start();
    }

    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();

        if (button.getText().equals("start")) {
            System.out.println("BT 'start' event registered");
            resume();
        }
        if (button.getText().equals("stop")) {
            System.out.println("BT 'stop' event registered");
            running = false;
        }
    }
    public static void freeze() {
        System.out.println("freezing timer?");
        running = false;
    }

    public static void resume() {
        System.out.println("resuming timer?");
        running = true;
        new Thread(() -> TimerModel.countdown());
    }

}
