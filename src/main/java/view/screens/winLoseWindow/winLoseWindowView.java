package main.java.view.screens.winLoseWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class winLoseWindowView extends BorderPane {
    private final double numRows = 7;
    // private Node attributes (controls)
    private VBox leftVBox;
    private VBox overallStats;
    private VBox rightVBox;
    private HBox centerHBox;

    private Label playerName;

    private HBox hBox;
    private Label winOrLose;
    private ImageView endGameStatus;
    private Label playerScore;
    private Label stat1;
    private Label stat2;
    private Label stat3;
    private LineChart lineChart;
    private BarChart barChart;
    private BarChart barChart2; // should be barchart
    private Button mainMenu;
    private Button playAgain;
    private Button returnToLeaderboard;
    private Button exitGame;

    public winLoseWindowView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        // create and configure controls
        // button = new Button("...")
        // label = new Label("...")
        winOrLose = new Label();
        playerName = new Label("Statistics for player");
        endGameStatus = new ImageView();
        playerScore = new Label();
        stat1 = new Label();
        stat2 = new Label();
        stat3 = new Label();

        lineChart = new LineChart<>(new CategoryAxis(), new NumberAxis());
        lineChart.setTitle("Time spent per move");

        barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        barChart.setTitle("Score");

        barChart2 = new BarChart<>(new CategoryAxis(), new NumberAxis());
        barChart2.setTitle("Average move time");

        mainMenu = new Button("Return to the main menu");
        playAgain = new Button("Play again");
        exitGame = new Button("Exit");
        returnToLeaderboard = new Button("Return to leaderboard");
    }

    // TODO: TAKE CARE OF THIS, I SPAMMED A QUICK 5 MINUTE WINDOW WITHOUT CHECKING WHAT THESE THINGS DO
    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, …

        labelStyle(playerScore);
        labelStyle(stat1);
        labelStyle(stat2);
        labelStyle(stat3);


        endGameStatus.setFitHeight(125);
        endGameStatus.setFitWidth(125);
        winOrLose.setStyle("-fx-font-size: 35; fx-font-weight: BOLD");
        overallStats = new VBox(winOrLose, endGameStatus, playerScore, stat1, stat2, stat3);
        overallStats.setAlignment(Pos.CENTER);


        mainMenu.setStyle("-fx-font-weight: BOLD; -fx-font-size: 13");
        playAgain.setStyle("-fx-font-weight: BOLD; -fx-font-size: 13");
        exitGame.setStyle("-fx-font-weight: BOLD; -fx-font-size: 13");
        returnToLeaderboard.setStyle("-fx-font-weight: BOLD; -fx-font-size: 13");
        HBox.setMargin(mainMenu, new Insets(17, 17, 17, 17));
        HBox.setMargin(playAgain, new Insets(17, 17, 17, 17));
        HBox.setMargin(exitGame, new Insets(17, 17, 17, 17));
        HBox.setMargin(returnToLeaderboard, new Insets(17, 17, 17, 17));
        hBox = new HBox(mainMenu, returnToLeaderboard, playAgain, exitGame);
        hBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(17, 17, 17, 17));
        hBox.setMinHeight(50);
        this.setBottom(hBox);



        BorderPane.setAlignment(lineChart, Pos.CENTER);
        BorderPane.setMargin(lineChart, new Insets(17, 17, 17, 17));
        this.setRight(lineChart);

        playerName.setStyle("-fx-font-size: 35; -fx-font-weight: BOLD");
        playerName.setAlignment(Pos.CENTER);
        playerName.setMinHeight(100);
        BorderPane.setAlignment(playerName, Pos.CENTER);
        this.setTop(playerName);

        leftVBox = new VBox(overallStats, lineChart);
        leftVBox.setMinWidth(800);
        leftVBox.setAlignment(Pos.CENTER);
        rightVBox = new VBox(barChart, barChart2);
        rightVBox.setMinWidth(800);
        rightVBox.setAlignment(Pos.CENTER);

        centerHBox = new HBox(leftVBox, rightVBox);
        HBox.setMargin(centerHBox, new Insets(17, 17, 17, 17));

        centerHBox.maxHeight(750);
        this.setCenter(centerHBox);

    }

    private void labelStyle(Label label) {
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: 17");
        label.setPadding(new Insets(6, 6, 6, 6));
    }

    // package-private Getters
    // for controls used by Presenter
    Button getMainMenu() {
        return mainMenu;
    }

    Button getReturnToLeaderboard() { return returnToLeaderboard; }

    Button getPlayAgain() {
        return playAgain;
    }

    Button getExitGame() {
        return exitGame;
    }

    Label getWinOrLose() {
        return winOrLose;
    }

    ImageView getEndGameStatus() {
        return endGameStatus;
    }

    Label getPlayerScore() { return playerScore; }

    Label getStat1() { return stat1; }

    Label getStat2() {
        return stat2;
    }

    Label getStat3() {
        return stat3;
    }

    public Label getPlayerName() { return playerName; }

    LineChart getLineChart() {
        return lineChart;
    }
    BarChart getBarChart() {
        return barChart;
    }
    BarChart getBarChart2() {
        return barChart2;
    }


}

