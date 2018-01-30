package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;



public class Main extends Application{

   public static MediaPlayer mediaplayer;
   public static Media sound;



    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("GUI");
        primaryStage.setScene(new Scene(root, 280, 400));
        primaryStage.show();

    }




    public static void main(String[] args) {


        launch(args);
    }
}

