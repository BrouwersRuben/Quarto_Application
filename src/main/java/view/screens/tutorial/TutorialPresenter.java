package main.java.view.screens.tutorial;

import main.java.model.Quarto;

public class TutorialPresenter {
    private Quarto model;
    private TutorialView view;

    public TutorialPresenter(Quarto model, TutorialView view) {
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
        //TODO: finish the buttons to go to different screens
        this.view.getExit().setOnAction(event -> {
            System.out.println("Go back to the main screen");
            updateView();
        });
    }

    private void updateView() {
// fills the view with model data
    }

}
