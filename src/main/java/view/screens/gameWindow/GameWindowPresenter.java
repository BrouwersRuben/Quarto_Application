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
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        // Add event handlers (inner classes or
        // lambdas) to view controls.
        // In the event handlers: call model methods
        // and updateView().

        this.view.getPauseGame().setOnAction(event -> {
            // setPauseWindow(); TODO: timer restarts instead of continues
            //timerStart();
            setPauseScreen();
            updateView();
        });
        this.view.getSaveGame().setOnAction(event -> {
            // TODO: some method that updates the database with the current state of the game
            System.out.println("You have indicated that you would like to save the game");
            updateView();
        });
        this.view.getEndGame().setOnAction(event -> {
            closingAlert(event);
            updateView();
        });
        view.getQuarto().setOnAction(event -> {
            System.out.println("You have indicated that you saw a quarto");
            //Hasquarto has to be called here, to check if what the user saw was correct
            model.hasQuarto();
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

        view.getPlayerTurn().setText("Your turn!\nTo pick a piece");

        //* TODO: 1. Game Starts, 2. Whoever starts selects a piece, 3. Piece gets placed on board.


        while (model.retrieveRemainingPieces().size()!=0) {
            view.getAvailablePieces().getChildren().forEach(piece -> {
                piece.setOnMouseClicked(mouseEvent -> {

                    view.getErrorLabel().setText("");
                    view.getChosenPiece().getChildren().add(new ImageView(Images.P0.getImage()));

                    Image im = new Image("media/images/" + piece.getId() + ".png");
                    view.getChosenPiece().getChildren().add(new ImageView(im));
                    view.getChosenPiece().setId(piece.getId());
                    System.out.println(piece.getId()); // for testing purposes


                    if (!model.isUnique(Integer.valueOf(piece.getId()))) {
                        view.getErrorLabel().setText("This piece is already on the board");
                        System.out.println("iegāja pie valid mouse false");
                    } else {
                        System.out.println("iegāja pie validmouse true");

                        // Here the computer makes the move, based on the piece that was given to him.
                        model.generateValidCoordinates();
                        view.getGameBoard().add(new ImageView(im), model.getX(), model.getY());
                        view.getChosenPiece().getChildren().add(new ImageView(Images.P0.getImage()));
                        model.addToListOnBoard(Integer.valueOf(view.getChosenPiece().getId()));
                        model.setUsedTiles(parseInt(view.getChosenPiece().getId()), model.getX(), model.getY());
                        model.removeRemainingPieces(Integer.valueOf(view.getChosenPiece().getId()));

                    }
                });

//            if (model.isValidMouseRelease()) {
//                System.out.println("iegāja pie release ar vērtību: "+model.isValidMouseRelease());
//                piece.setOnMouseReleased(e -> {
//                    System.out.println("Mouse has been released");
//                });
//            }

                view.getPlayerTurn().setText("Their turn!");

            });

        }

        view.getGameBoard().getChildren().forEach(item -> {
            item.setOnMouseClicked(mouseEvent -> {

                Image im = new Image("media/images/" + view.getChosenPiece().getId() + ".png");

                System.out.println(view.getChosenPiece().getId()+" ŠEIT");

                if (model.isUnique(Integer.valueOf(view.getChosenPiece().getId()))) {
                    view.getGameBoard().add(new ImageView(im), GridPane.getColumnIndex(item), GridPane.getRowIndex(item));
                    view.getChosenPiece().getChildren().add(new ImageView(Images.P0.getImage()));
                    model.addToListOnBoard(Integer.valueOf(view.getChosenPiece().getId()));
//                   view.getChosenPiece().setId("0"); // what does this do exactly ?
                    view.getErrorLabel().setText("");

                    // testing
                    System.out.println("piece "+view.getChosenPiece().getId()+" has been set on tile "+GridPane.getRowIndex(item)+" "+GridPane.getColumnIndex(item));
                    // updates board tiles array
                    model.setUsedTiles(parseInt(view.getChosenPiece().getId()), GridPane.getRowIndex(item), GridPane.getColumnIndex(item));
                    model.removeRemainingPieces(Integer.valueOf(view.getChosenPiece().getId()));


                    System.out.println("\nAI is now making the move.");


//                    System.out.println("\nAI has selected a piece for you to play.");
//                    Image ai = new Image("media/images/"+model.selectRandomPiece()+".png");
//                    view.getChosenPiece().getChildren().add(new ImageView(ai));


                } else {
                    view.getErrorLabel().setText("This piece is already on the board");
                }

                view.getPlayerTurn().setText("Your turn!");
//                model.aiMove();
            });
        });
        setWinLoseWindow();
    }

    private void updateView() {
        // fills the view with model data
        view.getUserName().setText("Username: " + model.getUserName());
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
