package main.java.view.screens.winLoseWindow;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import main.java.model.dataBase.Leaderboard;
import main.java.view.screens.leaderboard.LeaderboardPresenter;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Collections;

public class winLoseWindowView extends GridPane {
    // private Node attributes (controls)

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
    private final boolean testTrue=true;
    private final boolean testFalse=false;

    public winLoseWindowView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        // create and configure controls
        // button = new Button("...")
        // label = new Label("...")
        winOrLose = updateWinOrLose(false);
        endGameStatus = updateEndGameStatus(false);
        playerScore = new Label("score: "+ Leaderboard.records[0].getScore());
        stat1 = new Label("average time spent per round: "+(Leaderboard.averageTime));
        stat2 = new Label("fastest move: "+ Collections.min(Leaderboard.turnStats)+ "seconds");
        stat3 = new Label("slowest move: "+ Collections.max(Leaderboard.turnStats)+" seconds");

        lineChart = new LineChart<>(new CategoryAxis(), new NumberAxis());
        lineChart.setTitle("Time spent per move");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("this game");
        for (int i=0; i <Leaderboard.turnStats.size(); i++) {
            String turn = ""+(i+1);
            series1.getData().add(new XYChart.Data<>(turn, Leaderboard.turnStats.get(i)));
        }
        lineChart.getData().addAll(series1);

        mainMenu = new Button("Return to the main menu");
        playAgain = new Button("Play again");
        exitGame = new Button("Exit");
    }

    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, …
        setAlignment(Pos.CENTER);
        addLabel(winOrLose, 0,0);
        addImageView(endGameStatus,0,1);
        endGameStatus.setFitHeight(100);
        endGameStatus.setFitWidth(100);
        addLabel(playerScore,0,2);
        addLabel(stat1, 0,3);
        addLabel(stat2, 0,4);
        addLabel(stat3, 0,5);
        addLineChart(lineChart,0,6);
        addButton(mainMenu, 1, 7, 3, 2);
        addButton(playAgain, 0,7,3,2);
        addButton(exitGame, 2,7,3,2);
//        getColumnConstraints().add(new ColumnConstraints(100)); // column 0 is 100 wide
//        getColumnConstraints().add(new ColumnConstraints(200));

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            this.getRowConstraints().add(rowConst);
        }
    }

    private void addButton(Button button, int i1, int i2, int i3, int i4) {
        this.add(button, i1, i2, i3, i4);
        button.setStyle("-fx-font-weight: BOLD");
    }

    private void addLineChart(LineChart lineChart, int i1, int i2) {
        this.add(lineChart, i1, i2);
    }

    private void addLabel(Label label, int i1, int i2) {
        this.add(label, i1, i2);
        label.setAlignment(Pos.CENTER);
        this.setHalignment(label, HPos.CENTER);
        label.setStyle("-fx-font-size: 24");
    }

    private void addImageView(ImageView image, int i1, int i2) {
        this.add(image, i1, i2);
    }

    private Label updateWinOrLose(boolean hasWon) {
        if (hasWon) {
            return new Label("VICTORY");
        } else {
            return new Label("DEFEAT");
        }
    }

    private ImageView updateEndGameStatus(boolean hasWon) {
        if (hasWon) {
            return new ImageView(Paths.get("resources/media/images/victory.png").toUri().toString());
        } else {
            return new ImageView(Paths.get("resources/media/images/defeat.png").toUri().toString());
        }
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
}

