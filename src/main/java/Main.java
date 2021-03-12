package main.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        QuartoView view = new QuartoView();
        new QuartoPresenter(view);
        Scene scene = new Scene(view);

        window.setWidth(625);
        window.setHeight(425);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
