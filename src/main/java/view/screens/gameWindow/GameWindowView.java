package main.java.view.screens.gameWindow;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.nio.file.Paths;

public class GameWindowView extends BorderPane {
    // private Node attributes (controls)
    private Button pauseGame;
    private Button saveGame;
    private Button endGame;

    private Text gameTitle;
    private Text time;
    private Text turns;
    private Label timeCounter;
    private Label turnCounter;

    FlowPane pieces = new FlowPane(Orientation.VERTICAL);

    public GameWindowView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        // create and configure controls
        // button = new Button("...")
        // label = new Label("...")
        pauseGame = new Button("Pause");
        saveGame = new Button("Save");
        endGame = new Button("End");

        gameTitle = new Text("QUARTO");
        time = new Text("Time: ");
        turns = new Text("Turns: ");
        timeCounter = new Label("test");
        turnCounter = new Label("test");


        pieces.setVgap(8);
        pieces.setHgap(8);
        pieces.setPrefWrapLength(500);

        for (int i=1; i<=16; i++) {
            pieces.getChildren().add(new ImageView(Paths.get("resources/media/images/"+i+".png").toUri().toString()));
        }
    }

    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, …
//        addButton(pauseGame, 1, 0, 3, 1);
//        addButton(saveGame, 0, 1, 4, 2);
//        addButton(endGame, 0, 2, 3, 1);
//        GridPane gridPane = new GridPane();
//        gridPane.setGridLinesVisible(true);
//        gridPane.setHgap(10);
//        gridPane.setVgap(10);

        this.setTop(gameTitle);
        this.setCenter(pieces);
        this.setBottom(pauseGame);
        this.setBottom(saveGame);
        this.setBottom(endGame);






    }



//    private void addButton(Button button, int i1, int i2, int i3, int i4){
//        this.add(button, i1, i2, i3, i4);
//        button.setPrefHeight(25);
//        button.setPrefWidth(50);
//        button.setStyle("-fx-font-weight: BOLD");
//    }

    // package-private Getters
    // for controls used by Presenter


    public Button getPauseGame() {
        return pauseGame;
    }

    public Button getSaveGame() {
        return saveGame;
    }

    public Button getEndGame() {
        return endGame;
    }


}

