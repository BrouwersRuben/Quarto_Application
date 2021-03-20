package main.java.view.screens.gameWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameWindowView extends BorderPane { // TODO: make the layout responsive(currently fixated on px count) & just overall work on it more/clean up code
    // private Node attributes (controls)
    private Button pauseGame;
    private Button saveGame;
    private Button endGame;
    private Label gameTitle;
    private Text time;
    private Text turn;
    private Label timeCounter;
    private Label turnCounter;
    private Label turnIndicator;

    // Center pane
    GridPane pieceGrid = new GridPane(); // REMARK: What is this? I think it is best to make this gridpane as well, and make each image a button so it is clickable or something, but just remove the styling
    public static final int numColumns = 4; // TODO: remove one of these
    public static final int numRows = 4;

    GridPane gameBoard = new GridPane();
    HBox centerHBox = new HBox(gameBoard, pieceGrid);

    // Bottom pane
    GridPane bottomPane = new GridPane();
    GridPane bottomPaneTwo = new GridPane();
    HBox bottomHBox = new HBox(bottomPane, bottomPaneTwo);

    List<Image> pieces = new ArrayList<>(); // Initializing arrayList, to occupy it I've created loadPieces() function


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

        pieceGrid.setVgap(20);
        pieceGrid.setHgap(20);
//        pieces.setOrientation(Orientation.VERTICAL);


        for (int i=0; i<numColumns;i++) {
            for (int j=0; j<numRows;j++) {
//                for (int img = 1; img <= 16; img++) {
//                    pieces.getChildren().add(new ImageView(Paths.get("resources/media/images/" + img + ".png").toUri().toString()));
//
//                    //i think it is best to just make 11 buttons and add them where needed, because i have to be able to remove them as well, when they are placed on the board
//                }
                Rectangle pieceTile = new Rectangle(60, 60);
                pieceTile.setStyle("-fx-stroke:black; -fx-stroke-width:1");
                GridPane.setRowIndex(pieceTile, i);
                GridPane.setColumnIndex(pieceTile, j);
                pieceGrid.getChildren().addAll(pieceTile);
                for (int img=i; img<=16;img++) {
                    Image p = new Image(Paths.get("resources/media/images/" + img + ".png").toUri().toString());
                pieceTile.setFill(new ImagePattern(p));

            }
                pieceGrid.getRowCount();
            }
        }

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                Rectangle boardTile = new Rectangle(60, 60);
                boardTile.setStyle("-fx-fill:whitesmoke; -fx-stroke:black; -fx-stroke-width:1");
                GridPane.setRowIndex(boardTile, i);
                GridPane.setColumnIndex(boardTile, j);
                gameBoard.getChildren().addAll(boardTile);
                System.out.println(GridPane.getColumnIndex(pieceGrid));
            }
        }

        gameTitle.setPadding(new Insets(0, 0, 0, 85)); // top, right, bottom, left
        gameTitle.setStyle("-fx-font-weight: BOLD; -fx-font-size: 32");
        centerHBox.setPrefWidth(745);
        centerHBox.setPadding(new Insets(0, 20, 10, 20));
//        pieces.setPrefColumns(3); // ?this doesn't work?
        gameBoard.setPrefWidth(centerHBox.getPrefWidth() / 2); // TODO: shorten this
        pieceGrid.setPrefWidth(centerHBox.getPrefWidth() / 2);
        bottomPane.setPrefWidth(centerHBox.getPrefWidth() / 2);
        bottomPaneTwo.setPrefWidth(centerHBox.getPrefWidth() / 2);
    }


    public void loadPieces() {
        for (int img = 1; img <= 16; img++) {
            pieces.add(new Image(Paths.get("resources/media/images/" + img + ".png").toUri().toString()));
        }
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

    public GridPane getPieceGrid() {
        return pieceGrid;
    }
}

