package main.java;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.model.Quarto;
import main.java.model.dataBase.Leaderboard;
import main.java.model.dataBase.saveAndLoad;
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
