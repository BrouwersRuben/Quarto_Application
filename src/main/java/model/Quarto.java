package main.java.model;

import javafx.scene.control.Label;
import main.java.model.board.Board;
import main.java.model.board.PieceStatus;
import main.java.model.pieces.Piece;

public class Quarto {
    protected boolean isRunning;
    protected int amountOfTurns;

    // private attributes
    Piece quartoPiece = new Piece();
    PieceStatus pieceStatus;
    Board board = new Board();

    public Quarto() {
// Constructor

    }

    // methods with business logic
   /* public void setInhand(Piece clickedPiece){
        if (clickedPiece.pieceStatus() == pieceStatus.OFF_BOARD.toString() || clickedPiece.pieceStatus() == pieceStatus.OFF_BOARD.toString()){
            clickedPiece.setPieceStat(pieceStatus.IN_HAND.getCode());
            board.setRemainingPieces(-1);
            board.setRemainingSpots(+1);
        }
    }

    public void playPiece(Piece piece, int startColumn, int startRow, int endColumn, int endRow){
        if (board.getRemainingSpots()>= 0 && board.getRemainingSpots()< 16){
            if (piece.pieceStatus().equals(pieceStatus.OFF_BOARD.toString()) || piece.pieceStatus().equals(pieceStatus.IN_PLAY.toString())){
                //This should not be possible, but...
                System.out.println("There is a problem with the current piece");
                return;
            }

        }
    }*/

    public void gameOver(){
        // TODO: Game Over method, timer ran out
    }

// needed getters and setters
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
