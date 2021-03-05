package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.Model.Quarto;
import javafx.scene.Scene;
import main.java.View.QuartoPresenter;
import main.java.View.QuartoView;
import main.java.View.Screens.Tutorial.TutorialPresenter;
import main.java.View.Screens.Tutorial.TutorialView;

public class Main extends Application {

    protected boolean isRunning;
    protected int amountOfTurns;
    protected int gameTimer;
    Scene startScreen, tutorialScreen;
    Stage window;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        Quarto model = new Quarto();
        window.setTitle("Quarto");
        window.setResizable(false);

        QuartoView view = new QuartoView();
        new QuartoPresenter(model, view);

        TutorialView tutorial = new TutorialView();
        new TutorialPresenter(model, tutorial);

        startScreen = new Scene(view, 625, 425);
        tutorialScreen = new Scene(tutorial, 625, 525);

        window.setScene(startScreen);
        window.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
