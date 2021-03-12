package main.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.model.leaderBoard.Leaderboard;
import main.java.view.QuartoPresenter;
import main.java.view.QuartoView;

public class Main extends Application {

    protected boolean isRunning;
    protected int amountOfTurns;
    protected int gameTimerSeconds;
    Stage window;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

//        Quarto model = new Quarto();
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
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.connectToDb();
        Application.launch(args);
    }
}
