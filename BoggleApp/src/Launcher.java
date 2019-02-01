import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Launcher extends Application{

    @FXML
    Button newGame;

    Stage primaryStage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void click() throws Exception{
        Stage game = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("views/Main.fxml"));

        game.setTitle("Dodgeball!");
        game.setScene(new Scene(root, 650, 600));
        game.show();
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/Main.fxml"));

        primaryStage.setTitle("Dodgeball!");
        primaryStage.setScene(new Scene(root, 700, 575));
        primaryStage.show();

//        Parent root = FXMLLoader.load(getClass().getResource("views/Splash.fxml"));
//
//        primaryStage.setTitle("hello plz");
//        primaryStage.setScene(new Scene(root, 150, 100));
//        primaryStage.show();
    }
}
