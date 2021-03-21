package main.java.view.screens.gameWindow;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import main.java.view.Images;

import java.nio.file.Paths;
import java.util.ArrayList;

public class GameWindowView extends BorderPane { // TODO: make the layout responsive(currently fixated on px count) & just overall work on it more/clean up code
    // private Node attributes (controls)
    private ArrayList<Label> labels = new ArrayList<Label>();
    private Button pauseGame;
    private Button saveGame;
    private Button endGame;
    private Label gameTitle;
    private Text time; //TODO: STATUSBAR !!!!!
    private Text turn;
    private Label timeCounter;
    private Label turnCounter;
    private Label turnIndicator;
    private Images piecesImg;

    // Center pane
    TilePane pieces = new TilePane(); // REMARK: What is this? I think it is best to make this gridpane as well, and make each image a button so it is clickable or something, but just remove the styling
    public static final int numColumns = 4; // TODO: remove one of these
    public static final int numRows = 4;

    GridPane gameBoard = new GridPane();
    HBox centerHBox = new HBox(gameBoard, pieces);

    // Bottom pane
    GridPane bottomPane = new GridPane();
    GridPane bottomPaneTwo = new GridPane();
    HBox bottomHBox = new HBox(bottomPane, bottomPaneTwo);


    public GameWindowView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        // create and configure controls
        // button = new Button("...")
        // label = new Label("...")
        pauseGame = new Button("Pause");
        saveGame = new Button("Save");
        endGame = new Button("End");

        gameTitle = new Label("QUARTO");
        time = new Text("Time: ");
        turn = new Text("Turn: ");
        timeCounter = new Label("test");
        turnCounter = new Label("test");
        turnIndicator = new Label("Your turn!\nSelect a piece!");

    }

    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, …
        this.setTop(gameTitle);
        this.setCenter(centerHBox);
        this.setBottom(bottomHBox);

        bottomPaneTwo.add(turnIndicator, 0, 0);
        bottomPaneTwo.setAlignment(Pos.CENTER); //TODO: Center them in the middle vertically

        bottomPane.add(time, 1, 0);
        bottomPane.add(timeCounter, 2, 0);

        bottomPane.add(turn, 1, 1);
        bottomPane.add(turnCounter, 2, 1);

        bottomPane.add(saveGame, 0, 2);
        bottomPane.add(pauseGame, 1, 2);
        bottomPane.add(endGame, 2, 2);
//        bottomPane.setHgap(10); // TODO: Find out why this pushes the layout
//        bottomPane.setVgap(10);
        bottomPane.setPadding(new Insets(0, 0, 10, 60));

        pieces.setVgap(20);
        pieces.setHgap(20);
        pieces.setOrientation(Orientation.VERTICAL);


        for (int i = 0; i < 16; i++) {
            int cellCoord = i + 1;

            switch (i){
                case 0:
                    ImageView BFRS = new ImageView(piecesImg.BFRS.getImage());
                    Label BFRSLabel = new Label(String.valueOf(cellCoord), BFRS);
                    BFRSLabel.setId("moveable");
                    labels.add(BFRSLabel);
                case 1:
                    ImageView BHRS = new ImageView(piecesImg.BHRS.getImage());
                    Label BHRSLabel = new Label(String.valueOf(cellCoord), BHRS);
                    BHRSLabel.setId("moveable");
                    labels.add(BHRSLabel);
                case 2:
                    ImageView BFRT = new ImageView(piecesImg.BFRT.getImage());
                    Label BFRTLabel = new Label(String.valueOf(cellCoord), BFRT);
                    BFRTLabel.setId("moveable");
                    labels.add(BFRTLabel);
                case 3:
                    ImageView BHRT = new ImageView(piecesImg.BFRT.getImage());
                    Label BHRTLabel = new Label(String.valueOf(cellCoord), BHRT);
                    BHRTLabel.setId("moveable");
                    labels.add(BHRTLabel);
                case 3:
                    ImageView BHRT = new ImageView(piecesImg.BFRT.getImage());
                    Label BHRTLabel = new Label(String.valueOf(cellCoord), BHRT);
                    BHRTLabel.setId("moveable");
                    labels.add(BHRTLabel);
            }
        }

        gameTitle.setPadding(new Insets(0, 0, 0, 85)); // top, right, bottom, left
        gameTitle.setStyle("-fx-font-weight: BOLD; -fx-font-size: 32");
        centerHBox.setPrefWidth(745);
        centerHBox.setPadding(new Insets(0, 20, 10, 20));
        pieces.setPrefColumns(3); // ?this doesn't work?
        gameBoard.setPrefWidth(centerHBox.getPrefWidth() / 2); // TODO: shorten this
        pieces.setPrefWidth(centerHBox.getPrefWidth() / 2);
        bottomPane.setPrefWidth(centerHBox.getPrefWidth() / 2);
        bottomPaneTwo.setPrefWidth(centerHBox.getPrefWidth() / 2);
    }


    // package-private Getters
    // for controls used by Presenter
    public Button getPauseGame() {
        return pauseGame;
    }

    public Button getSaveGame() {
        return saveGame;
    }

    public Button getEndGame() {
        return endGame;
    }

    public TilePane getPieces() {
        return pieces;
    }
}

