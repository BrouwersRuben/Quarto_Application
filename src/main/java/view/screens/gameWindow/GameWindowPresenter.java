package main.java.view.screens.gameWindow;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
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
import java.text.DecimalFormat;

public class GameWindowPresenter {
    private final Quarto model;
    private final GameWindowView view;

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
            timerStart();
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
            model.hasQuarto();
        });
        view.getWinScreen().setOnAction(event -> {
            System.out.println("Showing the victory screen");
            model.setWon();
            //Temp
            model.getStatistics(model.getRecordsUserId(0));
            setWinLoseWindow();
            updateView();
        });
        view.getLoseScreen().setOnAction(event -> {
            System.out.println("Showing the defeat screen");
            if (minute == 2){
                model.setLost();
                //Temp
                model.getStatistics(model.getRecordsUserId(3));
                setWinLoseWindow();
            }
            updateView();
        });

        view.getPlayerTurn().setText("Your turn!\nTo pick a piece");

        view.getAvailablePieces().getChildren().forEach(piece -> {
            piece.setOnMouseClicked(mouseEvent -> {
                view.getChosenPiece().getChildren().add(new ImageView(Images.P0.getImage()));

                //this does not work
                //Image im = new Image(Images.piece.getID().getImage());

                Image im = new Image("media/images/" + piece.getId() + ".png");
                view.getChosenPiece().getChildren().add(new ImageView(im));
                view.getChosenPiece().setId(piece.getId());

                // TODO: Switch users/Turn taking
                //model.setUser1(false);

                view.getPlayerTurn().setText("Their turn!");


                if (model.isUnique(Integer.valueOf(piece.getId()))){
                    model.addToListOnBoard(Integer.valueOf(piece.getId()));
                    view.getErrorLabel().setText("");
                } else {
                    view.getErrorLabel().setText("This piece is already on the board");
                    view.getChosenPiece().setId("0");
                }

                System.out.println();
            });
        });

        view.getGameBoard().getChildren().forEach(item -> {
            item.setOnMouseClicked(mouseEvent -> {
                Image im = new Image("media/images/" + view.getChosenPiece().getId() + ".png");

                if(!model.isUnique(Integer.valueOf(view.getChosenPiece().getId()))){
                    view.getGameBoard().add(new ImageView(im), GridPane.getColumnIndex(item), GridPane.getRowIndex(item));
                    view.getChosenPiece().getChildren().add(new ImageView(Images.P0.getImage()));
                    view.getErrorLabel().setText("");
                } else {
                    view.getErrorLabel().setText("This piece is already on the board");
                }

                view.getPlayerTurn().setText("Your turn!");

                //model.placePiece() --> row column (pieceID enum value)
                //boolean? validity
                //or
                //model.pieceIsValid()
                //Model ==> Set/Collection

            });
        });

    }

    private void updateView() {
        // fills the view with model data
        //view.getUserName().setText(model.getUserName);
        //view.
        view.getUserName().setText("Username: " + model.getUserName());
    }

    public void addWindowEventHandlers() {
        Window window = view.getScene().getWindow();
        // Add event handlers to window

    }

    // TODO: is there another place to store this? Maybe model?
    private int second = 0, minute = 0;
    private String ddSecond, ddMinute;
    private Timer timer;

    public void normalTimer(){
        DecimalFormat dFormat = new DecimalFormat("00");
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  second++;

                  if(second == 60){
                      second = 0;
                      minute++;
                  }

                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        view.getTimer().setText(ddMinute + ":" + ddSecond);
                    }
                });
            }

        });
        timer.start();
    }

    public void timerStop(){
        timer.stop();
    }

    public void timerStart(){
        timer.start();
    }

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
