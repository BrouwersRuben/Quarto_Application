package main.java.view.screens.tutorial;

import main.java.model.Quarto;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;

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
            setMainWindow();
            updateView();
        });
    }

    private void updateView() {
// fills the view with model data
    }

    private void setMainWindow() {
        QuartoView quartoView = new QuartoView();
        QuartoPresenter quartoPresenter = new QuartoPresenter(model, quartoView);
        view.getScene().setRoot(quartoView);
        quartoView.getScene().getWindow().setWidth(625);
        quartoView.getScene().getWindow().setHeight(425);
    }

}
