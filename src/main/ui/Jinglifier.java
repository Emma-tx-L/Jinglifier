package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Slider tempoSlider;

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
        setUpMinorButton();
        setUpResetButton();
/*        setUpStopButton();*/
        setUpSliders();

        primaryStage.setScene(new Scene(grid, WIDTH, HEIGHT));
        primaryStage.show();
    }

    /*private void setUpSliders() {
        noteSlider = new Slider(22, 108, 60);
        noteSlider.setBlockIncrement(1);

        GridPane.setConstraints(noteSlider, 1,4);
        grid.getChildren().add(noteSlider);

        instrSlider = new Slider(0, 80, 10);
        instrSlider.setBlockIncrement(1);

        GridPane.setConstraints(instrSlider, 1, 5);
        grid.getChildren().add(instrSlider);

        tempoSlider = new Slider(100, 800, 200);
        tempoSlider.setBlockIncrement(20);

        GridPane.setConstraints(tempoSlider, 1, 6);
        grid.getChildren().add(tempoSlider);

        setChangeKeyButton();
        setChangeInstrumentButton();
        setChangeTempoButton();
    }
*/
    private void setUpSliders() {
        noteSlider = new Slider(22, 108, player1.BASENOTE);
        noteSlider.setBlockIncrement(1);

        noteSlider.valueProperty().addListener(
                (observable, oldvalue, newvalue) ->
                {
                    int note = (int) noteSlider.getValue();
                    player1.setBaseNote(note);
                } );
        grid.getChildren().add(noteSlider);
        GridPane.setConstraints(noteSlider, 2,4);

        instrSlider = new Slider(0, 80, player1.INSTRUMENT);
        instrSlider.valueProperty().addListener(
                (observable, oldvalue, newvalue) ->
                {
                    int note = (int) instrSlider.getValue();
                    player1.setInstrument(note);
                } );
        instrSlider.setBlockIncrement(1);
        GridPane.setConstraints(instrSlider, 2, 5);
        grid.getChildren().add(instrSlider);

        tempoSlider = new Slider(100, 800, player1.TEMPO);
        tempoSlider.setBlockIncrement(20);
        tempoSlider.valueProperty().addListener(
                (observable, oldvalue, newvalue) ->
                {
                    int note = (int) tempoSlider.getValue();
                    player1.setTempo(note);
                } );
        GridPane.setConstraints(tempoSlider, 2, 6);
        grid.getChildren().add(tempoSlider);

        setUpLabels();
    }

    private void setUpLabels() {
        Label noteSlider = new Label("Change Key");
        GridPane.setConstraints(noteSlider, 1, 4);
        grid.getChildren().add(noteSlider);

        Label instrSlider = new Label("Change Instrument");
        GridPane.setConstraints(instrSlider, 1, 5);
        grid.getChildren().add(instrSlider);

        Label tempoSlider = new Label("Change Tempo");
        GridPane.setConstraints(tempoSlider, 1, 6);
        grid.getChildren().add(tempoSlider);
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

    private void setChangeTempoButton() {
        Button tempoBtn = new Button();
        tempoBtn.setText("Change Tempo");
        tempoBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int note = (int) tempoSlider.getValue();
                player1.setTempo(note);
            }
        });

        GridPane.setConstraints(tempoBtn, 2,6);
        grid.getChildren().add(tempoBtn);
    }

    private void setUpJingleButton() {
        jingleBtn = new Button();
        jingleBtn.setText("Jinglify!");
        jingleBtn.setDefaultButton(true);
        jingleBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                input = TEXT.getText();
                player1.setStop(false);
                player1.playString(input);
            }
        });

        GridPane.setConstraints(jingleBtn, 1,2);
        grid.getChildren().add(jingleBtn);
    }

    private void setUpStopButton() {
        Button stopBtn = new Button();
        stopBtn.setText("Stop");
        stopBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                player1.setStop(true);
            }
        });

        GridPane.setConstraints(stopBtn, 2,2);
        grid.getChildren().add(stopBtn);
    }

    private void setUpMinorButton() {
        final Button minorBtn = new Button();
        minorBtn.setText("Minor/Major");
        minorBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (player1.getMinor()) {
                    player1.setMinor(false);
                } else {
                    player1.setMinor(true);
                }
            }
        });

        GridPane.setConstraints(minorBtn, 2,2);
        grid.getChildren().add(minorBtn);
    }

    private void setUpResetButton() {
        final Button resetBtn = new Button();
        resetBtn.setText("Reset");
        resetBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                player1.reset();
                instrSlider.setValue(player1.INSTRUMENT);
                tempoSlider.setValue(player1.TEMPO);
                noteSlider.setValue(player1.BASENOTE);
            }
        });

        GridPane.setConstraints(resetBtn, 1,7, 2, 1);
        grid.getChildren().add(resetBtn);
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
