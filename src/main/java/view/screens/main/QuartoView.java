package main.java.view.screens.main;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class QuartoView extends GridPane {
    // private Node attributes (controls)
    private final int numColums = 5;
    private final int numRows = 8;

    private Text title;
    private Button startGame;
    private Button leaderboard;
    private Button tutorial;
    private Button exit;

    public QuartoView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        // create and configure controls
        // button = new Button("...")
        // label = new Label("...")
        startGame = new Button("Start Game");
        leaderboard = new Button("Leaderboard");
        tutorial = new Button("Tutorial");
        exit = new Button("Exit");
        title = new Text("QUARTO");
    }

    private void layoutNodes() {
        // add/set … methodes
        // Insets, padding, alignment, …

        this.setPadding(new Insets(10));

        for (int i = 0; i < numColums; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numColums);
            this.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            this.getRowConstraints().add(rowConst);
        }

        this.setGridLinesVisible(false);
        this.setHgap(8);
        this.setVgap(8);

        title.textAlignmentProperty();
        this.add(title, 1, 1, 3, 1);
        setHalignment(title, HPos.CENTER);
        title.setStyle("-fx-font-weight: BOLD;" +
                "-fx-font-size: 45;");

        addButton(startGame, 1, 3, 3, 1);
        addButton(leaderboard, 1, 4, 3, 1);
        addButton(tutorial, 1, 5, 3, 1);
        addButton(exit, 1, 6, 3, 1);
    }

    private void addButton(Button button, int i1, int i2, int i3, int i4) {
        this.add(button, i1, i2, i3, i4);
        button.setPrefHeight(25);
        button.setPrefWidth(200);
        button.setAlignment(Pos.CENTER);
        setHalignment(button, HPos.CENTER);
        button.setStyle("-fx-font-weight: BOLD");
    }

    // package-private Getters
    // for controls used by Presenter
    Text getTitle() {
        return title;
    }

    Button getStartGame() {
        return startGame;
    }

    Button getLeaderboard() { return leaderboard; }

    Button getTutorial() {
        return tutorial;
    }

    Button getExit() {
        return exit;
    }
}