package main.java.view.screens.gameWindow;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Window;
import javafx.util.Duration;
import main.java.model.Quarto;
import main.java.model.board.Board;
import main.java.model.players.Player;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;

import java.util.concurrent.TimeUnit;

public class GameWindowPresenter {
    private final Quarto model;
    private final GameWindowView view;

    public GameWindowPresenter(Quarto model, GameWindowView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void setMainWindow() {
        QuartoView quartoView = new QuartoView();
        QuartoPresenter quartoPresenter = new QuartoPresenter(model, quartoView);
        view.getScene().setRoot(quartoView);
        quartoView.getScene().getWindow().setWidth(745);
        quartoView.getScene().getWindow().setHeight(475);
    }

    private void addEventHandlers() {
        // Add event handlers (inner classes or
        // lambdas) to view controls.
        // In the event handlers: call model methods
        // and updateView().
        this.view.getPauseGame().setOnAction(event -> {
            // setPauseWindow(); TODO: need a pause screen for this I assume +something that would stop the view from being updated (for the timer etc.)
            updateView();
        });
        this.view.getSaveGame().setOnAction(event -> {
            // TODO: some method that updates the database with the current state of the game
            updateView();
        });
        this.view.getEndGame().setOnAction(event -> {
            setMainWindow();
            updateView();
        });
    }

    private void updateView() {
        // fills the view with model data
    }

    public void addWindowEventHandlers() {
        Window window = view.getScene().getWindow();
        // Add event handlers to window
    }

    /* TODO: Timer status bar
    public class Timer {
        protected int gameTimerSeconds = 300 *//*5 min*//*;
        public boolean timeIsOver = false;
        public int playerTurn = 0;
        private Board board;
        private Player player;
        private Quarto quarto;

        public Timer(Board _board) {
            board = _board;
        }

        public Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (playerTurn == 1 && !timeIsOver && !player.hasQuarto)
                {
                    gameTimerSeconds -= 1;
                model.getStatusBar().whitePlayerTimer.setText("White timer: " + TimeUnit.SECONDS.toMinutes(gameTimerSeconds) + ":" + (gameTimerSeconds % 60));
                }
                if (!timeIsOver && (gameTimerSeconds == 0 || gameTimerSeconds == 0))
                {
                    quarto.gameOver();
                    timeIsOver = true;
                }
            }
        }));
    }

    public void reset() {
        gameTimerSeconds = 300;
        //all the variables that have to be resetted after each turn.
    }*/
}
