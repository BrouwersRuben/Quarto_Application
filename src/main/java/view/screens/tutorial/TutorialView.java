package main.java.view.screens.tutorial;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TutorialView extends BorderPane {
    ;

    // private Node attributes (controls)
    private Text title;
    private Text body;
    private Button exit;
    private Media media;
    private MediaPlayer player;
    private MediaView playerView;
    private Path path;

    private VBox mediaBox;
    private Slider time;
    private Button playButton;

    public TutorialView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        // create and configure controls
        // button = new Button("...")
        // label = new Label("...")
        title = new Text("Quarto Tutorial");
        body = new Text();
        exit = new Button("Exit");
        media = new Media(Paths.get("resources/media/videos/Quarto.mp4").toUri().toString());
        player = new MediaPlayer(media);
        playerView = new MediaView(player);
        mediaBox = new VBox();
        time = new Slider();
        playButton = new Button("||");
        path = Paths.get("resources/tutorial.txt");
    }

    private void layoutNodes() {
        // add/set … methodes
        // Insets, padding, alignment, …

        this.setPadding(new Insets(20, 20, 10, 20));

        this.setTop(title);
        setAlignment(title, Pos.TOP_CENTER);
        setMargin(exit, new Insets(15));
        title.setStyle("-fx-font-weight: BOLD;" +
                "-fx-font-size: 33;");

        body.setStyle("-fx-font-size: 18;");
        setAlignment(body, Pos.TOP_LEFT);
        this.setCenter(body);
        setMargin(body, new Insets(30));

        exit.setPrefWidth(200);
        exit.setPrefHeight(25);
        this.setBottom(exit);
        setMargin(exit, new Insets(25));
        setAlignment(exit, Pos.BOTTOM_CENTER);

        player.play();
        this.setRight(mediaBox);
        setAlignment(mediaBox, Pos.CENTER);

        mediaBox.getChildren().add(playerView);
        mediaBox.getChildren().add(time);
        mediaBox.getChildren().add(playButton);
        playerView.setFitWidth(500);
        mediaBox.setAlignment(Pos.CENTER);
        mediaBox.setPadding(new Insets(5, 10, 5, 10));
        VBox.setVgrow(time, Priority.ALWAYS);
    }

    // package-private Getters
// for controls used by Presenter

    public Button getExit() {
        return exit;
    }

    public Slider getTime() {
        return time;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public Text getBody() {
        return body;
    }

    public Path getPath() {
        return path;
    }
}