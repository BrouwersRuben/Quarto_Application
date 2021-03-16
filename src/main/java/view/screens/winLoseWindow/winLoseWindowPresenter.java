package main.java.view.screens.winLoseWindow;

import javafx.stage.Window;
import main.java.model.Quarto;

public class winLoseWindowPresenter {
    private final Quarto model;
    private final winLoseWindowView view;

    public winLoseWindowPresenter(Quarto model, winLoseWindowView view) {
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
    }

    private void updateView() {
        // fills the view with model data
    }

    public void addWindowEventHandlers() {
        Window window = view.getScene().getWindow();
        // Add event handlers to window
    }
}
