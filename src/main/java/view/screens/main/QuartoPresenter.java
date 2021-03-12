package main.java.view.screens.main;

import javafx.application.Platform;
import main.java.model.Quarto;
import main.java.view.screens.tutorial.TutorialPresenter;
import main.java.view.screens.tutorial.TutorialView;

public class QuartoPresenter {

    private final QuartoView view;
    private final Quarto model;

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

        //TODO: finish the buttons to go to different screens
        this.view.getStartGame().setOnAction(event -> {
            System.out.println("Start the game");
            updateView();
        });

        this.view.getLeaderboard().setOnAction(event -> {
            System.out.println("Go to the Leaderboard screen");
            updateView();
        });

        this.view.getTutorial().setOnAction(event -> {
            System.out.println("Go to the Tutorial screen");
            setTutorialView();
            updateView();
        });

        this.view.getExit().setOnAction(event -> {
            Platform.exit();
            updateView();
        });
    }

    private void updateView() {
// fills the view with model data

    }

    private void setTutorialView() {
        TutorialView tutorialView = new TutorialView();
        TutorialPresenter tutorialPresenter = new TutorialPresenter(model, tutorialView);
        view.getScene().setRoot(tutorialView);
        tutorialView.getScene().getWindow().sizeToScene();
    }
}
