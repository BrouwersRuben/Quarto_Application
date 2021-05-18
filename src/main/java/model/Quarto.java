package main.java.model;

import main.java.model.board.Board;
import main.java.model.board.GameTimer;
import main.java.model.board.RemainingPieces;
import main.java.model.dataBase.Leaderboard;
import main.java.model.dataBase.Database;
import main.java.model.dataBase.Statistics;
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
    private final Leaderboard leaderboard = new Leaderboard();
    private final Database database = new Database();
    private final Board board = new Board();
    private final RemainingPieces remainingPieces = new RemainingPieces();
    private static final Human player = new Human();
    private final GameTimer timer = new GameTimer();
    private final Random random = new Random();


    public Quarto() {
    // Constructor

    }

    boolean playerTurn;
    private int x;
    private int y;

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    /**
     * Method which makes sure that the coordinates AI generates are valid.
     * If they are not, the process is repeated until the AI finds an unoccupied tile.
     */
//    public void generateValidCoordinates() {
//        //TODO: NOT WORKING. maybe you can figure it out
////        boolean conditionMet=false;
////
////        do {
////            x = random.nextInt(4);
////            y = random.nextInt(4);
////            for (int i = 0; i < board.getUsedTiles().size(); i++) {
////                if (!board.getUsedTiles().get(i).get(1).equals(x) && !board.getUsedTiles().get(i).get(2).equals(y)) {
////                    conditionMet=true;
////                }
////            }
////        }
////        while ((!conditionMet));
////        setX(x);
////        setY(y);
//        x = random.nextInt(4);
//        y = random.nextInt(4);
//
//        for (int i=0; i<board.getUsedTiles().size(); i++) {
//            if (board.getUsedTiles().get(i).get(1).equals(x) && board.getUsedTiles().get(i).get(2).equals(y)) {
//                generateValidCoordinates();
//            } else {
//                setX(x);
//                setY(y);
//            }
//        }
//    }

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
                if (isItAQuarto(board.getWinningLines().get(j), j)) {
                    database.saveRecord(username);
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

    public boolean isItAQuarto(ArrayList<Integer> integers, int line) {
        int first = findCorrespondingPiece(line, integers.get(0));
        int second = findCorrespondingPiece(line, integers.get(1));
        int third = findCorrespondingPiece(line, integers.get(2));
        int fourth = findCorrespondingPiece(line, integers.get(3));

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

    public int findCorrespondingPiece(int line, int tile) {
        int piece = 0;

        for (int j = 0; j < board.getUsedTiles().size(); j++) {
            if (tile==(board.getUsedTiles().get(j).get(3))) {
                piece = board.getUsedTiles().get(j).get(0)-1;
            }
        }
        return piece;
    }

    public String turnIndicator() {
        if (playerTurn) {
            return "Your turn!";
        } else {
            return "Their turn!";
        }
    }

    public boolean isPlayerTurn() { return playerTurn; }
    public void setPlayerTurn(boolean playerTurn) { this.playerTurn = playerTurn; }

    public ArrayList<Integer> retrieveRemainingPieces() {
        return remainingPieces.getRemainingPieces();
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

    /**
     * Creates an object which is used for entering advanced statistics data into the database.
     */
    public void createTurnData(int playerID, int turnID, Timestamp turnStart, Timestamp turnEnd, double score) {
        Statistics statistics = new Statistics(playerID, turnID, turnStart, turnEnd, score);
    }


    public void createTableIfDoesntExist() { database.createTableIfDoesntExist(); }

    public void getStatistics(int id) { leaderboard.getStatistics(id); }

    public String getRecords(int i) { return String.format("%d. %s - %d", i + 1, Leaderboard.records[i].getUsername(), Leaderboard.records[i].getScore()); }

    public String getRecordsUserName(int i) { return Leaderboard.records[i].getUsername(); }

    public int getRecordsUserScore(int i) { return Leaderboard.records[i].getScore(); }

    public Long getAverageTime() { return Leaderboard.averageTime; }

    public int getFastestMove() { return Collections.min(Leaderboard.turnStats); }

    public int getSlowestMove() { return Collections.max(Leaderboard.turnStats); }

    public int getRecordsUserId(int i) { return Leaderboard.records[i].getId(); }

    public int getTurnStatsSize() { return Leaderboard.turnStats.size(); }

    public int getTurnStats(int i) { return Leaderboard.turnStats.get(i); }

    public String getPlaceHolder() {
        return remainingPieces.getPlaceHolder();
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
        return player.getName();
    }

    public void setUserName(String name) {
        player.setName(name);
    }

    public boolean setWon() {
        return player.setHasQuarto(true);
    }

    public boolean setLost() {
        return player.setHasQuarto(false);
    }

    public boolean getWinState() {
        return player.isHasQuarto();
    }

    public void gameOver() {
        if (timer.getDdMinute().equals("02")) {
            player.setHasQuarto(false);
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
        leaderboard.getLeaderboard();
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
