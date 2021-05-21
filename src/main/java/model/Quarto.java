package main.java.model;

import main.java.model.board.Board;
import main.java.model.board.GameTimer;
import main.java.model.board.RemainingPieces;
import main.java.model.dataBase.GameStatistics;
import main.java.model.dataBase.Database;
import main.java.model.dataBase.TurnStatistics;
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
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 * @version 1.0
 */
public class Quarto {
    protected boolean isRunning;
    protected int amountOfTurns;
    protected int gameID;
    protected String username;
    private final GameStatistics gameStatistics = new GameStatistics();
    private final TurnStatistics turnStatistics = new TurnStatistics();
    private final Database database = new Database();
    private final Board board = new Board();
    private final RemainingPieces remainingPieces = new RemainingPieces();
    private final Human human = new Human();
    private final GameTimer timer = new GameTimer();
    private final Random random = new Random();
    private Timestamp turnStartTime;
    private Timestamp turnEndTime;
    private int turn;


    public Quarto() {
    // Constructor

    }


    private int x;
    private int y;

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }


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

//        //TODO: NOT WORKING. maybe you can figure it out


    public void generateValidCoordinates() {

        x = random.nextInt(4);
        y = random.nextInt(4);

        for (int i=0; i<board.getUsedTiles().size(); i++) {
            if (board.getUsedTiles().get(i).get(1) == x && board.getUsedTiles().get(i).get(2) == y) {
                generateValidCoordinates();
            }
        }
        setX(x);
        setY(y);
    }


    /**
     * Here the game is initialized.
     */
    public void startGame() {
        // if starting new game
        getGameID();
        username = getUserName();

        remainingPieces.fillRemainingPieces();
        remainingPieces.fillPieces();
        board.fillWinningLines();
    }

    public void saveGame() {

    }




    /**
     * Method which checks if the game is over or not by
     * comparing the current board status with the pre-set winning lines.
     * @return
     */
    public boolean isGameOver() {
        System.out.println("Here is the current game status: " + board.getBoardStatus());

        for (int j = 0; j < board.getWinningLines().size(); j++) {
            if (isThereALine(j)) {
                System.out.println("There's a line. Checking if it's quarto...");
                if (isItAQuarto(board.getWinningLines().get(j))) {
//                    database.saveRecord(username);
                    gameStatistics.saveGameStatistics(turnStatistics.getTurnStatsArray(), getGameID(), getUserName());
                    return true;
                }
            }
        }
        // Checking if draw (when no pieces left and no winning line).
        if (remainingPieces.getRemainingPieces().size() == 0) {
            return true;
        } else {
            System.out.println("It wasn't Quarto.");
            return false;
        }
    }

    public boolean isItAQuarto(ArrayList<Integer> integers) {
        int first = findCorrespondingPiece(integers.get(0));
        int second = findCorrespondingPiece(integers.get(1));
        int third = findCorrespondingPiece(integers.get(2));
        int fourth = findCorrespondingPiece(integers.get(3));

        if (remainingPieces.getPieces().get(first).getFill()==remainingPieces.getPieces().get(second).getFill() && remainingPieces.getPieces().get(first).getFill()==remainingPieces.getPieces().get(third).getFill() && remainingPieces.getPieces().get(first).getFill()==remainingPieces.getPieces().get(fourth).getFill()) {
            return true;
        } else if (remainingPieces.getPieces().get(first).getColor()==remainingPieces.getPieces().get(second).getColor() && remainingPieces.getPieces().get(first).getColor()==remainingPieces.getPieces().get(third).getColor() && remainingPieces.getPieces().get(first).getColor()==remainingPieces.getPieces().get(fourth).getColor()) {
            return true;
        } else if (remainingPieces.getPieces().get(first).getShape()==remainingPieces.getPieces().get(second).getShape() && remainingPieces.getPieces().get(first).getShape()==remainingPieces.getPieces().get(third).getShape() && remainingPieces.getPieces().get(first).getShape()==remainingPieces.getPieces().get(fourth).getShape()) {
            return true;
        } else if (remainingPieces.getPieces().get(first).getLength()==remainingPieces.getPieces().get(second).getLength() && remainingPieces.getPieces().get(first).getLength()==remainingPieces.getPieces().get(third).getLength() && remainingPieces.getPieces().get(first).getLength()==remainingPieces.getPieces().get(fourth).getLength()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isThereALine(int line) {
        return board.getBoardStatus().get(board.getWinningLines().get(line).get(0)) == 1 &&
                board.getBoardStatus().get(board.getWinningLines().get(line).get(1)) == 1 &&
                board.getBoardStatus().get(board.getWinningLines().get(line).get(2)) == 1 &&
                board.getBoardStatus().get(board.getWinningLines().get(line).get(3)) == 1;
    }

    public int findCorrespondingPiece(int tile) {
        int piece = 0;

        for (int j = 0; j < board.getUsedTiles().size(); j++) {
            if (tile==(board.getUsedTiles().get(j).get(3))) {
                piece = board.getUsedTiles().get(j).get(0)-1;
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

    public int convertCoordinates(int pieceColumn, int pieceRow) {
        int tempNumber=0; // for testing, can be whatever (0)
        if (pieceRow==0) {
            tempNumber=pieceColumn;
        } else if (pieceRow==1) {
            tempNumber=pieceColumn+4;
        } else if (pieceRow==2) {
            tempNumber=pieceColumn+8;
        } else if (pieceRow==3) {
            tempNumber =pieceColumn+12;
        }
        return tempNumber;
    }

    public void updateBoardStatusAndUsedPieces(int pieceID, int pieceColumn, int pieceRow) {
        int integer = convertCoordinates(pieceColumn, pieceRow);
        board.getBoardStatus().set(integer, 1);
        ArrayList<Integer> temporary = new ArrayList<>();
        temporary.add(pieceID);
        temporary.add(pieceRow);
        temporary.add(pieceColumn);
        temporary.add(integer);
        board.getUsedTiles().add(temporary);
        System.out.println("Coordinates for pieces: "+board.getUsedTiles());
    }

    /**
     * Keeps track of which pieces the AI can pick for the player to make a turn with.
     */
    public void removeRemainingPieces(Integer pieceID) {
        while (remainingPieces.getRemainingPieces().contains(pieceID)) {
            remainingPieces.getRemainingPieces().remove(pieceID);
        }
        System.out.println("Remaining pieces: "+ remainingPieces.getRemainingPieces());
    }


    // Methods for turnStatistics
    public Timestamp setStartTimestamp() { return turnStartTime = turnStatistics.setTimeStamp(); }
    public Timestamp setEndTimestamp() { return turnEndTime = turnStatistics.setTimeStamp(); }
    public Timestamp getTurnStartTime() { return turnStartTime; }
    public Timestamp getTurnEndTime() { return turnEndTime; }

    public void createTurnData(int turnID, Timestamp turnStart, Timestamp turnEnd) {
        turnStatistics.createTurnData(turnID, turnStart, turnEnd);
    }

    public ArrayList<TurnStatistics> getTurnStatsArray() {
        return turnStatistics.getTurnStatsArray();
    }

    public void setTurnID(int turn) { turn=turn+1; }
    public int getTurnID() { return turn; }

    public int getGameID() {
        return gameStatistics.getGameID();
    }


    /**
     * Creates an object which is used for entering advanced statistics data into the database.
     */



    public void createTableIfDoesntExist() { database.createTableIfDoesntExist(); }

    public void getStatistics(int id) { gameStatistics.getStatistics(id); }

    public String getRecords(int i) { return String.format("%d. %s - %d", i + 1, GameStatistics.records[i].getUsername(), GameStatistics.records[i].getScore()); }

    public String getRecordsUserName(int i) { return GameStatistics.records[i].getUsername(); }

    public int getRecordsUserScore(int i) { return GameStatistics.records[i].getScore(); }

    public Long getAverageTime() { return GameStatistics.averageTime; }

    public int getFastestMove() { return Collections.min(GameStatistics.turnStats); }

    public int getSlowestMove() { return Collections.max(GameStatistics.turnStats); }

    public int getRecordsUserId(int i) { return GameStatistics.records[i].getId(); }

    public int getTurnStatsSize() { return GameStatistics.turnStats.size(); }

    public int getTurnStats(int i) { return GameStatistics.turnStats.get(i); }

    public String getPlaceHolder() { return remainingPieces.getPlaceHolder(); }

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

    public void setUserName(String name) {
        human.setName(name);
    }

    public boolean setWon() {
        return human.setHasQuarto(true);
    }

    public boolean setLost() {
        return human.setHasQuarto(false);
    }

    public boolean getWinState() {
        return human.isHasQuarto();
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

    public void closeDB() {
        database.closeDb();
    }

    public void openDB() {
        database.connectToDb();
        gameStatistics.getLeaderboard();
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


}
