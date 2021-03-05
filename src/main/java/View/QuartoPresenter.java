package main.java.View;

import javafx.stage.Stage;
import main.java.Main;
import main.java.Model.Quarto;

public class QuartoPresenter {

    Main main = new Main();
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
            updateView();
        });

        this.view.getExit().setOnAction(event -> {
            System.exit(69);
            updateView();
        });
    }

    private void updateView() {
// fills the view with model data
    }

}
