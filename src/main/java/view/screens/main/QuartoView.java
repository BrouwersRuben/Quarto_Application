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
        this.add(title, 1, 1, 3, 1 );
        this.setHalignment(title, HPos.CENTER);
        title.setStyle("-fx-font-weight: BOLD;" +
                        "-fx-font-size: 45;");

        this.add(startGame, 1, 3, 3, 1 );
        startGame.setPrefHeight(25);
        startGame.setPrefWidth(200);
        startGame.setAlignment(Pos.CENTER);
        this.setHalignment(startGame, HPos.CENTER);
        startGame.setStyle("-fx-font-weight: BOLD");

        this.add(leaderboard, 1, 4, 3, 1 );
        leaderboard.setPrefHeight(25);
        leaderboard.setPrefWidth(200);
        leaderboard.setAlignment(Pos.CENTER);
        this.setHalignment(leaderboard, HPos.CENTER);
        leaderboard.setStyle("-fx-font-weight: BOLD");

        this.add(tutorial, 1, 5, 3, 1 );
        tutorial.setPrefHeight(25);
        tutorial.setPrefWidth(200);
        tutorial.setAlignment(Pos.CENTER);
        this.setHalignment(tutorial, HPos.CENTER);
        tutorial.setStyle("-fx-font-weight: BOLD");

        this.add(exit, 1, 6, 3, 1 );
        exit.setPrefHeight(25);
        exit.setPrefWidth(200);
        exit.setAlignment(Pos.CENTER);
        this.setHalignment(exit, HPos.CENTER);
        exit.setStyle("-fx-font-weight: BOLD");
    }

// package-private Getters
// for controls used by Presenter
    public Text getTitle() {
        return title;
    }
    public Button getStartGame() {
        return startGame;
    }
    public Button getLeaderboard() {
        return leaderboard;
    }
    public Button getTutorial() {
        return tutorial;
    }
    public Button getExit() {
        return exit;
    }
}