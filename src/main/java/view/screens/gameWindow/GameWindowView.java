package main.java.view.screens.gameWindow;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameWindowView extends GridPane {
    // private Node attributes (controls)
    private Button pauseGame;
    private Button saveGame;
    private Button endGame;

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

    }

    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, …
        addButton(pauseGame, 8, 7, 5, 1);
        addButton(saveGame, 1, 7, 3, 1);
        addButton(endGame, 4, 7, 3, 1);
    }



    private void addButton(Button button, int i1, int i2, int i3, int i4){
        this.add(button, i1, i2, i3, i4 );
        button.setPrefHeight(25);
        button.setPrefWidth(200);
        button.setAlignment(Pos.CENTER);
        setHalignment(button, HPos.CENTER);
        button.setStyle("-fx-font-weight: BOLD");
    }

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

