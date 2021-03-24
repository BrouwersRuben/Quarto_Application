package main.java.view.screens.winLoseWindow;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import main.java.model.dataBase.Leaderboard;

import java.nio.file.Paths;
import java.util.Collections;

public class winLoseWindowView extends BorderPane {
    // private Node attributes (controls)
    private VBox vBox;
    private HBox hBox;

    private Label winOrLose;
    private ImageView endGameStatus;
    private Label playerScore;
    private Label stat1;
    private Label stat2;
    private Label stat3;
    private LineChart lineChart;
    private Button mainMenu;
    private Button playAgain;
    private Button exitGame;
    private final double numRows = 7;

    public winLoseWindowView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        // create and configure controls
        // button = new Button("...")
        // label = new Label("...")
        winOrLose = new Label();
        endGameStatus =  new ImageView();
        playerScore = new Label();
        stat1 = new Label();
        stat2 = new Label();
        stat3 = new Label();

        lineChart = new LineChart<>(new CategoryAxis(), new NumberAxis());
        lineChart.setTitle("Time spent per move");

        mainMenu = new Button("Return to the main menu");
        playAgain = new Button("Play again");
        exitGame = new Button("Exit");
    }

    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, …

        labelStyle(playerScore);
        playerScore.setPadding(new Insets(17, 17, 17, 17));
        labelStyle(stat1);
        stat1.setPadding(new Insets(17, 17, 17, 17));
        labelStyle(stat2);
        stat2.setPadding(new Insets(17, 17, 17, 17));
        labelStyle(stat3);
        stat3.setPadding(new Insets(17, 17, 17, 17));

        endGameStatus.setFitHeight(125);
        endGameStatus.setFitWidth(125);
        winOrLose.setStyle("-fx-font-size: 35; fx-font-weight: BOLD");
        vBox = new VBox(winOrLose, endGameStatus, playerScore, stat1, stat2, stat3);
        vBox.setAlignment(Pos.CENTER);
        this.setCenter(vBox);

        mainMenu.setStyle("-fx-font-weight: BOLD; -fx-font-size: 13");
        playAgain.setStyle("-fx-font-weight: BOLD; -fx-font-size: 13");
        exitGame.setStyle("-fx-font-weight: BOLD; -fx-font-size: 13");
        HBox.setMargin(mainMenu, new Insets(17, 17, 17, 17));
        HBox.setMargin(playAgain, new Insets(17, 17, 17, 17));
        HBox.setMargin(exitGame, new Insets(17, 17, 17, 17));
        hBox = new HBox(mainMenu, playAgain, exitGame);
        hBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(17, 17, 17, 17));

        this.setBottom(hBox);
        lineChart.setMaxWidth(500);
        lineChart.setMaxHeight(500);
        BorderPane.setAlignment(lineChart, Pos.CENTER);
        BorderPane.setMargin(lineChart, new Insets(17, 17, 17, 17));
        this.setRight(lineChart);
    }

    private void labelStyle(Label label) {
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: 17");
    }



    // package-private Getters
    // for controls used by Presenter
    public Button getMainMenu() {
        return mainMenu;
    }

    public Button getPlayAgain() {
        return playAgain;
    }
    public Button getExitGame() {
        return exitGame;
    }
    public Label getWinOrLose() {
        return winOrLose;
    }
    public ImageView getEndGameStatus() {
        return endGameStatus;
    }
    public Label getPlayerScore() {
        return playerScore;
    }
    public Label getStat1() {
        return stat1;
    }
    public Label getStat2() {
        return stat2;
    }
    public Label getStat3() {
        return stat3;
    }
    public LineChart getLineChart() {
        return lineChart;
    }
}

