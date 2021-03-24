package main.java.view.screens.gameWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import main.java.view.Images;


public class GameWindowView extends BorderPane {
    // private Node attributes (controls)
    private Button pauseGame;
    private Button saveGame;
    private Button endGame;
    private Label gameTitle;
    // TODO: TurnCounter?? Ideas
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

    private void resetVariables(){
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

        playerTurn = new Label();
    }

    // TODO: put these methods in the model.Board class?
    private void initialiseChosenPiece(){
        this.chosenPieceBack[0] = new ImageView(Images.P0.getImage());
    }

    private void initialiseGameBoard(){
        for (int i = 0; i < this.gameBoardBack.length; i++){
            for (int j = 0; j < this.gameBoardBack[i].length; j++){
                this.gameBoardBack[i][j] = new ImageView(Images.P0.getImage());
                this.gameBoardBack[i][j].setId("free");
            }
        }
    }

    private void initialiseAvailablePieces(){
        int count = 0;
        for (int i = 0; i < this.availablePiecesBack.length; i++){
            for (int j = 0; j < this.availablePiecesBack[i].length; j++){
                count++;
                String path = "Images.P" + count + ".getImage().getUrl()";
                this.availablePiecesBack[i][j] = new ImageView(/*path*/"media/images/"+ count +".png");
                this.availablePiecesBack[i][j].setId(String.valueOf(count));
            }
        }
    }

    private void layoutChosenPiece(){
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

        bottomBox = new HBox(saveGame, pauseGame, endGame, userName, errorLabel);
        bottomBox.setPadding(new Insets(10, 10, 10, 10));
        bottomBox.setMargin(saveGame, new Insets(5, 10, 5, 10));
        bottomBox.setMargin(pauseGame, new Insets(5, 10, 5, 10));
        bottomBox.setMargin(endGame, new Insets(5, 10, 5, 10));
        bottomBox.setMargin(userName, new Insets(5, 10, 5, 10));
        bottomBox.setMargin(errorLabel, new Insets(5, 10, 5, 10));
        userName.setStyle("-fx-font-size: 15");
        errorLabel.setStyle("-fx-font-size: 15;");
        errorLabel.setFill(Paint.valueOf("Red"));


        vBox = new VBox(chosenPiece, playerTurn, timer, quarto/*, moveCount*/);
        chosenPiece.setAlignment(Pos.CENTER);
        this.vBox.setAlignment(Pos.TOP_CENTER);

        vBox.setMargin(chosenPiece, new Insets(10, 10, 10, 10));
        vBox.setMargin(playerTurn, new Insets(10, 10, 10, 10));
        vBox.setMargin(quarto, new Insets(10, 10, 10, 10));

        hBox = new HBox(gameBoard, vBox, availablePieces);
        hBox.setAlignment(Pos.CENTER);

        HBox.setMargin(gameBoard, new Insets(10, 10, 10, 10));
        HBox.setMargin(vBox, new Insets(10, 10, 10, 10));
        HBox.setMargin(availablePieces, new Insets(10, 10, 10, 10));

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


        for (int i = 1; i <= 16; i++) {
            pieces.getChildren().add(new ImageView(Paths.get("resources/media/images/" + i + ".png").toUri().toString()));
            //i think it is best to just make 11 buttons and add them where needed, because i have to be able to remove them as well, when they are placed on the board
        }

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                Rectangle tile = new Rectangle(60, 60);
                tile.setStyle("-fx-fill:whitesmoke; -fx-stroke:black; -fx-stroke-width:1");
                GridPane.setRowIndex(tile, i);
                GridPane.setColumnIndex(tile, j);
                gameBoard.getChildren().addAll(tile);
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
    public void setChosenPiece(GridPane chosenPiece) {
        this.chosenPiece = chosenPiece;
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

