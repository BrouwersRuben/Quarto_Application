package main.java.view.screens.gameWindow;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;
import main.java.view.screens.pauseScreen.PauseScreenPresenter;
import main.java.view.screens.pauseScreen.PauseScreenView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class GameWindowPresenter {
    private final Quarto model;
    private final GameWindowView view;

    // TODO: is there another place to store this? Maybe model?
    private int second = 0, minute = 0;
    private String ddSecond, ddMinute;
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
            timerStop();
            setPauseScreen();
            updateView();
        });
        this.view.getSaveGame().setOnAction(event -> {
            // TODO: some method that updates the database with the current state of the game
            System.out.println("You have indicated that you would like to save the game");
            updateView();
        });
        this.view.getEndGame().setOnAction(event -> {
            setMainWindow();
            // TODO: Go to winning/losing screen, also when timer reaches like a minute or 2
            updateView();
        });
        view.getQuarto().setOnAction(event -> {
            System.out.println("You have indicated that you saw a quarto");
            // TODO: Call the hasQuarto method to see if the quarto was right.
        });


        view.getPlayerTurn().setText("Your turn!\nTo pick a piece");
        view.getAvailablePieces().getChildren().forEach(item -> {
            item.setOnMouseClicked(mouseEvent -> {
                Image imBlank = new Image("media/images/0.png");
                view.getChosenPiece().getChildren().add(new ImageView(imBlank));

                Image im = new Image("media/images/" + item.getId() + ".png");
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

                Image im = new Image("media/images/" + view.getChosenPiece().getId() + ".png");
                view.getGameBoard().add(new ImageView(im), GridPane.getColumnIndex(item), GridPane.getRowIndex(item));

                Image imBlank = new Image("media/images/0.png");
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
}

/*    private EventHandler<ActionEvent> createSimpleEventHandlerForNewWindow(Scene scene) {
        return (event) -> {
            Stage stage = new Stage();
            stage.initOwner(view.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
//            stage.setX(view.getScene().getWindow().getX() + 100);
//            stage.setY(view.getScene().getWindow().getY() + 100);
            stage.setTitle("Quarto - Pause screen");
            stage.showAndWait();
            //stage.sizeToScene();
        };
    }*/
