package main.java.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.view.screens.tutorial.TutorialView;

public class QuartoPresenter {

//    private Quarto model;
    private QuartoView view;

    public QuartoPresenter(/*Quarto model, */QuartoView view) {
//        this.model = model;
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
            changeScreen(new Scene(new TutorialView()), "Tutorial", 625, 525);
            updateView();
        });

        this.view.getExit().setOnAction(event -> {
            Platform.exit();
            updateView();
        });
    }

    private EventHandler<ActionEvent> changeScreen(Scene scene, String windowTitle, int width, int height) {
        return (event) -> {
            Stage stage = new Stage();
            stage.initOwner(view.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setWidth(width);
            stage.setHeight(height);
            stage.setX(view.getScene().getWindow().getX() + 100);
            stage.setY(view.getScene().getWindow().getY() + 100);
            stage.setTitle(windowTitle);
            stage.show();
            //stage.sizeToScene();
        };
    }
    private void updateView() {
// fills the view with model data

    }
}
