package main.java.view.screens.userNamePrologue;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.screens.gameWindow.GameWindowPresenter;
import main.java.view.screens.gameWindow.GameWindowView;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;

public class UserNameProloguePresenter {
    private final Quarto model;
    private final UserNamePrologueView view;

    public UserNameProloguePresenter(Quarto model, UserNamePrologueView view) {
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
        this.view.getBack().setOnAction(event -> {
            setMainWindow();
            updateView();
        });

        this.view.getStartGame().setOnAction(event -> {
            model.createTableIfDoesntExist();
            setGameWindow();
            updateView();
        });

        // TODO: Set gameWindow
    }

    private void updateView() {
        // fills the view with model data
    }

    public void addWindowEventHandlers() {
        Window window = view.getScene().getWindow();
        // Add event handlers to window
    }

    private void setMainWindow() {
        QuartoView quartoView = new QuartoView();
        QuartoPresenter quartoPresenter = new QuartoPresenter(model, quartoView);
        view.getScene().setRoot(quartoView);
        quartoView.getScene().getWindow().setWidth(625);
        quartoView.getScene().getWindow().setHeight(425);
    }

    private void setGameWindow() {
        GameWindowView gameWindowView = new GameWindowView();
        GameWindowPresenter gameWindowPresenter = new GameWindowPresenter(model, gameWindowView);
        view.getScene().setRoot(gameWindowView);
        gameWindowView.getScene().getWindow().setWidth(GameWindowPresenter.screenWidth);
        gameWindowView.getScene().getWindow().setHeight(GameWindowPresenter.screenHeight);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        gameWindowView.getScene().getWindow().setX((screenBounds.getWidth() - GameWindowPresenter.screenWidth) / 2);
        gameWindowView.getScene().getWindow().setY((screenBounds.getHeight() - GameWindowPresenter.screenHeight) / 2);
    }
}