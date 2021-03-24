package main.java.view.screens.gameWindow;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;
import main.java.view.screens.winLoseWindow.winLoseWindowPresenter;
import main.java.view.screens.winLoseWindow.winLoseWindowView;

import java.awt.event.MouseEvent;

public class GameWindowPresenter {
    private final Quarto model;
    private final GameWindowView view;
    public static double screenWidth = 1152;
    public static double screenHeight = 648;

    public GameWindowPresenter(Quarto model, GameWindowView view) {
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
        this.view.getPauseGame().setOnAction(event -> {
            // setPauseWindow(); TODO: need a pause screen for this I assume +something that would stop the view from being updated (for the timer etc.)
            updateView();
        });
        this.view.getSaveGame().setOnAction(event -> {
            // TODO: some method that updates the database with the current state of the game
            //leaderboard.getStatistics(Leaderboard.records[0].getId()); // Temporary (to load the values)
            setWinLoseWindow();
            updateView();
        });
        this.view.getEndGame().setOnAction(event -> {
            setMainWindow();
            updateView();
        });

//        this.view.getPieces().Mous(MouseEvent.MOUSE_CLICKED, event -> {
//            System.out.println("Tile pressed ");
//            event.consume();
//        });
    }

    private void updateView() {
        // fills the view with model data
    }

    public void addWindowEventHandlers() {
        Window window = view.getScene().getWindow();
        // Add event handlers to window
    }

    public static double getScreenWidth() {
        return screenWidth;
    }

    public static double getScreenHeight() {
        return screenHeight;
    }

    private void setMainWindow() {
        QuartoView quartoView = new QuartoView();
        QuartoPresenter quartoPresenter = new QuartoPresenter(model, quartoView);
        view.getScene().setRoot(quartoView);
        quartoView.getScene().getWindow().setWidth(625);
        quartoView.getScene().getWindow().setHeight(425);
    }

    private void setWinLoseWindow() {
        winLoseWindowView winLoseView = new winLoseWindowView();
        winLoseWindowPresenter winLosePresenter = new winLoseWindowPresenter(model, winLoseView);
        view.getScene().setRoot(winLoseView);
        winLoseView.getScene().getWindow().setWidth(1152);
        winLoseView.getScene().getWindow().setHeight(648);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        winLoseView.getScene().getWindow().setX((screenBounds.getWidth() - GameWindowPresenter.screenWidth) / 2);
        winLoseView.getScene().getWindow().setY((screenBounds.getHeight() - GameWindowPresenter.screenHeight) / 2);
    }
}
