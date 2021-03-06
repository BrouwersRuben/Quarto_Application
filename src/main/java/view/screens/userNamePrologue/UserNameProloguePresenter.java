package main.java.view.screens.userNamePrologue;

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
            if (view.getUserName().getText().trim().isEmpty()) {
                this.view.getError().setText("This cannot be empty!");
            } else if (view.getUserName().getText().length() > 15) {
                this.view.getError().setText("Your username cannot be this long");
            } else {
                setGameWindow();
                model.createTableIfDoesntExist();
                model.setUserName(view.getUserName().getText().replaceAll("\\s+", ""));
                model.startGame();
            }
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
        gameWindowView.getScene().getWindow().setWidth(625);
        gameWindowView.getScene().getWindow().setHeight(370);
        gameWindowPresenter.normalTimer();
    }
}