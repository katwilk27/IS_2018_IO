package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;

public class Controller implements Initializable
{
    @FXML private Button b1;
    @FXML private Button b2;
    @FXML private Button b3;
    @FXML private Button b4;
    @FXML private Button b5;
    @FXML private Button b6;
    @FXML private Button b7;
    @FXML private Button b8;
    @FXML private Button b9;
    @FXML private Button b0;
    @FXML private Button clear;
    @FXML private Button enter;
    @FXML private Button change;
    @FXML private Button logw;
    @FXML private Button block;
    @FXML private Label text;
    @FXML private Button deserial;

    @FXML private Circle dioda;

    private boolean change_PSW = false;
    private String PIN;
    private String kod="1111";
    private StringBuilder builder =new StringBuilder();
    private File log_file = new File("Log.txt");
    Serialization serialization = new Serialization();


    public Controller() throws IOException {
    }

    @ Override
    public void initialize (URL location, ResourceBundle resources)
    {
        dioda.setFill(RED);
        changelog(true);
        text.setText("Wprowadz haslo:");


        final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10000), e-> {
            changelog(true);
            text.setText("Zbyt długie wprowadzanie kodu");
            builder.setLength(0);
        }));

        b1.setOnAction(e ->
        {
            addToText("1");
            timeline.play();
        });
        b2.setOnAction(e ->
        {
            addToText("2");
            timeline.play();
        });
        b3.setOnAction(e ->
        {
            addToText("3");
            timeline.play();
        });
        b4.setOnAction(e->
        {
            addToText("4");
            timeline.play();
        });
        b5.setOnAction(e->
        {
            addToText("5");
            timeline.play();
        });
        b6.setOnAction(e->
        {
            addToText("6");
            timeline.play();
        });
        b7.setOnAction(e->
        {
            addToText("7");
            timeline.play();
        });
        b8.setOnAction(e->
        {
            addToText("8");
            timeline.play();
        });
        b9.setOnAction(e->
        {
            addToText("9");
            timeline.play();
        });
        b0.setOnAction(e->
        {
            addToText("0");
            timeline.play();
        });

        clear.setOnAction(e->
        {
            builder.setLength(0);
            text.setText(" ");

        });
        enter.setOnAction (e->
        {
            if(change_PSW == false) {
                try {
                    checkedPassword();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                text.setText(" ");
                timeline.stop();
            }
            else {
                kod = PIN;
                text.setText(" ");
                text.setText("Haslo zostalo zmienione");
                change_PSW = false;
            }
        });

        change.setOnAction(e->
        {
            text.setText("Wprowadz nowe haslo: ");
            change_PSW = true;
        });

        block.setOnAction(e-> {
            changelog(true);
        });

        logw.setOnAction(e -> {
            try {
                Desktop.getDesktop().edit(log_file);
            } catch (IOException e1) {
                e1.printStackTrace();
                text.setText("Plik nieodnaleziony");
            }
        });

        deserial.setOnAction(e ->
                System.out.println(serialization.deserializeDate())
        );
    }

    private void addToText(String l)
    {
        builder.append(l);
        PIN = builder.toString();
        text.setText(PIN);
    }

    private void changelog(boolean log)
    {
        deserial.setDisable(log);
        change.setDisable(log);
        logw.setDisable(log);
        block.setDisable(log);
        dioda.setFill(RED);
        //  text.setText("  ");

    }
    private void checkedPassword() throws IOException {
        if(PIN.equals(kod)) {
            text.setText("poprawny kod");
            changelog(false);
            dioda.setFill(GREEN);
        }
        else {
            text.setText("niepoprawny kod");
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            System.out.println(currentDate);
            System.out.println(PIN);
            String filename = "Log.txt";
            FileWriter fw = new FileWriter(filename,true);
            fw.write(currentDate + "\t" + PIN+"\n");
            fw.write(System.lineSeparator());
            fw.close();
            serialization.serialize(currentDate,PIN);
        }
    }
}

