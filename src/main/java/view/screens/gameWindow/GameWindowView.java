package main.java.view.screens.gameWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameWindowView extends BorderPane { // TODO: make the layout responsive(currently fixated on px count) & just overall work on it more/clean up code
    // private Node attributes (controls)
    public static final int gridDimension = 4;
    private Button pauseGame;
    private Button saveGame;
    private Button endGame;
    private Label gameTitle;
    private Label time;
    private Label turn;
    private Label timeCounter;
    private Label turnCounter;
    private Label turnIndicator;

    // Center pane
    GridPane pieceGrid = new GridPane(); // REMARK: What is this? I think it is best to make this gridpane as well, and make each image a button so it is clickable or something, but just remove the styling
    GridPane gameBoard = new GridPane();
    HBox centerHBox = new HBox(gameBoard, pieceGrid);

    // Bottom pane
    GridPane leftBottomPane = new GridPane();
    GridPane rightBottomPane = new GridPane();
    HBox bottomHBox = new HBox(leftBottomPane, rightBottomPane);

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
        time = new Label("Time: ");
        turn = new Label("Turn: ");
        timeCounter = new Label("test");
        turnCounter = new Label("test");
        turnIndicator = new Label("    Your turn!\nSelect a piece!");
    }

    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, …
        this.setTop(gameTitle);
        gameTitle.setStyle("-fx-font-weight: BOLD; -fx-font-size: 42");
        BorderPane.setAlignment(gameTitle, Pos.CENTER);

        this.setCenter(centerHBox);
        centerHBox.setPrefWidth(745);
        centerHBox.setPadding(new Insets(0, 20, 10, 20));
        gameBoard.setPrefWidth(setWidthForHBox());
        gameBoard.setAlignment(Pos.CENTER); // will improve it later
        pieceGrid.setPrefWidth(setWidthForHBox());
        pieceGrid.setAlignment(Pos.CENTER);

        this.setBottom(bottomHBox);
        leftBottomPane.add(time, 1, 0);
        leftBottomPane.add(timeCounter, 2, 0);
        leftBottomPane.add(turn, 1, 1);
        leftBottomPane.add(turnCounter, 2, 1);
        leftBottomPane.add(saveGame, 0, 2);
        leftBottomPane.add(pauseGame, 1, 2);
        leftBottomPane.add(endGame, 2, 2);
        leftBottomPane.setPrefWidth(setWidthForHBox());
        leftBottomPane.setAlignment(Pos.TOP_CENTER);

        rightBottomPane.setPrefWidth(setWidthForHBox());
        rightBottomPane.add(turnIndicator, 0, 0);
        rightBottomPane.setAlignment(Pos.CENTER); //TODO: Center them in the middle vertically
        time.setStyle("-fx-font-size:20");
        turn.setStyle("-fx-font-size:20");
        timeCounter.setStyle("-fx-font-size:20");
        turnCounter.setStyle("-fx-font-size:20");
        turnIndicator.setStyle("-fx-font-size:20");

        for (int i = 0; i< gridDimension; i++) {
            for (int j=0; j<gridDimension;j++) {
                Rectangle pieceTile = new Rectangle(setWidthForHBox() / 6, setWidthForHBox() / 6);
                pieceTile.setStyle("-fx-fill:beige;-fx-stroke:black; -fx-stroke-width:1");
                GridPane.setRowIndex(pieceTile, i);
                GridPane.setColumnIndex(pieceTile, j);
                pieceGrid.getChildren().addAll(pieceTile);
            }
        }

        for (int i = 0; i < gridDimension; i++) {
            for (int j = 0; j <gridDimension; j++) {
                Rectangle boardTile = new Rectangle(setWidthForHBox() / 6, setWidthForHBox() / 6);
                boardTile.setStyle("-fx-fill:whitesmoke; -fx-stroke:black; -fx-stroke-width:1");
                GridPane.setRowIndex(boardTile, i);
                GridPane.setColumnIndex(boardTile, j);
                gameBoard.getChildren().addAll(boardTile);
                System.out.println(GridPane.getColumnIndex(pieceGrid));
            }
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

    public double setWidthForHBox() {
        return GameWindowPresenter.getScreenWidth() / 2;
    }
}

