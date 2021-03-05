import javafx.application.Application;
import javafx.stage.Stage;
import main.java.Model.Quarto;
import javafx.scene.Scene;
import main.java.View.QuartoPresenter;
import main.java.View.QuartoView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Quarto model = new Quarto();
        QuartoView view = new QuartoView();
        new QuartoPresenter(model, view);
        primaryStage.setScene(new Scene(view));

        primaryStage.setTitle("Quarto");
        //TODO: Set correct size!
        primaryStage.setWidth(625);
        primaryStage.setHeight(425);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    protected boolean isRunning;
    protected int amountOfTurns;
    protected int gameTimer;

    public static void main(String[] args) {
        Application.launch(args);
    }


}


/*
Start developing the game (implementation of the domain model),
focus on game logic - a player vs another player or vs the ‘computer’
who makes randomly generated modes - (main.java.Model part of MVP)
 */
