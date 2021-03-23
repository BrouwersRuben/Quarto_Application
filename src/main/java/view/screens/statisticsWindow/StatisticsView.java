package main.java.view.screens.statisticsWindow;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import main.java.model.dataBase.Leaderboard;
import main.java.model.dataBase.Statistics;
import main.java.model.dataBase.Record;
import main.java.view.screens.leaderboard.LeaderboardPresenter;

import java.util.Collections;

public class StatisticsView extends GridPane {
    // private Node attributes (controls)

    private Label playerName;
    private Label playerScore;
    private Label stat1;
    private Label stat2;
    private Label stat3;
    private Button leaderboard;
    private final double numRows = 6;

    public StatisticsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        // create and configure controls
        // button = new Button("...")
        // label = new Label("...")
        playerName = new Label(Leaderboard.records[LeaderboardPresenter.getUsernameClicked()].getUsername());
        playerScore = new Label("score: "+(Leaderboard.records[LeaderboardPresenter.getUsernameClicked()].getScore()));
        stat1 = new Label("average time spent per round: "+(Leaderboard.averageTime));
        stat2 = new Label("fastest move: "+ Collections.min(Leaderboard.turnStats)+" seconds");
        stat3 = new Label("slowest move: "+ Collections.max(Leaderboard.turnStats)+" seconds");
        leaderboard = new Button("Return to leaderboard");
    }

    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, …
        addLabel(playerName,0,0); // TODO: fix this redundant initialization, when I'm overriding the style below
        playerName.setStyle("-fx-font-size:48; -fx-font-weight: bold");
        addLabel(playerScore,0,1);
        addLabel(stat1, 0,2);
        addLabel(stat2, 0,3);
        addLabel(stat3, 0,4);
        addButton(leaderboard, 0, 5, 3, 2);
//        getColumnConstraints().add(new ColumnConstraints(100)); // column 0 is 100 wide
//        getColumnConstraints().add(new ColumnConstraints(200));

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            this.getRowConstraints().add(rowConst);
        }

//        this.setGridLinesVisible(true);
        setAlignment(Pos.CENTER);
    }

    private void addButton(Button button, int i1, int i2, int i3, int i4) {
        this.add(button, i1, i2, i3, i4);
        button.setPrefHeight(30);
        button.setPrefWidth(400);
        button.setAlignment(Pos.CENTER);
        this.setHalignment(button, HPos.CENTER);
        button.setStyle("-fx-font-weight: BOLD");
    }

    private void addLabel(Label label, int i1, int i2) {
        this.add(label, i1, i2);
        label.setAlignment(Pos.CENTER);
        this.setHalignment(label, HPos.CENTER);
        label.setStyle("-fx-font-size: 24");
    }

    // package-private Getters
    // for controls used by Presenter
    public Button getLeaderboard() {
        return leaderboard;
    }


}

