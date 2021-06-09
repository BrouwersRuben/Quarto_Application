package main.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.model.Quarto;
import main.java.view.screens.main.QuartoPresenter;
import main.java.view.screens.main.QuartoView;

import java.sql.SQLException;

public class Main extends Application {
    Stage window;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        window = primaryStage;

//        window.setResizable(false);
//        Fucks in Linux

        window.setTitle("Quarto");
        final Quarto model = new Quarto();
        model.openDB();
        final QuartoView view = new QuartoView();
        final QuartoPresenter presenter = new QuartoPresenter(model, view);
        final Scene scene = new Scene(view);

        window.setWidth(625);
        window.setHeight(425);
        window.setScene(scene);
        presenter.addWindowEventHandlers();
        window.show();
    }
}
