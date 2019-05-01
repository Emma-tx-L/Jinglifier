package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.NotePlayer;

public class Jinglifier extends Application {
    private String input;
    private NotePlayer player1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        initializeNotePlayer();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        final TextArea text = new TextArea ();
        text.setText("Type something here!");
        text.setPrefColumnCount(20);
        text.setPrefRowCount(10);
        text.setWrapText(true);
        GridPane.setConstraints(text, 1,1);
        grid.getChildren().add(text);

        Button btn = new Button();
        btn.setText("Jinglify!");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                input = text.getText();
                player1.playString(input);
            }
        });
        GridPane.setConstraints(btn, 1,2);
        grid.getChildren().add(btn);


/*        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(text);
        primaryStage.setScene(new Scene(root, 300, 250));*/

        primaryStage.setScene(new Scene(grid, 300, 250));
        primaryStage.show();
    }

    private void initializeNotePlayer() {
        player1 = new NotePlayer();
    }
}
