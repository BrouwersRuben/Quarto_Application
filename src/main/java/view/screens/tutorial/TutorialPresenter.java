package main.java.view.screens.tutorial;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;
import main.java.model.Quarto;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;

public class TutorialPresenter {
    private Quarto model;
    private TutorialView view;

    public TutorialPresenter(Quarto model, TutorialView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        // Adds event handlers (inner classes or lambdas)
        // to view controls
        // Event handlers: call model methods and
        // update the view.

        this.view.getExit().setOnAction(event -> {
            view.getPlayer().pause();
            setMainWindow();
            updateView();
        });

        this.view.getBody().setText(model.readTutorialFile(view.getPath()));

        this.view.getPlayButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                MediaPlayer.Status status = view.getPlayer().getStatus();
                if (status == status.PLAYING) {
                    if (view.getPlayer().getCurrentTime().greaterThanOrEqualTo(view.getPlayer().getTotalDuration())) {
                        view.getPlayer().seek(view.getPlayer().getStartTime());
                        view.getPlayer().play();
                    } else {
                        view.getPlayer().pause();
                        view.getPlayButton().setText(">");
                    }
                }
                if (status == status.HALTED || status == status.STOPPED || status == status.PAUSED) {
                    view.getPlayer().play();
                    view.getPlayButton().setText("||");
                }
            }
        });
        view.getPlayer().currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov)
            {
                updatesValues();
            }
        });
        view.getTime().valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov)
            {
                if (view.getTime().isPressed()) {
                    view.getPlayer().seek(view.getPlayer().getMedia().getDuration().multiply(view.getTime().getValue() / 100));
                }
            }
        });
    }

    private void updateView() {
        // fills the view with model data
    }



    private void setMainWindow() {
        QuartoView quartoView = new QuartoView();
        QuartoPresenter quartoPresenter = new QuartoPresenter(model, quartoView);
        view.getScene().setRoot(quartoView);
        quartoView.getScene().getWindow().setWidth(625);
        quartoView.getScene().getWindow().setHeight(425);
    }

    protected void updatesValues() {
        Platform.runLater(new Runnable() {
            public void run() {
                // Updating to the new time value
                // This will move the slider while running your video
                view.getTime().setValue(view.getPlayer().getCurrentTime().toMillis() /
                        view.getPlayer().getTotalDuration().toMillis() * 100);
            }
        });
    }

    }
