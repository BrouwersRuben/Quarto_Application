package main.java.view.screens.winLoseWindow;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Window;
import main.java.model.Quarto;
import main.java.view.screens.leaderboard.LeaderboardPresenter;
import main.java.view.screens.leaderboard.LeaderboardView;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;
import main.java.view.screens.userNamePrologue.UserNameProloguePresenter;
import main.java.view.screens.userNamePrologue.UserNamePrologueView;

import java.nio.file.Paths;

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
        this.view.getReturnToLeaderboard().setOnAction(event -> {
            setLeaderboardWindow();
            updateView();
        });


    }

    private void updateView() {
        // fills the view with model data
        view.getPlayerScore().setText("score: "+model.getScore());
        view.getPlayerName().setText("Statistics for "+model.getPlayerName());
        view.getStat1().setText("average time spent per round: " + model.getAverageTime());
        view.getStat2().setText("fastest move: " + model.getFastestMove() + " seconds");
        view.getStat3().setText("slowest move: " + model.getSlowestMove() + " seconds");


        // TODO: DO THIS CORRECTLY, SO THAT PLAYER AND COMPUTER HAVE SEPARATE COLORS/INDICATORS
        XYChart.Series<String, Number> lineChart = new XYChart.Series<>();
        XYChart.Series<String, Number> barChart1 = new XYChart.Series<>();
        XYChart.Series<String, Number> barChart2 = new XYChart.Series<>();


        for (int i = 0; i < model.getTurnStatsSize(); i++) {
            String turn = "" + (i + 1);
            lineChart.getData().add(new XYChart.Data<>(turn, model.getTurnStats(i)));
        }

        lineChart.setName("Seconds spent per move");

        barChart1.getData().add(new XYChart.Data<>("player", model.getScore()));
        barChart1.getData().add(new XYChart.Data<>("average", model.getAllGameAverageScore()));

        barChart2.getData().add(new XYChart.Data<>("player", model.getAverageTime()));
        barChart2.getData().add(new XYChart.Data<>("average", model.getAllGameAverageTimePerTurn()));



        view.getLineChart().getData().addAll(lineChart);
        view.getBarChart().getData().addAll(barChart1);
        view.getBarChart2().getData().addAll(barChart2);

        view.getWinOrLose().setText(model.getWinState() ? "Victory" : "Defeat");
        view.getEndGameStatus().setImage(model.getWinState() ? new Image(Paths.get("resources/media/images/victory.png").toUri().toString()) : new Image(Paths.get("resources/media/images/defeat.png").toUri().toString()));
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
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//        userNamePrologueView.getScene().getWindow().setX((screenBounds.getWidth() - GameWindowPresenter.screenWidth) / 2);
//        userNamePrologueView.getScene().getWindow().setY((screenBounds.getHeight() - GameWindowPresenter.screenHeight) / 2);
    }

    private void setMainWindow() {
        QuartoView quartoView = new QuartoView();
        QuartoPresenter quartoPresenter = new QuartoPresenter(model, quartoView);
        view.getScene().setRoot(quartoView);
        quartoView.getScene().getWindow().setWidth(625);
        quartoView.getScene().getWindow().setHeight(425);
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//        quartoView.getScene().getWindow().setX((screenBounds.getWidth() - GameWindowPresenter.screenWidth) / 2);
//        quartoView.getScene().getWindow().setY((screenBounds.getHeight() - GameWindowPresenter.screenHeight) / 2);
    }

    private void setLeaderboardWindow() {
        LeaderboardView leaderboardView = new LeaderboardView();
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(model, leaderboardView);
        view.getScene().setRoot(leaderboardView);
        leaderboardView.getScene().getWindow().setWidth(625);
        leaderboardView.getScene().getWindow().setHeight(425);
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//        leaderboardView.getScene().getWindow().setX((screenBounds.getWidth() - GameWindowPresenter.screenWidth) / 2);
//        leaderboardView.getScene().getWindow().setY((screenBounds.getHeight() - GameWindowPresenter.screenHeight) / 2);
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
            model.closeDB();
            Platform.exit();
        }
    }
}
