package main.java.view.screens.leaderboard;


import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.model.dataBase.Leaderboard;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;
import main.java.view.screens.statisticsWindow.StatisticsPresenter;
import main.java.view.screens.statisticsWindow.StatisticsView;

public class LeaderboardPresenter {
    private static int usernameClicked;
    Leaderboard leaderboard = new Leaderboard();
    private final Quarto model;
    private final LeaderboardView view;


    public LeaderboardPresenter(Quarto model, LeaderboardView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        // Add event handlers (inner classes or
        // lambdas) to view controls.
        // In the event handlers: call model methods
        // and updateView().

        this.view.getExit().setOnAction(event -> {
            setMainWindow();
            updateView();
        });
        this.view.getText1().setOnMouseClicked(event -> {
            usernameClicked = 0;
            leaderboard.getStatistics(Leaderboard.records[usernameClicked].getId()); // give id from the selected one
            setStatisticsWindow();
        });
        this.view.getText2().setOnMouseClicked(event -> {
            usernameClicked = 1;
            leaderboard.getStatistics(Leaderboard.records[usernameClicked].getId());
            setStatisticsWindow();
        });
        this.view.getText3().setOnMouseClicked(event -> {
            usernameClicked = 2;
            leaderboard.getStatistics(Leaderboard.records[usernameClicked].getId());
            setStatisticsWindow();
        });
        this.view.getText4().setOnMouseClicked(event -> {
            usernameClicked = 3;
            leaderboard.getStatistics(Leaderboard.records[usernameClicked].getId());
            setStatisticsWindow();
        });
        this.view.getText5().setOnMouseClicked(event -> {
            usernameClicked = 4;
            leaderboard.getStatistics(Leaderboard.records[usernameClicked].getId());
            setStatisticsWindow();
        });
    }

    private void updateView() {
        // fills the view with model data
        this.view.getText1().setText("1. "+Leaderboard.records[0].getUsername()+" - "+Leaderboard.records[0].getScore());
        this.view.getText2().setText("2. "+Leaderboard.records[1].getUsername()+" - "+Leaderboard.records[1].getScore());
        this.view.getText3().setText("3. "+Leaderboard.records[2].getUsername()+" - "+Leaderboard.records[2].getScore());
        this.view.getText4().setText("4. "+Leaderboard.records[3].getUsername()+" - "+Leaderboard.records[3].getScore());
        this.view.getText5().setText("5. "+Leaderboard.records[4].getUsername()+" - "+Leaderboard.records[4].getScore());
    }

    public void addWindowEventHandlers() {
        Window window = view.getScene().getWindow();
        // Add event handlers to window
    }

    private void setMainWindow() {
        QuartoView quartoView = new QuartoView();
        QuartoPresenter quartoPresenter = new QuartoPresenter(model, quartoView);
        view.getScene().setRoot(quartoView);
        quartoView.getScene().getWindow().setWidth(625);
        quartoView.getScene().getWindow().setHeight(425);
    }

    private void setStatisticsWindow() {
        StatisticsView statisticsView = new StatisticsView();
        StatisticsPresenter statisticsPresenter = new StatisticsPresenter(model, statisticsView);
        view.getScene().setRoot(statisticsView);
        statisticsView.getScene().getWindow().setWidth(1152);
        statisticsView.getScene().getWindow().setHeight(648);
    }

    public static int getUsernameClicked() {
        return usernameClicked;
    }
}

