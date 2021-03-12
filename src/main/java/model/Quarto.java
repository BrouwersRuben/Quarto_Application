package main.java.model;

import main.java.model.board.Board;
import main.java.model.board.PStatus;
import main.java.model.pieces.Piece;

public class Quarto {
    // private attributes
    Board newBoard = new Board();
    Piece quartoPiece = new Piece();
    PStatus pieceStatus;
    protected boolean isRunning;
    protected int amountOfTurns;
    protected int gameTimerSeconds = 300 /*5 min*/;

    public Quarto() {
// Constructor

    }

    // methods with business logic
    public boolean pass(int piece) {
        if (piece < 0 || piece > 15) {
            System.out.println("Non existant");
            return false;
        }

        if (newBoard.getPieceStatus()[piece] == pieceStatus.OFF_BOARD.getCode()) {
            newBoard.setPieceInHand(piece);
            newBoard.getPieceStatus()[piece] = pieceStatus.IN_HAND.getCode();
            newBoard.setRemainingPieces(-1);
            return true;
        } else {
            System.out.printf("We could not pass piece #%d. The piece is %s\n", piece, ((newBoard.getPieceStatus()[piece] == pieceStatus.IN_HAND.getCode()) ? "in someones hand" : "already on the board"));
            return false;
        }
    }

    /*
    public boolean hasQuarto() {

    }
     */

    public void play(int piece) {
        if (quartoPiece.getPieceStat() != 1 || quartoPiece.getPieceStat() != 2) {
            //WIP
        }
    }

    public String showAvailablePieces() {
        for (int i = 0; i <= newBoard.getPieces().length; i++) {
            if (newBoard.getPieceStatus()[i] != PStatus.IN_HAND.getCode() || newBoard.getPieceStatus()[i] != PStatus.IN_PLAY.getCode()) {
                return String.format("%s", "The available pieces will be shown here");
            }
        }
        //ELSE?
        return "";
    }

    private void startGameTimer() {
            while(gameTimerSeconds > 0) {
                try {
                    Thread.sleep(1000);
                    gameTimerSeconds = gameTimerSeconds - 1;
                    //update the timer
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //maybe quit the game when the user is afk for to long.
            //reset the timer after every move.
            if(gameTimerSeconds <= 0) {
                System.exit(0);
            }
    }

    private void resetVariables(){
        gameTimerSeconds = 300;
        //all the variables that have to be resetted after each turn.
    }

// needed getters and setters

    public int getGameTimer() {
        return gameTimer;
    }
    public int getAmountOfTurns() {
        return amountOfTurns;
    }

}