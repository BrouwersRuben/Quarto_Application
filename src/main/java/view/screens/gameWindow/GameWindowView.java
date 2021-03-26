package main.java.view.screens.gameWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import main.java.view.Images;


public class GameWindowView extends BorderPane {
    // private Node attributes (controls)
    private Button pauseGame;
    private Button saveGame;
    private Button endGame;
    private Button winScreen;
    private Button loseScreen;
    private Label gameTitle;
    private Text turn;
    private Label turnCounter;
    private Label turnIndicator;

    private Text userName;

    // Bottom pane
    private HBox bottomBox;

    //GameWindow
    private HBox hBox;
    private VBox vBox;
    private GridPane gameBoard;
    private ImageView[][] gameBoardBack;
    private ImageView[] chosenPieceBack;
    private GridPane chosenPiece;
    private GridPane availablePieces;
    private ImageView[][] availablePiecesBack;
    private Label playerTurn;
    private Label timer;
    private Button quarto;
    private Text errorLabel;


    public GameWindowView() {
        initialiseNodes();
        layoutNodes();
        resetVariables();
    }

    private void resetVariables() {
        initialiseGameBoard();
        layoutGameboard();
        initialiseChosenPiece();
        layoutChosenPiece();
        initialiseAvailablePieces();
        layoutAvailablePieces();
    }

    private void initialiseNodes() {
        // create and configure controls
        // button = new Button("...")
        // label = new Label("...")

        //Title
        gameTitle = new Label("QUARTO");

        //GameBoard area
        gameBoardBack = new ImageView[4][4];
        gameBoard = new GridPane();
        chosenPieceBack = new ImageView[1];
        chosenPiece = new GridPane();
        availablePiecesBack = new ImageView[4][4];
        availablePieces = new GridPane();
        quarto = new Button("QUARTO!!");
        timer = new Label("00:00");
        errorLabel = new Text();


        //BottomBox
        pauseGame = new Button("Pause");
        saveGame = new Button("Save");
        endGame = new Button("End");
        userName = new Text();
        winScreen = new Button("WIN");
        loseScreen = new Button("LOSE");

        playerTurn = new Label();
    }

    private void initialiseChosenPiece() {
        this.chosenPieceBack[0] = new ImageView(Images.P0.getImage());
    }

    private void initialiseGameBoard() {
        for (int i = 0; i < this.gameBoardBack.length; i++) {
            for (int j = 0; j < this.gameBoardBack[i].length; j++) {
                this.gameBoardBack[i][j] = new ImageView(Images.P0.getImage());
                this.gameBoardBack[i][j].setId("free");
            }
        }
    }

    private void initialiseAvailablePieces() {
        int count = 0;
        for (int i = 0; i < this.availablePiecesBack.length; i++) {
            for (int j = 0; j < this.availablePiecesBack[i].length; j++) {
                count++;
                String path = "Images.P" + count + ".getImage().getUrl()";
                this.availablePiecesBack[i][j] = new ImageView(/*path*/"media/images/" + count + ".png");
                this.availablePiecesBack[i][j].setId(String.valueOf(count));
            }
        }
    }

    private void layoutChosenPiece() {
        this.chosenPiece.setGridLinesVisible(true);
        this.chosenPiece.setPadding(new Insets(5.0));
        this.chosenPiece.add(this.chosenPieceBack[0], 0, 0);
    }

    private void layoutGameboard() {
        this.gameBoard.setGridLinesVisible(true);
        this.gameBoard.setPadding(new Insets(5.0));
        for (int i = 0; i < this.gameBoardBack.length; i++) {
            for (int j = 0; j < this.gameBoardBack[i].length; j++) {
                this.gameBoard.add(this.gameBoardBack[i][j], i, j);
                GridPane.setMargin(this.gameBoardBack[i][j], new Insets(1.0));
            }
        }
    }

    private void layoutAvailablePieces() {
        this.availablePieces.setGridLinesVisible(false);
        this.availablePieces.setPadding(new Insets(5.0));
        for (int i = 0; i < this.availablePiecesBack.length; i++) {
            for (int j = 0; j < this.availablePiecesBack[i].length; j++) {
                this.availablePieces.add(this.availablePiecesBack[i][j], i, j);
                GridPane.setMargin(this.availablePiecesBack[i][j], new Insets(1.0));
            }
        }
    }

    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, …

        bottomBox = new HBox(saveGame, pauseGame, endGame, userName, errorLabel, winScreen, loseScreen);
        bottomBox.setPadding(new Insets(10, 10, 10, 10));
        HBox.setMargin(saveGame, new Insets(5, 10, 5, 10));
        HBox.setMargin(pauseGame, new Insets(5, 10, 5, 10));
        HBox.setMargin(endGame, new Insets(5, 10, 5, 10));
        HBox.setMargin(userName, new Insets(5, 10, 5, 10));
        HBox.setMargin(errorLabel, new Insets(5, 10, 5, 10));
        HBox.setMargin(winScreen, new Insets(5, 10, 5, 10));
        HBox.setMargin(loseScreen, new Insets(5, 10, 5, 10));
        userName.setStyle("-fx-font-size: 15");
        errorLabel.setStyle("-fx-font-size: 15;");
        errorLabel.setFill(Paint.valueOf("Red"));


        vBox = new VBox(chosenPiece, playerTurn, timer, quarto/*, moveCount*/);
        chosenPiece.setAlignment(Pos.CENTER);
        this.vBox.setAlignment(Pos.TOP_CENTER);

        VBox.setMargin(chosenPiece, new Insets(10, 10, 10, 10));
        VBox.setMargin(playerTurn, new Insets(10, 10, 10, 10));
        VBox.setMargin(quarto, new Insets(10, 10, 10, 10));

        hBox = new HBox(gameBoard, vBox, availablePieces);
        hBox.setAlignment(Pos.CENTER);

        HBox.setMargin(gameBoard, new Insets(10, 10, 10, 10));
        HBox.setMargin(vBox, new Insets(10, 10, 10, 10));
        HBox.setMargin(availablePieces, new Insets(10, 10, 10, 10));

        this.setTop(gameTitle);
        this.setCenter(hBox);
        this.setBottom(bottomBox);
        BorderPane.setAlignment(bottomBox, Pos.BOTTOM_RIGHT);

        gameTitle.setStyle("-fx-font-weight: BOLD; -fx-font-size: 20");
        BorderPane.setAlignment(gameTitle, Pos.CENTER);


    }

    // package-private Getters
    // for controls used by Presenter
    public Button getPauseGame() {
        return pauseGame;
    }

    public Button getSaveGame() {
        return saveGame;
    }

    public Button getWinScreen() {
        return winScreen;
    }

    public Button getLoseScreen() {
        return loseScreen;
    }

    public Button getEndGame() {
        return endGame;
    }

    public Label getPlayerTurn() {
        return playerTurn;
    }

    public GridPane getGameBoard() {
        return gameBoard;
    }

    public GridPane getAvailablePieces() {
        return availablePieces;
    }

    public GridPane getChosenPiece() {
        return chosenPiece;
    }

    public void setChosenPiece(GridPane chosenPiece) {
        this.chosenPiece = chosenPiece;
    }

    public ImageView[][] getGameBoardBack() {
        return gameBoardBack;
    }

    public ImageView[] getChosenPieceBack() {
        return chosenPieceBack;
    }

    public ImageView[][] getAvailablePiecesBack() {
        return availablePiecesBack;
    }

    public Button getQuarto() {
        return quarto;
    }

    public Label getTimer() {
        return timer;
    }

    public Text getUserName() {
        return userName;
    }

    public Text getErrorLabel() {
        return errorLabel;
    }
}

