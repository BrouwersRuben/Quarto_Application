package main.java.model;

import main.java.model.board.Board;
import main.java.model.board.PieceStatus;
import main.java.model.dataBase.Leaderboard;
import main.java.model.dataBase.saveAndLoad;
import main.java.model.pieces.Piece;
import main.java.model.players.Human;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.Collections;

public class Quarto {
    protected boolean isRunning;
    protected int amountOfTurns;

    public double width = 1158;


    // private attributes
    private Leaderboard leaderboard = new Leaderboard();
    private Board newBoard = new Board();
    private Piece quartoPiece = new Piece();
    private PieceStatus pieceStatus;
    private saveAndLoad createTable = new saveAndLoad();
    private Board board = new Board();
    private Human player = new Human();
    //has to have a human, and a bridge between the model and the view

    public Quarto() {
// Constructor

    }
    public void createTableIfDoesntExist(){
        createTable.createTableIfDoesntExist();
    }

    public void getStatistics(int id){
        leaderboard.getStatistics(id);
    }

    public String getRecords(int i){
        return String.format("%d. %s - %d", i+1, Leaderboard.records[i].getUsername(), Leaderboard.records[i].getScore());
    }

    public String getRecordsUserName(int i){
        return Leaderboard.records[i].getUsername();
    }

    public int getRecordsUserScore(int i){
        return Leaderboard.records[i].getScore();
    }

    public Long getAverageTime(){
        return Leaderboard.averageTime;
    }

    public int getFastestMove(){
        return Collections.min(Leaderboard.turnStats);
    }

    public int getSlowestMove(){
        return Collections.max(Leaderboard.turnStats);
    }

    public int getRecordsUserId(int i){
        return Leaderboard.records[i].getId();
    }

    public int getTurnStatsSize(){
        return Leaderboard.turnStats.size();
    }

    public int getTurnstats(int i){
        return Leaderboard.turnStats.get(i);
    }

    //Business logic

    public String checkIfTutorialExist(Path path){
        if (Files.exists(path) && Files.isRegularFile(path)){
            return "The File exist and is a regular file.";
        } else if (!Files.exists(path)){
            return "The File does not exist, or the path you have given is wrong.";
        } else {
            return "Error";
        }
    }

    public String readTutorialFile(Path path){
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
        } catch(FileNotFoundException e){
            return "An error occurred!";
        }
    }

    public String getUserName(){
        return player.getName();
    }

    public void setUserName(String name){
        player.setName(name);
    }

    public boolean hasQuarto(){
        // TODO: Make the hasQuarto
        return false;
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

    public void gameOver(){
        // TODO: Game Over method, timer ran out
    }

    public void addToListOnBoard(Integer pieceId){
        board.setPiecesOnBoard(pieceId);
    }

    public boolean isUnique(Integer pieceId){
        for(int i = 0; i < board.getPiecesOnBoard().size(); i++){
            if (pieceId.equals(board.getPiecesOnBoard().get(i))) {
                return false;
            }
        }
        return true;
    }

    public void closeDB(){
        leaderboard.closeDb();
    }

    public void dataBaseInit(){
        leaderboard.connectToDb();
        leaderboard.getRecords();
    }


}
