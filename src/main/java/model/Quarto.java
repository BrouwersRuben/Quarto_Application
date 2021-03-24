package main.java.model;

import main.java.model.board.Board;
import main.java.model.pieces.Piece;
import main.java.model.players.Human;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.TreeSet;

public class Quarto {
    protected boolean isRunning;
    protected int amountOfTurns;

    // private attributes
    private Piece quartoPiece = new Piece();
    private Board board = new Board();
    private Human player = new Human();
    //has to have a human, and a bridge between the model and the view

    public Quarto() {
// Constructor

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

// needed getters and setters
    public int getAmountOfTurns() {
        return amountOfTurns;
    }

}
