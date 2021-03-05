package main.java.View;

import main.java.Model.Quarto;

public class QuartoPresenter {
    private Quarto model;
    private QuartoView view;
    public QuartoPresenter(Quarto model, QuartoView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
// Adds event handlers (inner classes or lambdas)
// to view controls
// Event handlers: call model methods and
// update the view.
        this.view.getExit().setOnAction(event -> {
            System.exit(69);
            updateView();
        });
    }

    private void updateView() {
// fills the view with model data
    }

}
