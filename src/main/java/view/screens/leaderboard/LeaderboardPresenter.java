package main.java.view.screens.leaderboard;


import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;
import main.java.view.screens.statisticsWindow.StatisticsPresenter;
import main.java.view.screens.statisticsWindow.StatisticsView;

public class LeaderboardPresenter {
    private static int usernameClicked;
    private final Quarto model;
    private final LeaderboardView view;


    public LeaderboardPresenter(Quarto model, LeaderboardView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    public static int getUsernameClicked() {
        return usernameClicked;
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

        //TODO: Cqn this be made more streamlined?
        this.view.getText1().setOnMouseClicked(event -> {
            model.setPlayerSelected(0);
            setUsernameClicked(0);
        });
        this.view.getText2().setOnMouseClicked(event -> {
            model.setPlayerSelected(1);
            setUsernameClicked(1);
        });
        this.view.getText3().setOnMouseClicked(event -> {
            model.setPlayerSelected(2);
            setUsernameClicked(2);
        });
        this.view.getText4().setOnMouseClicked(event -> {
            model.setPlayerSelected(3);
            setUsernameClicked(3);
        });
        this.view.getText5().setOnMouseClicked(event -> {
            model.setPlayerSelected(4);
            setUsernameClicked(4);
        });
    }

    private void updateView() {
        // fills the view with model data
        this.view.getText1().setText(model.getRecords(0));
        this.view.getText2().setText(model.getRecords(1));
        this.view.getText3().setText(model.getRecords(2));
        this.view.getText4().setText(model.getRecords(3));
        this.view.getText5().setText(model.getRecords(4));
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
        statisticsView.getScene().getWindow().setWidth(625);
        statisticsView.getScene().getWindow().setHeight(425);
    }

    private void setUsernameClicked(int usernameClicked){
        model.getStatistics(model.getRecordsUserId(usernameClicked));
        setStatisticsWindow();
    }
}

