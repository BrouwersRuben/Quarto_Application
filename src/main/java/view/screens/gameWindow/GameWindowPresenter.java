package main.java.view.screens.gameWindow;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;

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
        view.getQuarto().setOnAction(event -> {
            System.out.println("You have indicated that you saw a quarto");
            // TODO: Call the hasQuarto method to see if the quarto was right.
        });


        view.getPlayerTurn().setText("Your turn!\nTo pick a piece");
        view.getAvailablePieces().getChildren().forEach(item -> {
            item.setOnMouseClicked(mouseEvent -> {
                Image imBlank = new Image("Pieces/0.png");
                view.getChosenPiece().getChildren().add(new ImageView(imBlank));

                Image im = new Image("Pieces/" + item.getId() + ".png");
                view.getChosenPiece().getChildren().add(new ImageView(im));
                view.getChosenPiece().setId(item.getId());

                // TODO: Switch users
                //model.setUser1(false);

                // TODO: Add the piece to a used pieces to avoid doubles
            });
        });

        view.getPlayerTurn().setText("Their turn!");

        view.getGameBoard().getChildren().forEach(item -> {
            item.setOnMouseClicked(mouseEvent -> {


                Image im = new Image("Pieces/" + view.getChosenPiece().getId() + ".png");
                view.getGameBoard().add(new ImageView(im), GridPane.getColumnIndex(item), GridPane.getRowIndex(item));

                Image imBlank = new Image("Pieces/0.png");
                view.getChosenPiece().getChildren().add(new ImageView(imBlank));

            });
        });

    }

    private void updateView() {
        // fills the view with model data
    }

    public void addWindowEventHandlers() {
        Window window = view.getScene().getWindow();
        // Add event handlers to window
    }

    /* TODO: Timer status bar --> QUARTOmodel
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
