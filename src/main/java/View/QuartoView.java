package main.java.View;

import javafx.scene.Node;
import javafx.scene.Parent;
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
        title = new Text("Quarto");
    }

    private void layoutNodes() {
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

        this.setGridLinesVisible(true);
// add/set … methodes
// Insets, padding, alignment, …

    }

// package-private Getters
// for controls used by Presenter

}