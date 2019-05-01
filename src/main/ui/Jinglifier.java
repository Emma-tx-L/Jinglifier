package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.NotePlayer;

public class Jinglifier extends Application {
    private final TextArea TEXT = new TextArea ();
    private final int WIDTH = 400;
    private final int HEIGHT = 500;

    private GridPane grid;
    private Button jingleBtn;
    private Slider noteSlider;
    private Slider instrSlider;

    private String input;
    private NotePlayer player1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Jinglifier");
        initializeNotePlayer();

        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        setUpTextArea();
        setUpJingleButton();
        setUpSliders();

        primaryStage.setScene(new Scene(grid, WIDTH, HEIGHT));
        primaryStage.show();
    }

    private void setUpSliders() {
        noteSlider = new Slider(22, 108, 60);
        noteSlider.setBlockIncrement(1);

        GridPane.setConstraints(noteSlider, 1,4);
        grid.getChildren().add(noteSlider);

        instrSlider = new Slider(0, 80, 10);
        instrSlider.setBlockIncrement(1);

        GridPane.setConstraints(instrSlider, 1, 5);
        grid.getChildren().add(instrSlider);


        setChangeKeyButton();
        setChangeInstrumentButton();
    }

    private void setChangeKeyButton() {
        Button keyBtn = new Button();
        keyBtn.setText("Change Key");
        keyBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int note = (int) noteSlider.getValue();
                player1.setBaseNote(note);
            }
        });

        GridPane.setConstraints(keyBtn, 2,4);
        grid.getChildren().add(keyBtn);
    }

    private void setChangeInstrumentButton() {
        Button insBtn = new Button();
        insBtn.setText("Change Instrument");
        insBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int note = (int) instrSlider.getValue();
                player1.setInstrument(note);
            }
        });

        GridPane.setConstraints(insBtn, 2,5);
        grid.getChildren().add(insBtn);
    }

    private void setUpJingleButton() {
        jingleBtn = new Button();
        jingleBtn.setText("Jinglify!");
        jingleBtn.setDefaultButton(true);
        jingleBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                input = TEXT.getText();
                player1.playString(input);
            }
        });

        GridPane.setConstraints(jingleBtn, 1,2);
        grid.getChildren().add(jingleBtn);
    }

    private void setUpTextArea() {
        TEXT.setText("Type something here!");
        TEXT.setPrefColumnCount(20);
        TEXT.setPrefRowCount(10);
        TEXT.setWrapText(true);

        GridPane.setConstraints(TEXT, 1,1, 2, 1);
        grid.getChildren().add(TEXT);
    }

    private void initializeNotePlayer() {
        player1 = new NotePlayer();
    }
}
