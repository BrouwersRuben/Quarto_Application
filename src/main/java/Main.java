package main.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.model.Quarto;
import main.java.model.leaderBoard.Leaderboard;
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
        final Quarto model = new Quarto();
        final QuartoView view = new QuartoView();
        final QuartoPresenter presenter = new QuartoPresenter(model, view);
        // TODO: annot invoke "javafx.scene.Scene.getWindow()" because the return value of "main.java.view.screens.main.QuartoView.getScene()" is null
        final Scene scene = new Scene(view);

        window.setWidth(625);
        window.setHeight(425);

        window.setScene(scene);
        presenter.addWindowEventHandlers();
        window.show();
    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.connectToDb();
        Application.launch(args);
    }
}
