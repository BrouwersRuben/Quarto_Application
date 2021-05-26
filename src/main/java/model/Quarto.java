package main.java.model;

import main.java.model.board.Board;
import main.java.model.board.GameTimer;
import main.java.model.board.RemainingPieces;
import main.java.model.dataBase.GameStatistics;
import main.java.model.dataBase.Database;
import main.java.model.dataBase.PlayerRecords;
import main.java.model.dataBase.TurnData;
import main.java.model.players.Computer;
import main.java.model.players.Human;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.*;

/**
 * This is the class which is responsible for the functionality of the game.
 * It contains all of the necessary objects of the other classes to make use of them
 * in a combined environment.
 *
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 * @version 1.0
 */
public class Quarto {

    protected String username;
    private final GameStatistics gameStatistics = new GameStatistics();
    private final TurnData turnData = new TurnData();
    private final PlayerRecords record = new PlayerRecords();
    private final Database database = new Database();
    private final Board board = new Board();
    private final RemainingPieces remainingPieces = new RemainingPieces();
    private final Human human = new Human();
    private final GameTimer timer = new GameTimer();
    private final Random random = new Random();
    private final Computer computer = new Computer();
    private Timestamp turnStart;
    private Timestamp turnEnd;


    public Quarto() {
        // Constructor

    }


    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFirstMove(boolean startsFirst) {
        human.setFirstMove(startsFirst);
    }


    // TODO: TESTING
    // who starts first
    public void setPlayerTurn(boolean value) {
        human.setPlayerTurn(value);
    }

    public boolean getPlayerTurn() {
        return human.getPlayerTurn();
    }

    /**
     * Method which makes sure that the coordinates AI generates are valid.
     * If they are not, the process is repeated until the AI finds an unoccupied tile.
     */

    public void ruleBasedAI(boolean makeMove, int pieceID) {

        if (makeMove) {
            if (isBoardEmpty() && aiMakesFirstMove()) { // if AI has to make the move first then it generates the best move based on strategy (anywhere which can complete 3 winning lines)
                generateFirstMove();
                return;
            }

            if (winningMove(pieceID)) { // if AI sees a winning move he must place it
                return;
            } else {
                generateRandomCoordinates();
            }

            if(!isBoardEmpty()) {
                int previousPiece = board.getUsedTiles().get(board.getUsedTiles().size()-1).get(0);
                System.out.println(previousPiece);
            }
            generateRandomCoordinates();


        } else {
            boolean safeDecision = false; // checks if the random piece that was given can result in the player winning, if true,
            // it selects a different piece
            remainingPieces.getRemainingPiecesClone().clear();
            remainingPieces.setRemainingPiecesClone(remainingPieces.getRemainingPieces());
            computer.setSelectedPiece(pieceID);

            if (areTherePiecesRemaining()) {
                if (canOpponentWin(getSelectedPiece())) {
                    System.out.println("Computer thinks it isn't a safe move");
                    System.out.println("================= real remaining ============ "+remainingPieces.getRemainingPieces());
                    computer.setSelectedPiece(selectRandomPieceOnce());
                } else {
                    System.out.println("Computer thinks it's a safe move");
                }
            } else {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                generateRandomCoordinates();
            }
        }
    }

    public boolean areTherePiecesRemaining() {
        return !remainingPieces.getRemainingPiecesClone().isEmpty();
    }

    public int selectRandomPieceOnce() {
        int randomValue = random.nextInt(remainingPieces.getRemainingPiecesClone().size());
        int selectedPiece = remainingPieces.getRemainingPiecesClone().get(randomValue);
        remainingPieces.getRemainingPiecesClone().remove(randomValue);
        System.out.println("te ir size: "+remainingPieces.getRemainingPiecesClone());
        return selectedPiece;
    }


    public int getSelectedPiece() {
        return computer.getSelectedPiece();
    }

    public boolean isBoardEmpty() {
        if (!board.getBoardStatus().contains(1)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean aiMakesFirstMove() {
        return !human.isFirstMove();
    }

    public boolean canOpponentWin(int pieceID) {
        boolean winnable = false;

        for (int i = 0; i < board.getRemainingSpots().size(); i++) {
            if (simulatedTestPlay(board.getRemainingSpots().get(i), pieceID)) {
                winnable = true;
            }
        }
        return winnable;
    }


    public boolean winningMove(int pieceID) {
        boolean winnable = false;

        for (int i = 0; i < board.getRemainingSpots().size(); i++) {
            if (simulatedTestPlay(board.getRemainingSpots().get(i), pieceID)) {
                setX(x = board.getRemainingSpots().get(i).get(0));
                setY(y = board.getRemainingSpots().get(i).get(1));
                winnable = true;
            }
        }
        return winnable;
    }

    public void generateFirstMove() {
        int randomMove = random.nextInt(8);
        x = board.getValidFirstMoves()[randomMove][0];
        y = board.getValidFirstMoves()[randomMove][1];
        setX(x);
        setY(y);
    }

    public void generateRandomCoordinates() {
        x = random.nextInt(4);
        y = random.nextInt(4);

        for (int i = 0; i < board.getUsedTiles().size(); i++) {
            if (board.getUsedTiles().get(i).get(1) == x && board.getUsedTiles().get(i).get(2) == y) {
                generateRandomCoordinates();
            }
        }
        setX(x);
        setY(y);
    }


    @SuppressWarnings("unchecked") // boardStatus is
    private boolean simulatedTestPlay(ArrayList<Integer> availableSpots, int pieceID) {
        // If simulating where to place piece
        ArrayList<Integer> cloneRepresentation = (ArrayList<Integer>) board.getBoardStatus().clone();
        cloneRepresentation.set(convertCoordinates(true, availableSpots.get(0), availableSpots.get(1)), 1);

        System.out.println("\nSecond clone: "+cloneRepresentation+"\n");
        boolean temporary = false;
        for (int j = 0; j < board.getWinningLines().size(); j++) {
            if (isThereALine(cloneRepresentation, j)) {
                if (simulatedQuartoCheck(board.getWinningLines().get(j), pieceID)) {
                    temporary = true;
                }
            }
        }
        return temporary;
    }


    public boolean simulatedQuartoCheck(ArrayList<Integer> integers, int pieceID) {
        int first = findCorrespondingPiece(integers.get(0));
        int second = findCorrespondingPiece(integers.get(1));
        int third = findCorrespondingPiece(integers.get(2));
        int fourth = pieceID-1;

        if (remainingPieces.getPieces().get(first).getFill() == remainingPieces.getPieces().get(second).getFill() && remainingPieces.getPieces().get(first).getFill() == remainingPieces.getPieces().get(third).getFill() && remainingPieces.getPieces().get(first).getFill() == remainingPieces.getPieces().get(fourth).getFill()) {
            return true;
        } else if (remainingPieces.getPieces().get(first).getColor() == remainingPieces.getPieces().get(second).getColor() && remainingPieces.getPieces().get(first).getColor() == remainingPieces.getPieces().get(third).getColor() && remainingPieces.getPieces().get(first).getColor() == remainingPieces.getPieces().get(fourth).getColor()) {
            return true;
        } else if (remainingPieces.getPieces().get(first).getShape() == remainingPieces.getPieces().get(second).getShape() && remainingPieces.getPieces().get(first).getShape() == remainingPieces.getPieces().get(third).getShape() && remainingPieces.getPieces().get(first).getShape() == remainingPieces.getPieces().get(fourth).getShape()) {
            return true;
        } else if (remainingPieces.getPieces().get(first).getLength() == remainingPieces.getPieces().get(second).getLength() && remainingPieces.getPieces().get(first).getLength() == remainingPieces.getPieces().get(third).getLength() && remainingPieces.getPieces().get(first).getLength() == remainingPieces.getPieces().get(fourth).getLength()) {
            return true;
        } else {
            return false;
        }
    }

    public void setBoardRepresentation(int x, int y) {
        board.setBoardRepresentation(x, y);
    }

    public void removeRemainingSpots(int x, int y) {
        board.removeRemainingSpots(x, y);
    }


    /**
     * Here the game is initialized.
     */
    public void startGame() {
        // if starting new game

        getGameID();
        human.setDateStarted(new Timestamp(System.currentTimeMillis()));
        username = getUserName();

        remainingPieces.fillRemainingPieces();
        remainingPieces.fillPieces();
        board.fillWinningLines();
        board.fillRemainingSpots();
    }


    /**
     * Method which checks if the game is over or not by
     * comparing the current board status with the pre-set winning lines.
     *
     * @return
     */
    public boolean isGameOver() {
        System.out.println("Here is the current game status: " + board.getBoardStatus());

        for (int j = 0; j < board.getWinningLines().size(); j++) {
            if (isThereALine(board.getBoardStatus(), j)) {
                System.out.println("There's a line. Checking if it's quarto...");
                if (isItAQuarto(board.getWinningLines().get(j))) {
                    gameStatistics.saveGame(turnData.getTurnStatistics(), getGameID(), getUserName(), human.getDifficulty(), human.isHasQuarto(), human.getDateStarted());
                    return true;
                }
            }
        }
        // Checking if draw (when no pieces left and no winning line).
        if (remainingPieces.getRemainingPieces().size() == 0) {
            return true;
        } else {
            System.out.println("It wasn't Quarto.\n");
            return false;
        }
    }

    public boolean isItAQuarto(ArrayList<Integer> integers) {
        int first = findCorrespondingPiece(integers.get(0));
        int second = findCorrespondingPiece(integers.get(1));
        int third = findCorrespondingPiece(integers.get(2));
        int fourth = findCorrespondingPiece(integers.get(3));

        if (remainingPieces.getPieces().get(first).getFill() == remainingPieces.getPieces().get(second).getFill() && remainingPieces.getPieces().get(first).getFill() == remainingPieces.getPieces().get(third).getFill() && remainingPieces.getPieces().get(first).getFill() == remainingPieces.getPieces().get(fourth).getFill()) {
            return true;
        } else if (remainingPieces.getPieces().get(first).getColor() == remainingPieces.getPieces().get(second).getColor() && remainingPieces.getPieces().get(first).getColor() == remainingPieces.getPieces().get(third).getColor() && remainingPieces.getPieces().get(first).getColor() == remainingPieces.getPieces().get(fourth).getColor()) {
            return true;
        } else if (remainingPieces.getPieces().get(first).getShape() == remainingPieces.getPieces().get(second).getShape() && remainingPieces.getPieces().get(first).getShape() == remainingPieces.getPieces().get(third).getShape() && remainingPieces.getPieces().get(first).getShape() == remainingPieces.getPieces().get(fourth).getShape()) {
            return true;
        } else if (remainingPieces.getPieces().get(first).getLength() == remainingPieces.getPieces().get(second).getLength() && remainingPieces.getPieces().get(first).getLength() == remainingPieces.getPieces().get(third).getLength() && remainingPieces.getPieces().get(first).getLength() == remainingPieces.getPieces().get(fourth).getLength()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isThereALine(ArrayList<Integer> gameBoard, int line) {
        return gameBoard.get(board.getWinningLines().get(line).get(0)) == 1 &&
                gameBoard.get(board.getWinningLines().get(line).get(1)) == 1 &&
                gameBoard.get(board.getWinningLines().get(line).get(2)) == 1 &&
                gameBoard.get(board.getWinningLines().get(line).get(3)) == 1;
    }

    public int findCorrespondingPiece(int tile) {
        int piece = 0;

        for (int j = 0; j < board.getUsedTiles().size(); j++) {
            if (tile == (board.getUsedTiles().get(j).get(3))) {
                piece = board.getUsedTiles().get(j).get(0) - 1;
            }
        }
        return piece;
    }

    public String turnIndicator() {
        if (human.getPlayerTurn()) {
            return "Your turn!";
        } else {
            return "Their turn!";
        }
    }

    /**
     * Method to help AI select a piece for the player to place on the board.
     *
     * @return
     */
    public int selectRandomPiece() {
        return remainingPieces.getRemainingPieces().get(random.nextInt(remainingPieces.getRemainingPieces().size()));
    }


    //TODO: CHANGE

    /**
     * A method which declares whether a board tile is occupied or not
     * with 1's and 0's.
     * This is later on used to compare the board state to possible winning lines/combinations.
     */

    public int convertCoordinates(boolean isAI, int pieceColumn, int pieceRow) {
        int tempNumber = 0; // for testing, can be whatever (0)
        if (pieceRow == 0) {
            tempNumber = pieceColumn;
        } else if (pieceRow == 1) {
            tempNumber = pieceColumn + 4;
        } else if (pieceRow == 2) {
            tempNumber = pieceColumn + 8;
        } else if (pieceRow == 3) {
            tempNumber = pieceColumn + 12;
        }

        if (!isAI) {
            removeRemainingSpots(pieceColumn, pieceRow);
        }

        return tempNumber;
    }

    public void updateBoardStatusAndUsedPieces(int pieceID, int pieceColumn, int pieceRow) {
        int integer = convertCoordinates(false, pieceColumn, pieceRow);
        board.getBoardStatus().set(integer, 1);
        ArrayList<Integer> temporary = new ArrayList<>();
        temporary.add(pieceID);
        temporary.add(pieceColumn);
        temporary.add(pieceRow);
        temporary.add(integer);
        board.getUsedTiles().add(temporary);
        System.out.println("Coordinates for pieces: " + board.getUsedTiles());
        System.out.println("Remaining spots: " + board.getRemainingSpots());
    }

    /**
     * Keeps track of which pieces the AI can pick for the player to make a turn with.
     */
    public void removeRemainingPieces(Integer pieceID) {
        while (remainingPieces.getRemainingPieces().contains(pieceID)) {
            remainingPieces.getRemainingPieces().remove(pieceID);
        }
        System.out.println("Remaining pieces: " + remainingPieces.getRemainingPieces());
    }


    // Methods for turnStatistics
    public void setStartTimestamp() {
        turnStart = turnData.createTimestamp();
    }

    public void setEndTimestamp() {
        turnEnd = turnData.createTimestamp();
    }

    public Timestamp getTurnStartTime() {
        return turnStart;
    }

    public Timestamp getTurnEndTime() {
        return turnEnd;
    }


    public void createTurnData(int id, int turnID, Timestamp turnStart, Timestamp turnEnd) {
        System.out.println(id + " " + turnID + " " + turnStart + " " + turnEnd);
        turnData.createTurnData(id, turnID, turnStart, turnEnd);
    }

    // put it into Human class maybe..
    public int getGameID() {
        return gameStatistics.getGameID();
    }


    /**
     * Creates an object which is used for entering advanced statistics data into the database.
     */

    // Prepares data that will be shown in the leaderboard page (top 5 players).
    public void getLeaderboard() {
        gameStatistics.getLeaderboard();
    }

    // Displays the top 5 players.
    public String getRecords(int i) {
        return String.format("%d. %s - %d", i + 1, gameStatistics.getRecords().get(i).getUsername(), gameStatistics.getRecords().get(i).getScore());
    }

    // Sets/Gets the player which the user required to see more info about.
    public void setPlayerSelected(int i) {
        record.setPlayerSelected(i);
    }

    public int getPlayerSelected() {
        return record.getPlayerSelected();
    }

    // Gets the specific player data that will be shown for the advanced statistics page.
    public int getRecordsUserId(int i) {
        return gameStatistics.getRecords().get(i).getId();
    }

    public void getTopFiveStatistics() {
        gameStatistics.getStatistics(getRecordsUserId(getPlayerSelected()));
    }


    // Displays the username, score, and other statistics for the advanced statistics page.
    public String getUsernameFromRecords(int i) {
        return gameStatistics.getRecords().get(i).getUsername();
    }

    public int getScoreFromRecords(int id) {
        return gameStatistics.getRecords().get(id).getScore();
    }

    public double getAverageTime() {
        return Math.round(gameStatistics.getPlayerAverageMoveTime()*100.0)/100.0;
    }

    public long getScore() {
        return gameStatistics.getPlayerScore();
    }

    public double getFastestMove() {
        return gameStatistics.getPlayerFastestMoveTime();
    }

    public double getSlowestMove() {
        return gameStatistics.getPlayerSlowestMoveTime();
    }

    public int getTurnStatsSize() {
        return gameStatistics.getPlayerTimeSpentOnTurn().size();
    }

    public double getTurnStats(int i) {
        return gameStatistics.getPlayerTimeSpentOnTurn().get(i);
    }

    public double getAverageTurnStats(int i) {
        return gameStatistics.getOverallTimeSpentOnTurn().get(i);
    }

    public String getPlaceHolder() {
        return remainingPieces.getPlaceHolder();
    }

    // Statistics for win/lose screen.
    public void getFinishedGameStatistics() {
        gameStatistics.getStatistics(getGameID());
    }

    public double getAllGameAverageScore() {
        return gameStatistics.getAllGameAverageScore();
    }

    public double getAllGameAverageTimePerTurn() {
        return gameStatistics.getAllGameAverageTimePerTurn();
    }

    //Business logic

    /**
     * Checks if the file containing the tutorial of the game exists in our game module.
     */
    public String checkIfTutorialExist(Path path) {
        if (Files.exists(path) && Files.isRegularFile(path)) {
            return "The File exist and is a regular file.";
        } else if (!Files.exists(path)) {
            return "The File does not exist, or the path you have given is wrong.";
        } else {
            return "Error";
        }
    }

    /**
     * Displays the tutorial.
     */
    public String readTutorialFile(Path path) {
        checkIfTutorialExist(path);
        try {
            File tutorialFile = new File(String.valueOf(path));
            Scanner myReader = new Scanner(tutorialFile);
            StringBuilder textFile = new StringBuilder();
            while (myReader.hasNext()) {
                textFile.append(myReader.nextLine());
                textFile.append("\n");
            }
            myReader.close();
            return textFile.toString();
        } catch (FileNotFoundException e) {
            return "An error occurred!";
        }
    }

    public String getUserName() {
        return human.getName();
    }

    public String getPlayerName() {
        return gameStatistics.getPlayerName();
    }

    public void setUserName(String name) {
        human.setName(name);
    }

    public boolean getWinState() {
        return human.isHasQuarto();
    }

    public void setWon() {
        human.setHasQuarto(true);
    }

    public void setLost() {
        human.setHasQuarto(false);
    }

    public void gameOver() {
        if (timer.getDdMinute().equals("02")) {
            human.setHasQuarto(false);
        }
    }

    public void addToListOnBoard(Integer pieceId) {
        board.setPiecesOnBoard(pieceId);
    }


    /**
     * Checks if the piece which the user has selected has been already placed or not.
     *
     * @param pieceId The identifier of the piece which was selected.
     * @return
     */
    public boolean isUnique(Integer pieceId) {
        for (int i = 0; i < board.getPiecesOnBoard().size(); i++) {
            if (pieceId.equals(board.getPiecesOnBoard().get(i))) {
                return false;
            }
        }
        return true;
    }

    public void timerIncrement() {
        timer.timerIncrement();
    }

    public String ddMinute() {
        return timer.getDdMinute();
    }

    public String ddSecond() {
        return timer.getDdSecond();
    }


    public void setTurn() {
        human.setTurn(human.getTurn() + 1);
    }

    public int getTurn() {
        return human.getTurn();
    }


    // Database related methods.
    public void openDB() {
        try {
            gameStatistics.connectToDb();
            database.connectToDb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        database.closeDb();
    }

    public void createTableIfDoesntExist() {
        database.createTableIfDoesntExist();
    }
}
