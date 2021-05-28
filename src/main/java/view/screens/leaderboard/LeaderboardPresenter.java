package main.java.view.screens.leaderboard;


import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;
import main.java.view.screens.winLoseWindow.winLoseWindowPresenter;
import main.java.view.screens.winLoseWindow.winLoseWindowView;

public class LeaderboardPresenter {
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

        //TODO: Can this be made more streamlined?
        this.view.getText1().setOnMouseClicked(event -> {
            model.setPlayerSelected(0);
            model.getSpecificPlayerStatistics();
            setWinLoseWindow();
        });
        this.view.getText2().setOnMouseClicked(event -> {
            model.setPlayerSelected(1);
            model.getSpecificPlayerStatistics();
            setWinLoseWindow();
        });
        this.view.getText3().setOnMouseClicked(event -> {
            model.setPlayerSelected(2);
            model.getSpecificPlayerStatistics();
            setWinLoseWindow();
        });
        this.view.getText4().setOnMouseClicked(event -> {
            model.setPlayerSelected(3);
            model.getSpecificPlayerStatistics();
            setWinLoseWindow();
        });
        this.view.getText5().setOnMouseClicked(event -> {
            model.setPlayerSelected(4);
            model.getSpecificPlayerStatistics();
            setWinLoseWindow();
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
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        quartoView.getScene().getWindow().setX((screenBounds.getWidth() - 625) / 2);
        quartoView.getScene().getWindow().setY((screenBounds.getHeight() - 425) / 2);
    }

    private void setWinLoseWindow() {
        winLoseWindowView winLoseView = new winLoseWindowView();
        winLoseWindowPresenter winLosePresenter = new winLoseWindowPresenter(model, winLoseView);
        view.getScene().setRoot(winLoseView);
        winLoseView.getScene().getWindow().setWidth(1600);
        winLoseView.getScene().getWindow().setHeight(900);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        winLoseView.getScene().getWindow().setX((screenBounds.getWidth() - 1600) / 2);
        winLoseView.getScene().getWindow().setY((screenBounds.getHeight() - 900) / 2);
    }
}

