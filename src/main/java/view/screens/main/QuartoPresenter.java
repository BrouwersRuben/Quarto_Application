package main.java.view.screens.main;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.java.model.Quarto;
import main.java.model.leaderBoard.Leaderboard;
import main.java.view.screens.leaderboard.LeaderboardPresenter;
import main.java.view.screens.leaderboard.LeaderboardView;
import main.java.view.screens.tutorial.TutorialPresenter;
import main.java.view.screens.tutorial.TutorialView;
import main.java.view.screens.userNamePrologue.UserNameProloguePresenter;
import main.java.view.screens.userNamePrologue.UserNamePrologueView;

public class QuartoPresenter {

    private final QuartoView view;
    private final Quarto model;
    // Is it call the leaderboard class to have the closeDb() here
    private final Leaderboard lb = new Leaderboard();

    public QuartoPresenter(Quarto model, QuartoView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    public void addWindowEventHandlers() {
        // Add event handlers to window
        view.getScene().getWindow().setOnCloseRequest(event -> {
            closingAlert(event);
        });
    }

    private void addEventHandlers() {
// Adds event handlers (inner classes or lambdas)
// to view controls
// Event handlers: call model methods and
// update the view.

        this.view.getStartGame().setOnAction(event -> {
            setUserNamePrologue();
            updateView();
        });

        this.view.getLeaderboard().setOnAction(event -> {
            setLeaderboard();
            updateView();
        });

        this.view.getTutorial().setOnAction(event -> {
            setTutorialView();
            updateView();
        });

        this.view.getExit().setOnAction(event -> {
            closingAlert(event);
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
        leaderboardView.getScene().getWindow();
    }

    private void setUserNamePrologue() {
        UserNamePrologueView userNamePrologueView = new UserNamePrologueView();
        UserNameProloguePresenter userNameProloguePresenter = new UserNameProloguePresenter(model, userNamePrologueView);
        view.getScene().setRoot(userNamePrologueView);
        userNamePrologueView.getScene().getWindow().sizeToScene();
    }

    private void closingAlert(Event event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("You are about to quit the game!");
        alert.setContentText("Are you sure you want to quit the game!");
        alert.setTitle("Closing alert!");
        alert.getButtonTypes().clear();
        ButtonType no = new ButtonType("NO");
        ButtonType yes = new ButtonType("YES");
        alert.getButtonTypes().addAll(yes, no);
        alert.showAndWait();
        if (alert.getResult() == null || alert.getResult().equals(no)) {
            event.consume();
        } else {
            lb.closeDb();
            Platform.exit();
        }
    }
}
