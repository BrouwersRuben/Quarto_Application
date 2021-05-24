package main.java.view.screens.statisticsWindow;

import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.screens.leaderboard.LeaderboardPresenter;
import main.java.view.screens.leaderboard.LeaderboardView;

public class StatisticsPresenter {
    private final Quarto model;
    private final StatisticsView view;

    public StatisticsPresenter(Quarto model, StatisticsView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        this.view.getLeaderboard().setOnAction(event -> {
            setLeaderboardWindow();
            updateView();
        });
    }

    private void updateView() {
        view.getPlayerName().setText(model.getRecordsUserName(model.getPlayerSelected()));
        view.getPlayerScore().setText("score: " + (model.getRecordsUserScore(model.getPlayerSelected())));
        view.getStat1().setText("average time spent per round: " + (model.getAverageTime()));
        view.getStat2().setText("fastest move: " + model.getFastestMove() + " seconds");
        view.getStat3().setText("slowest move: " + model.getSlowestMove() + " seconds");
    }

    public void addWindowEventHandlers() {
        Window window = view.getScene().getWindow();
        // Add event handlers to window
    }

    private void setLeaderboardWindow() {
        LeaderboardView leaderboardView = new LeaderboardView();
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(model, leaderboardView);
        view.getScene().setRoot(leaderboardView);
        leaderboardView.getScene().getWindow().setWidth(625);
        leaderboardView.getScene().getWindow().setHeight(425);
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//        leaderboardView.getScene().getWindow().setX((screenBounds.getWidth() - GameWindowPresenter.screenWidth) / 2);
//        leaderboardView.getScene().getWindow().setY((screenBounds.getHeight() - GameWindowPresenter.screenHeight) / 2);
    }
}
