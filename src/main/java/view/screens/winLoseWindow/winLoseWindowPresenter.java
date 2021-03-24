package main.java.view.screens.winLoseWindow;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.screens.gameWindow.GameWindowPresenter;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;
import main.java.view.screens.userNamePrologue.UserNameProloguePresenter;
import main.java.view.screens.userNamePrologue.UserNamePrologueView;

public class winLoseWindowPresenter {
    private final Quarto model;
    private final winLoseWindowView view;

    public winLoseWindowPresenter(Quarto model, winLoseWindowView view) {
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
        this.view.getPlayAgain().setOnAction(event -> {
            setUserNamePrologue();
            updateView();
        });

        this.view.getMainMenu().setOnAction(event -> {
            setMainWindow();
            updateView();
        });

        this.view.getExitGame().setOnAction(event -> {
            closingAlert(event);
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

    private void setUserNamePrologue() {
        UserNamePrologueView userNamePrologueView = new UserNamePrologueView();
        UserNameProloguePresenter userNameProloguePresenter = new UserNameProloguePresenter(model, userNamePrologueView);
        view.getScene().setRoot(userNamePrologueView);
        userNamePrologueView.getScene().getWindow().sizeToScene();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        userNamePrologueView.getScene().getWindow().setX((screenBounds.getWidth() - GameWindowPresenter.screenWidth) / 2);
        userNamePrologueView.getScene().getWindow().setY((screenBounds.getHeight() - GameWindowPresenter.screenHeight) / 2);
    }

    private void setMainWindow() {
        QuartoView quartoView = new QuartoView();
        QuartoPresenter quartoPresenter = new QuartoPresenter(model, quartoView);
        view.getScene().setRoot(quartoView);
        quartoView.getScene().getWindow().setWidth(625);
        quartoView.getScene().getWindow().setHeight(425);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        quartoView.getScene().getWindow().setX((screenBounds.getWidth() - GameWindowPresenter.screenWidth) / 2);
        quartoView.getScene().getWindow().setY((screenBounds.getHeight() - GameWindowPresenter.screenHeight) / 2);
    }

    private void closingAlert(Event event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("You are about to quit the game!");
        alert.setContentText("Are you sure you want to quit the game!");
        alert.setTitle("Closing alert!");
        alert.getButtonTypes().clear();
        ButtonType no = new ButtonType("NO");
        ButtonType yes = new ButtonType("YES");
        alert.getButtonTypes().addAll(yes, no);
        alert.showAndWait();
        if (alert.getResult() == null || alert.getResult().equals(no)) {
            event.consume();
        } else {
//            lb.closeDb();
            Platform.exit();
        }
    }
}
