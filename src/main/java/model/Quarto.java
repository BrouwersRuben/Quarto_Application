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
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    /**
     * Here the game is initialized.
     */
    public void startGame() {
        // if starting new game

        getGameID();
        human.setDateStarted(new Timestamp(System.currentTimeMillis()));
        username = getUserName();
        computer.setDifficult(true);

        remainingPieces.fillRemainingPieces();
        remainingPieces.fillPieces();
        board.fillWinningLines();
        board.fillRemainingSpots();
    }

    /**
     * METHOD WHICH TAKES CARE OF AI TURN BASED LOGIC
     */
    public void ruleBasedAI(boolean makeMove, int pieceID) {
        if (computer.isDifficult()) { // accesses methods  based on difficulty
            if (makeMove) {
                if (isBoardEmpty() && aiMakesFirstMove()) { // if AI has to make the move first then it generates the best move based on strategy
                    // (anywhere which can complete 3 winning lines)
                    generateFirstMove();
                    return;
                }

                if (winningMove(pieceID)) { // if AI sees a winning move he must place it
                    return;
                } else {
                    generateRandomCoordinates();
                }

                generateRandomCoordinates();

            } else {
                boolean safeDecision = false;
                remainingPieces.getRemainingPiecesClone().clear();
                remainingPieces.setRemainingPiecesClone(remainingPieces.getRemainingPieces());
                computer.setSelectedPiece(pieceID);

                while (!safeDecision) {// checks if the random piece that was given can result in the player winning, if true,
                    // it selects a different piece
                    if (!isPieceCloneListEmpty()) {
                        if (winningMove(computer.getSelectedPiece())) {
                            computer.setSelectedPiece(selectRandomPieceOnce());
                            safeDecision = false;
                        } else {
                            safeDecision = true;
                        }
                    } else {
                        computer.setSelectedPiece(selectRandomPiece());
                        return;
                    }
                }
            }
        } else {
            generateRandomCoordinates();
        }
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

    // Checks if there's remaining pieces in the cloned arrayList to simulate games with.
    public boolean isPieceCloneListEmpty() {
        return remainingPieces.getRemainingPiecesClone().isEmpty();
    }

    /**
     * Checks if there's a winning move for the computer.
     * @param pieceID pieceID
     * @return boolean - is there a winning move to be made
     */
    public boolean winningMove(int pieceID) {
        boolean winnable = false;

        for (int i = 0; i < board.getRemainingSpots().size(); i++) {
            if (simulatedTestPlay(board.getRemainingSpots().get(i), pieceID)) {
                computer.setCoordinateX((board.getRemainingSpots().get(i).get(0)));
                computer.setCoordinateY(board.getRemainingSpots().get(i).get(1));
                winnable = true;
            }
        }
        return winnable;
    }

    /**
     * Method for simulating the moves which the player could make with the given piece.
     * @param availableSpots arrayList of available tiles
     * @param pieceID pieceID
     * @return returns boolean of isItQuarto
     */
    @SuppressWarnings("unchecked") // boardStatus is
    private boolean simulatedTestPlay(ArrayList<Integer> availableSpots, int pieceID) {
        // If simulating where to place piece
        ArrayList<Integer> cloneRepresentation = (ArrayList<Integer>) board.getBoardStatus().clone();
        cloneRepresentation.set(convertCoordinates(true, availableSpots.get(0), availableSpots.get(1)), 1);

        boolean temporary = false;
        for (int j = 0; j < board.getWinningLines().size(); j++) {
            if (isThereALine(cloneRepresentation, j)) {
                if (isItAQuarto(board.getWinningLines().get(j), pieceID)) {
                    temporary = true;
                }
            }
        }
        return temporary;
    }

    /**
     * Method used for the simulation which makes sure that the
     * pieces from the remaining pieces list are selected once when selecting them with a randomizer.
     * @return pieceID
     */
    public int selectRandomPieceOnce() {
        int randomValue = random.nextInt(remainingPieces.getRemainingPiecesClone().size());
        int selectedPiece = remainingPieces.getRemainingPiecesClone().get(randomValue);
        remainingPieces.getRemainingPiecesClone().remove(randomValue);
        return selectedPiece;
    }


    public int getSelectedPiece() {
        return computer.getSelectedPiece();
    }

    public void generateFirstMove() {
        int randomMove = random.nextInt(8);
        computer.setCoordinateX(board.getValidFirstMoves()[randomMove][0]);
        computer.setCoordinateY(board.getValidFirstMoves()[randomMove][1]);
    }

    public void generateRandomCoordinates() {
        computer.setCoordinateX(random.nextInt(4));
        computer.setCoordinateY(random.nextInt(4));

        for (int i = 0; i < board.getUsedTiles().size(); i++) {
            if (board.getUsedTiles().get(i).get(1) == computer.getCoordinateX() && board.getUsedTiles().get(i).get(2) == computer.getCoordinateY()) {
                generateRandomCoordinates();
            }
        }
    }

    /**
     * Method which checks if there's a quarto
     * @param integers winningLine
     * @param pieceID pieceID
     * @return boolean value confirming whether it's a quarto or not
     */
    public boolean isItAQuarto(ArrayList<Integer> integers, int pieceID) {

        int first = findCorrespondingPiece(integers.get(0));
        int second = findCorrespondingPiece(integers.get(1));
        int third = findCorrespondingPiece(integers.get(2));
        int fourth = findCorrespondingPiece(integers.get(3));


        if(board.getBoardStatus().get(integers.get(0))==0) {
            first = pieceID-1;
        } else if(board.getBoardStatus().get(integers.get(1))==0) {
            second = pieceID-1;
        } else if(board.getBoardStatus().get(integers.get(2))==0) {
            third = pieceID-1;
        } else if(board.getBoardStatus().get(integers.get(3))==0) {
            fourth = pieceID-1;
        }

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
    Scans the board to see if the pieces on the board have created a line of four.
     @param gameBoard status of the gameBoard
     @param line winning line which is being compared to the gameBoard
     @return boolean value based on whether there is a line on the board.
     */
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


    /**
     * Method that selects a random piece for the player to place on the board.
     * Selects a random piece from an arrayList containing the pieces that haven't been used yet.
     * @return pieceID
     */
    public int selectRandomPiece() {
        return remainingPieces.getRemainingPieces().get(random.nextInt(remainingPieces.getRemainingPieces().size()));
    }

    /**
     * A method which accepts X and Y coordinates and converts them to a single digit
     * coordinate so that it could be used to indicate whether a board tile is occupied or not.
     * This is used to compare the board state to possible winning lines/combinations.
     * @param isAI boolean value, one represents the AI making the decision for himself, the other simulating the players decision
     * @param pieceColumn column coordinate of the location of the piece
     * @param pieceRow row coordinate of the location of the piece
     * @return single digit coordinate
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

    /**
    Updates an arrayList which contains various important values which are used for the game logic.
     @param pieceID pieceID
     @param pieceColumn column coordinate of the location of the piece
     @param pieceRow row coordinate of the location of the piece
     */
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
     * Removes the piece which has been used.
     * @param pieceID pieceID;
     */
    public void removeRemainingPieces(int pieceID) {
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
        turnData.createTurnData(id, turnID, turnStart, turnEnd);
    }

    // put it into Human class maybe..
    public int getGameID() {
        return gameStatistics.getGameID();
    }

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

    // Stores an integer value of the selected player.
    public int getPlayerSelected() {
        return record.getPlayerSelected();
    }

    // Gets the specific player data that will be shown for the advanced statistics page.
    public int getRecordsUserId(int i) {
        return gameStatistics.getRecords().get(i).getId();
    }

    // Gets statistics of the player selected from the top 5 leaderboard.
    public void getSpecificPlayerStatistics() {
        gameStatistics.getStatistics(getRecordsUserId(getPlayerSelected()));
    }

    // These next methods display the username, score, and other statistics for the advanced statistics page.
    public String getUsernameFromRecords(int i) {
        return gameStatistics.getRecords().get(i).getUsername();
    }

    public int getScoreFromRecords(int id) {
        return gameStatistics.getRecords().get(id).getScore();
    }

    public double getAverageTime() {
        BigDecimal bd = BigDecimal.valueOf(gameStatistics.getPlayerAverageMoveTime()).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
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

    // Blank image representing that no piece is selected.
    public String getPlaceHolder() {
        return remainingPieces.getPlaceHolder();
    }

    // Statistics for win/lose screen.
    public double getAllGameAverageScore() {
        return gameStatistics.getAllGameAverageScore();
    }

    public double getAllGameAverageTimePerTurn() {
        return gameStatistics.getAllGameAverageTimePerTurn();
    }

    //Business logic

    /**
     * Checks if the file containing the tutorial of the game exists in our game module.
     * @param path location path where the tutorial should be located
     * @return String explaining the status.
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
     * @param path location path of the video
     * @return video
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

    public void addToListOnBoard(Integer pieceId) {
        board.setPiecesOnBoard(pieceId);
    }

    public void setPlayerTurn(boolean value) {
        human.setPlayerTurn(value);
    }

    public boolean getPlayerTurn() {
        return human.getPlayerTurn();
    }

    public void setFirstMove(boolean startsFirst) {
        human.setFirstMove(startsFirst);
    }

    public int getX() {
        return computer.getCoordinateX();
    }

    public int getY() {
        return computer.getCoordinateY();
    }


    /**
     * Checks if the piece which the user has selected has been already placed or not.
     *
     * @param pieceID The identifier of the piece which was selected.
     * @return boolean value based on whether the piece has not been used
     */
    public boolean isUnique(Integer pieceID) {
        for (int i = 0; i < board.getPiecesOnBoard().size(); i++) {
            if (pieceID.equals(board.getPiecesOnBoard().get(i))) {
                return false;
            }
        }
        return true;
    }

    public void timerIncrement() { timer.timerIncrement(); }
    public String ddMinute() { return timer.getDdMinute(); }
    public String ddSecond() { return timer.getDdSecond(); }

    public void setTurn() { human.setTurn(human.getTurn() + 1); }
    public int getTurn() { return human.getTurn(); }

    public String turnIndicator() {
        if (human.getPlayerTurn()) {
            return "Your turn!";
        } else {
            return "Their turn!";
        }
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

    public void createTableIfDoesntExist() { database.createTableIfDoesntExist(); }
    public void closeDB() { database.closeDb(); }
}
