package main.java.view.screens.gameWindow;

import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;

import java.awt.event.MouseEvent;

public class GameWindowPresenter {
    private final Quarto model;
    private final GameWindowView view;
    public static double screenWidth = 1280;
    public static double screenHeight = 720;

    public GameWindowPresenter(Quarto model, GameWindowView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void setMainWindow() {
        QuartoView quartoView = new QuartoView();
        QuartoPresenter quartoPresenter = new QuartoPresenter(model, quartoView);
        view.getScene().setRoot(quartoView);
        quartoView.getScene().getWindow().setWidth(625);
        quartoView.getScene().getWindow().setHeight(425);
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
}
