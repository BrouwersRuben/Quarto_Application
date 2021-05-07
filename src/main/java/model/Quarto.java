package main.java.model;

import main.java.model.board.Board;
import main.java.model.board.GameTimer;
import main.java.model.board.Pieces;
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

public class Quarto {
    protected boolean isRunning;
    protected int amountOfTurns;
    // private attributes
    private final Leaderboard leaderboard = new Leaderboard();
    private final Database database = new Database();
    private final Board board = new Board();
    private final Pieces pieces = new Pieces();
    private final Human player = new Human();
    private final GameTimer timer = new GameTimer();
    private final Random random = new Random(); // for generating random AI move (temporary, maybe? makes no sense to place it board.class)
    //has to have a human, and a bridge between the model and the view

    public Quarto() {
// Constructor

    }
    // TODO: AI RANDOMIZED COORDINATE GENERATION
    boolean playerTurn;
    boolean validCoordinates=false;
    private int x;
    private int y;

    public int getX() { return x; }

    public int getY() { return y; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    // TODO: Should this be in board class or nah ?
    // Randomized coordinates
    public void generateValidCoordinates() {

        x = random.nextInt(4);
        y = random.nextInt(4);

        for (int i=0; i<board.getUsedTiles().size(); i++) {
            if (board.getUsedTiles().get(i).get(1) == x && board.getUsedTiles().get(i).get(2) == y) {
                generateValidCoordinates(); // This is so fucked, but it works
            }
        }
        setX(x);
        setY(y);
    }


    // TODO: GAME LOGIC

    public void startGame() {
        // if starting new game
        String username = getUserName();
        pieces.fillRemainingPieces();
        board.fillWinningLines();

    }

    // Check after each turn
    public boolean isGameOver() {
        System.out.println("Here is the current game status: "+board.getBoardStatus());

            for (int j=0; j<board.getWinningLines().size(); j++) {
                StringBuilder sb = new StringBuilder();
                 for (int k = 0; k<board.getWinningLines().get(j).size(); k++) {
                    if (board.getBoardStatus().get(board.getWinningLines().get(j).get(k)) == 1 ) {
                        sb.append("1");
                        if (sb.toString().equals("1111")) {
                            System.out.println("there's a line");
                            return true;
                        }
                    }
                }
            }


        // Checking if draw (when no pieces left and no winning line)
        return pieces.getRemainingPieces().size() == 0;
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
        return pieces.getRemainingPieces();
    }

    public int selectRandomPiece() {
        return pieces.getRemainingPieces().get(random.nextInt(pieces.getRemainingPieces().size()));
    }


    public void setUsedTiles(int pieceID, int pieceRow, int pieceColumn) {
        ArrayList<Integer> temporary = new ArrayList<>();
        temporary.add(pieceID);
        temporary.add(pieceRow);
        temporary.add(pieceColumn);
        board.getUsedTiles().add(temporary);
        System.out.println("Coordinates for pieces: "+board.getUsedTiles());
    }

    // TODO: NOTE: IM COUNTING TILES LEFT TO RIGHT
    /*                example
    //              board[4][4]:
    //    0 | 1 | 2 | 3
    //    -------+--------+--------+-------
    //    4 | 5 | 6 | 7
    //    -------+--------+--------+-------
    //    [0][2] | [1][2] | [2][2] | [3][2]
    //    -------+--------+--------+-------
    //    [0][3] | [1][3] | [2][3] | [3][3]
     */
    public void setCoordinatesToBoardStatus(int pieceColumn, int pieceRow) {
        int tempNumber = 999; // for testing, can be whatever (0)
        if (pieceRow==0) {
            tempNumber=pieceColumn;
        } else if (pieceRow==1) {
            tempNumber=pieceColumn+4;
        } else if (pieceRow==2) {
            tempNumber=pieceColumn+8;
        } else if (pieceRow==3) {
            tempNumber =pieceColumn+12;
        }
        board.getBoardStatus().set(tempNumber, 1);
        System.out.println("Board status: "+board.getBoardStatus());
    }

    // TODO: PLACE IN CORRECT CLASS

    public void removeRemainingPieces(Integer pieceID) {
        while (pieces.getRemainingPieces().contains(pieceID)) {
            pieces.getRemainingPieces().remove(pieceID);
        }
        System.out.println("Remaining pieces: "+pieces.getRemainingPieces());
    }

    public void createTurnData(int playerID, int turnID, Timestamp turnStart, Timestamp turnEnd) {
        Statistics statistics = new Statistics(playerID, turnID, turnStart, turnEnd);
    }


    public void createTableIfDoesntExist() {
        database.createTableIfDoesntExist();
    }

    public void getStatistics(int id) {
        leaderboard.getStatistics(id);
    }

    public String getRecords(int i) {
        return String.format("%d. %s - %d", i + 1, Leaderboard.records[i].getUsername(), Leaderboard.records[i].getScore());
    }

    public String getRecordsUserName(int i) {
        return Leaderboard.records[i].getUsername();
    }

    public int getRecordsUserScore(int i) {
        return Leaderboard.records[i].getScore();
    }

    public Long getAverageTime() {
        return Leaderboard.averageTime;
    }

    public int getFastestMove() {
        return Collections.min(Leaderboard.turnStats);
    }

    public int getSlowestMove() {
        return Collections.max(Leaderboard.turnStats);
    }

    public int getRecordsUserId(int i) {
        return Leaderboard.records[i].getId();
    }

    public int getTurnStatsSize() {
        return Leaderboard.turnStats.size();
    }

    public int getTurnstats(int i) {
        return Leaderboard.turnStats.get(i);
    }

    //Business logic

    public String checkIfTutorialExist(Path path) {
        if (Files.exists(path) && Files.isRegularFile(path)) {
            return "The File exist and is a regular file.";
        } else if (!Files.exists(path)) {
            return "The File does not exist, or the path you have given is wrong.";
        } else {
            return "Error";
        }
    }

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



    // WIN CONDITION

//    public boolean hasQuarto() {
//        // TODO: hasQuarto
//        /*if (hasquarto == true){
//            player.setHasQuarto(true);
//        } else {
//            player.setHasQuarto(false);
//        }*/
////        if (checkRows() == true || checkColumns() == true || checkDiagonals() == true) {
////            return true;
////        } else {
////            return false;
////        }
//        return false;
//    }
//    public boolean checkRows() {
//        for (int offset=0; offset<3; offset++) {
//            if (tileArray[offset] == tileArray[offset+1] && tileArray[offset] == tileArray[offset+2] && tileArray[offset] == tileArray[offset+3] && tileArray[offset] == 1) {
//                return true;
//            } else {
//                return false;
//            }
//    }
//    public boolean checkColumns() { return true; }
//    public boolean checkDiagonals() { return true; }



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
