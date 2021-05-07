package main.java.view.screens.gameWindow;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.Images;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;
import main.java.view.screens.pauseScreen.PauseScreenPresenter;
import main.java.view.screens.pauseScreen.PauseScreenView;
import main.java.view.screens.winLoseWindow.winLoseWindowPresenter;
import main.java.view.screens.winLoseWindow.winLoseWindowView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class GameWindowPresenter {
    private final Quarto model;
    private final GameWindowView view;
    private Timer timer;

    public GameWindowPresenter(Quarto model, GameWindowView view) {
        this.model = model;
        this.view = view;
        updateView();
        addEventHandlers();
    }

    private void addEventHandlers() {
        // Add event handlers (inner classes or
        // lambdas) to view controls.
        // In the event handlers: call model methods
        // and updateView().

        this.view.getSaveGame().setOnAction(event -> {
            // TODO: some method that updates the database with the current state of the game
            System.out.println("You have indicated that you would like to save the game");
            updateView();
        });
        this.view.getEndGame().setOnAction(event -> {
            closingAlert(event);
            updateView();
        });
        view.getWinScreen().setOnAction(event -> {
            model.setWon();

            //Temp
            model.getStatistics(model.getRecordsUserId(0));
            setWinLoseWindow();

            updateView();
        });
        view.getLoseScreen().setOnAction(event -> {
            model.setLost();

            //Temp
            model.getStatistics(model.getRecordsUserId(3));
            setWinLoseWindow();

            updateView();
        });

//        view.getPlayerTurn().setText("Their turn!"); this does nothing

        //* TODO: 1. Game Starts, 2. Whoever starts selects a piece, 3. Piece gets placed on board.
        // TODO: LOOP BETWEEN PLAYER AND COMPUTER


            view.getAvailablePieces().getChildren().forEach(piece -> {
                piece.setOnMouseClicked(mouseEvent -> {

                    view.getErrorLabel().setText("");
                    view.getChosenPiece().getChildren().add(new ImageView(Images.P0.getImage())); // empty placeholder for *selected piece*

                    Image im = new Image("media/images/" + piece.getId() + ".png");
                    view.getChosenPiece().getChildren().add(new ImageView(im));
                    view.getChosenPiece().setId(piece.getId());
                    System.out.println(piece.getId()); // for testing purposes


                    if (!model.isUnique(Integer.valueOf(piece.getId()))) {
                        view.getErrorLabel().setText("This piece is already on the board");
                    } else {
                        // Here the computer makes the move, based on the piece that was given to him.
                        model.generateValidCoordinates();
                        view.getGameBoard().add(new ImageView(im), model.getX(), model.getY());
                        view.getChosenPiece().getChildren().add(new ImageView(Images.P0.getImage()));
                        model.addToListOnBoard(Integer.valueOf(view.getChosenPiece().getId()));
                        model.setUsedTiles(parseInt(view.getChosenPiece().getId()), model.getX(), model.getY());
                        model.setCoordinatesToBoardStatus(model.getX(), model.getY());
                        model.removeRemainingPieces(Integer.valueOf(view.getChosenPiece().getId()));

                        // Computer selects the piece you have to place
                        int temporary = model.selectRandomPiece();
                        im = new Image("media/images/" +temporary+ ".png");
                        view.getChosenPiece().getChildren().add(new ImageView(im));
                        view.getChosenPiece().setId(String.valueOf(temporary));
//                        view.getPlayerTurn().setText("Your turn!");
                        model.setPlayerTurn(true);
                        updateView();

                        if (model.isGameOver()) {
                            model.setLost();
                            model.getStatistics(model.getRecordsUserId(3));
                            setWinLoseWindow();
                            System.out.println("Game is over! Thank you for playing!");
                            // TODO: here it should end game/show winLoseScreen
                        }
                    }
                });
            });


        view.getGameBoard().getChildren().forEach(item -> {
            item.setOnMouseClicked(mouseEvent -> {

                Image im = new Image("media/images/" + view.getChosenPiece().getId() + ".png");

                if (model.isUnique(Integer.valueOf(view.getChosenPiece().getId()))) {
                    view.getGameBoard().add(new ImageView(im), GridPane.getColumnIndex(item), GridPane.getRowIndex(item));
                    view.getChosenPiece().getChildren().add(new ImageView(Images.P0.getImage()));
                    model.addToListOnBoard(Integer.valueOf(view.getChosenPiece().getId()));
//                   view.getChosenPiece().setId("0"); // what does this do exactly ?
                    view.getErrorLabel().setText("");

                    // testing
                    System.out.println("\nPiece "+view.getChosenPiece().getId()+" has been set on tile "+GridPane.getColumnIndex(item)+" "+GridPane.getRowIndex(item));
                    // updates board tiles array
                    model.setUsedTiles(parseInt(view.getChosenPiece().getId()), GridPane.getColumnIndex(item), GridPane.getRowIndex(item));
                    model.setCoordinatesToBoardStatus(GridPane.getColumnIndex(item), GridPane.getRowIndex(item));
                    model.removeRemainingPieces(Integer.valueOf(view.getChosenPiece().getId()));
                    model.setPlayerTurn(false);
                    updateView();

                    if (model.isGameOver()) {
                        //TODO: Can't set winlosewindow cause database is not linked with game
                        model.setWon();

                        //Temp
                        model.getStatistics(model.getRecordsUserId(0));
                        setWinLoseWindow();
                        System.out.println("Game is over! Thank you for playing!");
                    }

                } else {
                    view.getErrorLabel().setText("This piece is already on the board");
                }
            });
        });
    }

    private void updateView() {
        // fills the view with model data
        view.getUserName().setText("Username: " + model.getUserName());
        view.getPlayerTurn().setText(model.turnIndicator());
    }

    public void addWindowEventHandlers() {
        Window window = view.getScene().getWindow();
        // Add event handlers to window

    }

//    public void setAIPiece() {
//        int piece = model.aiMove();
//
//    }

    public void normalTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        model.timerIncrement();
                        view.getTimer().setText(model.ddMinute() + ":" + model.ddSecond());
                    }
                });
            }

        });
        timer.start();
    }

//    public void timerStop(){
//        timer.stop();
//    }
//
//    public void timerStart(){
//        timer.start();
//    }

    private void setMainWindow() {
        QuartoView quartoView = new QuartoView();
        QuartoPresenter quartoPresenter = new QuartoPresenter(model, quartoView);
        view.getScene().setRoot(quartoView);
        quartoView.getScene().getWindow().setWidth(745);
        quartoView.getScene().getWindow().setHeight(475);
    }

    private void setPauseScreen() {
        PauseScreenView pauseView = new PauseScreenView();
        PauseScreenPresenter pausePresenter = new PauseScreenPresenter(model, pauseView);
        view.getScene().setRoot(pauseView);
        pauseView.getScene().getWindow().setWidth(670);
        pauseView.getScene().getWindow().setHeight(270);
    }

    private void closingAlert(Event event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("You are about to quit your game!");
        alert.setContentText("Are you sure you want to quit your game!");
        alert.setTitle("Closing game alert!");
        alert.getButtonTypes().clear();
        ButtonType no = new ButtonType("NO");
        ButtonType yes = new ButtonType("YES");
        alert.getButtonTypes().addAll(yes, no);
        alert.showAndWait();
        // TODO: This does not work (modality)
        //alert.initModality(Modality.WINDOW_MODAL);
        if (alert.getResult() == null || alert.getResult().equals(no)) {
            event.consume();
        } else {
            setMainWindow();
        }
    }

    private void setWinLoseWindow() {
        winLoseWindowView winLoseView = new winLoseWindowView();
        winLoseWindowPresenter winLosePresenter = new winLoseWindowPresenter(model, winLoseView);
        view.getScene().setRoot(winLoseView);
        winLoseView.getScene().getWindow().setWidth(1152);
        winLoseView.getScene().getWindow().setHeight(648);
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//        winLoseView.getScene().getWindow().setX((screenBounds.getWidth() - GameWindowPresenter.screenWidth) / 2);
//        winLoseView.getScene().getWindow().setY((screenBounds.getHeight() - GameWindowPresenter.screenHeight) / 2);
    }
}
