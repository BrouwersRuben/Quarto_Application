package main.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.model.Quarto;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;

public class Main extends Application {

    protected boolean isRunning;
    protected int amountOfTurns;
    protected int gameTimerSeconds;
    Stage window;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

//        window.setResizable(false);
//        Fucks in Linux

        window.setTitle("Quarto");
        final QuartoView view = new QuartoView();
        final Quarto model = new Quarto();
        new QuartoPresenter(model, view);
        final Scene scene = new Scene(view);

        window.setWidth(625);
        window.setHeight(425);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
