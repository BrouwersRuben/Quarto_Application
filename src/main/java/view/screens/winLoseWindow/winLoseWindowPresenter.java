package main.java.view.screens.winLoseWindow;

import javafx.application.Platform;
import javafx.event.Event;
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
            model.getLeaderboard();
            setLeaderboardWindow();
            updateView();
        });


    }

    private void updateView() {
        // fills the view with model data
        view.getPlayerScore().setText("Your score: "+model.getScore());
        view.getStat1().setText("Average score of all games: " + model.getAllGameAverageScore());
        view.getStat2().setText(model.scoredBetterThanAverage());
        view.getStat3().setText("You scored in the "+model.getGameDurationPercentile()+"% percentile");
        view.getPlayerName().setText("Statistics for "+model.getPlayerName());

        XYChart.Series<String, Number> lineChartPlayer = new XYChart.Series<>();
        XYChart.Series<String, Number> lineChartAverage = new XYChart.Series<>();
        XYChart.Series<String, Number> barChart1GameDurations = new XYChart.Series<>();

        XYChart.Series<String, Number> barChart2Average = new XYChart.Series<>();
        XYChart.Series<String, Number> barChart2Fastest = new XYChart.Series<>();
        XYChart.Series<String, Number> barChart2Slowest = new XYChart.Series<>();


        for (int i = 0; i < model.getTurnStatsSize(); i++) {
            String turn = "" + (i + 1);
            lineChartPlayer.getData().add(new XYChart.Data<>(turn, model.getTurnStats(i)));
            lineChartAverage.getData().add(new XYChart.Data<>(turn, model.getAverageTurnStats(i)));
        }

        lineChartPlayer.setName("Player");
        lineChartAverage.setName("Average");

        for (int i = 0; i<model.getGamesPlayedDuration().size(); i++) {
            barChart1GameDurations.getData().add(new XYChart.Data<>(""+i+"", model.getGamesPlayedDuration().get(i)));
        }

        barChart1GameDurations.setName("Individual games");


        barChart2Average.getData().add(new XYChart.Data<>("player", model.getAverageTime()));
        barChart2Average.getData().add(new XYChart.Data<>("average (all games combined)", model.getAllGameAverageMoveTime()));

        barChart2Fastest.getData().add(new XYChart.Data<>("player", model.getFastestMove()));
        barChart2Fastest.getData().add(new XYChart.Data<>("average (all games combined)", model.getAllGameFastestMoveTime()));

        barChart2Slowest.getData().add(new XYChart.Data<>("player", model.getSlowestMove()));
        barChart2Slowest.getData().add(new XYChart.Data<>("average (all games combined)", model.getAllGameSlowestMoveTime()));

        barChart2Average.setName("Average");
        barChart2Fastest.setName("Fastest");
        barChart2Slowest.setName("Slowest");





        view.getLineChart().getData().addAll(lineChartPlayer, lineChartAverage);
        view.getBarChart().getData().addAll(barChart1GameDurations);
        view.getBarChart2().getData().addAll(barChart2Fastest, barChart2Average, barChart2Slowest);

        view.getWinOrLose().setText(model.isHasWon() ? "Victory" : "Defeat");
        view.getEndGameStatus().setImage(model.isHasWon() ? new Image(Paths.get("resources/media/images/victory.png").toUri().toString()) : new Image(Paths.get("resources/media/images/defeat.png").toUri().toString()));
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
