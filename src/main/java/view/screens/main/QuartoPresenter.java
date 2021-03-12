package main.java.view.screens.main;

import javafx.application.Platform;
import main.java.model.Quarto;
import main.java.model.leaderBoard.Leaderboard;
import main.java.view.screens.leaderboard.LeaderboardPresenter;
import main.java.view.screens.leaderboard.LeaderboardView;
import main.java.view.screens.tutorial.TutorialPresenter;
import main.java.view.screens.tutorial.TutorialView;

public class QuartoPresenter {

    private final QuartoView view;
    private final Quarto model;
    // TODO: closeDb()
    // Is it call the leaderboard class to have the closeDb() here
    private Leaderboard lb = new Leaderboard();

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
            setTutorialView();
            updateView();
        });

        this.view.getExit().setOnAction(event -> {
            Platform.exit();
            lb.closeDb();
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
    private void setLeaderboard() {
        LeaderboardView leaderboardView = new LeaderboardView();
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(model, leaderboardView);
        view.getScene().setRoot(leaderboardView);
        leaderboardView.getScene().getWindow().sizeToScene();
    }
}
