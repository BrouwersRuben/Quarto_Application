package main.java.view.screens.statisticsWindow;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.screens.gameWindow.GameWindowPresenter;
import main.java.view.screens.leaderboard.LeaderboardPresenter;
import main.java.view.screens.leaderboard.LeaderboardView;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;

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
    }

    public void addWindowEventHandlers() {
        Window window = view.getScene().getWindow();
        // Add event handlers to window
    }

    private void setLeaderboardWindow() {
        LeaderboardView leaderboardView = new LeaderboardView();
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(model, leaderboardView);
        view.getScene().setRoot(leaderboardView);
        leaderboardView.getScene().getWindow().setWidth(1152);
        leaderboardView.getScene().getWindow().setHeight(648);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        leaderboardView.getScene().getWindow().setX((screenBounds.getWidth() - GameWindowPresenter.screenWidth) / 2);
        leaderboardView.getScene().getWindow().setY((screenBounds.getHeight() - GameWindowPresenter.screenHeight) / 2);
    }
}
