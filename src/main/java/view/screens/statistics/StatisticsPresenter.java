package main.java.view.screens.statistics;


import javafx.stage.Window;
import main.java.model.Quarto;

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

