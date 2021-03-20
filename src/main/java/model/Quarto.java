package main.java.model;

import main.java.model.board.Board;
import main.java.model.board.PieceStatus;
import main.java.model.pieces.Piece;

public class Quarto {
    protected boolean isRunning;
    protected int amountOfTurns;
    protected int gameTimerSeconds = 300 /*5 min*/;
    // private attributes
    Piece quartoPiece = new Piece();
    PieceStatus pieceStatus;
    Board board = new Board();

    public Quarto() {
// Constructor

    }

    // methods with business logic
    public void setInhand(Piece piece){
        if (piece.pieceStatus() == pieceStatus.OFF_BOARD.toString()){
            piece.setPieceStat(pieceStatus.IN_HAND.getCode());
            board.setRemainingPieces(-1);
            board.setRemainingSpots(+1);
        }
    }

    public void playPiece(Piece piece, int column, int row){
        if (board.getRemainingSpots()>= 0 && board.getRemainingSpots()< 16){
            if (piece.pieceStatus().equals(pieceStatus.OFF_BOARD.toString()) || piece.pieceStatus().equals(pieceStatus.IN_PLAY.toString())){
                //This should not be possible, but...
                System.out.println("There is a problem with the current piece");

            }
        }
    }

    public void startGameTimer() {
        while (gameTimerSeconds > 0) {
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
        if (gameTimerSeconds <= 0) {
            System.exit(0);
        }
    }

    public void resetVariables() {
        gameTimerSeconds = 300;
        //all the variables that have to be resetted after each turn.
    }

// needed getters and setters
    public int getGameTimerSeconds() {
        return gameTimerSeconds;
    }
    public int getAmountOfTurns() {
        return amountOfTurns;
    }

}

/*    public boolean pass(int piece) {
        if (piece < 0 || piece > 15) {
            System.out.println("Non existant");
            return false;
        }

        if (newBoard.getPieceStatus()[piece] == PieceStatus.OFF_BOARD.getCode()) {
            newBoard.setPieceInHand(piece);
            newBoard.getPieceStatus()[piece] = PieceStatus.IN_HAND.getCode();
            newBoard.setRemainingPieces(-1);
            return true;
        } else {
            System.out.printf("We could not pass piece #%d. The piece is %s\n", piece, ((newBoard.getPieceStatus()[piece] == PieceStatus.IN_HAND.getCode()) ? "in someones hand" : "already on the board"));
            return false;
        }
    }

    *//*
    // TODO: Has quarto method
    public boolean hasQuarto() {

    }
     *//*

    public void play(int piece) {
        if (quartoPiece.getPieceStat() != 1 || quartoPiece.getPieceStat() != 2) {
            //WIP
        }
    }

    public String showAvailablePieces() {
        for (int i = 0; i <= newBoard.getPieces().length; i++) {
            if (newBoard.getPieceStatus()[i] != PieceStatus.IN_HAND.getCode() || newBoard.getPieceStatus()[i] != PieceStatus.IN_PLAY.getCode()) {

            }
        }
        //ELSE?
        return "";
    }*/
