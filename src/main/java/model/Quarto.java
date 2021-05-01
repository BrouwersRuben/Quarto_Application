package main.java.model;

import main.java.model.board.Board;
import main.java.model.board.GameTimer;
import main.java.model.dataBase.Leaderboard;
import main.java.model.dataBase.SaveAndLoad;
import main.java.model.players.Human;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Quarto {
    protected boolean isRunning;
    protected int amountOfTurns;
    // private attributes
    private final Leaderboard leaderboard = new Leaderboard();
    private final SaveAndLoad createTable = new SaveAndLoad();
    private final Board board = new Board();
    private final Human player = new Human();
    private final GameTimer timer = new GameTimer();
    private Random random; // for generating random AI move (temporary, maybe? makes no sense to place it board.class)
    //has to have a human, and a bridge between the model and the view



    public Quarto() {
// Constructor

    }

    // TODO: GAME LOGIC

    public void startGame() {
        // if game doesn't exist
        board.fillRemainingPieces();

    }

    public void createRemainingPieces() {

    }

    public void setUsedTiles(int pieceID, int pieceRow, int pieceColumn) {
        ArrayList<Integer> temporary = new ArrayList<>();
        temporary.add(pieceID);
        temporary.add(pieceRow);
        temporary.add(pieceColumn);
        board.getUsedTiles().add(temporary);
        System.out.println(board.getUsedTiles());
        System.out.println(board.getUsedTiles().get(0).get(0));
    }

//    public void generateCoordinates() { // TODO: PLACE IT IN THE CORRECT CLASS
//        int temporary = random.nextInt(board.getRemainingTiles().size());
//        int x = board.getRemainingTiles().get(temporary).charAt(0);
//        int y = board.getRemainingTiles().get(temporary).charAt(1);
//        int piece = board.getRemainingPieces().get(random.nextInt(board.getRemainingPieces().size()));
//    }

    // TODO: PLACE IN CORRECT CLASS

    public int selectRandomPiece() {
        return board.getRemainingPieces().get(random.nextInt(board.getRemainingPieces().size()));
    }

    public int generateXCoordinate() {
        int x = random.nextInt(4);

        for (int i=0; i<board.getUsedTiles().size(); i++) {
            if (board.getUsedTiles().get(i).get(2)!=x) {
                break;
            }
        } return x;
    }

    public int generateYCoordinate() {
        int y = random.nextInt(4);

        for (int i=0; i<board.getUsedTiles().size(); i++) {
            if (board.getUsedTiles().get(i).get(3)!=y) {
                break;
            }
        } return y;
    }


//    public void setPieces(int pieceID, int pieceRow, int pieceColumn, int pieceStatus) { // 2D array rows first
//        int tile = 0;
//        if (pieceRow==0) {
//            tile=pieceColumn;
//        } else if (pieceRow==1) {
//            tile=pieceColumn+4;
//        } else if (pieceRow==2) {
//            tile=pieceColumn+8;
//        } else if (pieceRow==3) {
//            tile=pieceColumn+12;
//        }
//
//
//        board.getUsedTiles().add(new ArrayList<Integer>(pieceID, pieceRow, pieceColumn));
//        board.getUsedTiles().get(0).add(1, pieceRow);
//        board.getUsedTiles().get(0).add(2, pieceColumn);
//        System.out.println(board.getUsedTiles());
//    }

    public void removeRemainingPieces(Integer pieceID) {
        while (board.getRemainingPieces().contains(pieceID)) {
            board.getRemainingPieces().remove(pieceID);
        }
        System.out.println(board.getRemainingPieces());
    }


    public void createTableIfDoesntExist() {
        createTable.createTableIfDoesntExist();
    }

    public void getStatistics(int id) {
        leaderboard.loadStatistics(id);
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

    public boolean hasQuarto() {
        // TODO: hasQuarto
        /*if (hasquarto == true){
            player.setHasQuarto(true);
        } else {
            player.setHasQuarto(false);
        }*/
//        if (checkRows() == true || checkColumns() == true || checkDiagonals() == true) {
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }
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
        leaderboard.closeDb();
    }

    public void dataBaseInit() {
        leaderboard.connectToDb();
        leaderboard.getRecords();
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
