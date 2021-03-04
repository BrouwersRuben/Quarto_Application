package main.java.Model;

import main.java.Model.Board.Board;
import main.java.Model.Board.PStatus;
import main.java.Model.Pieces.Piece;

public class Quarto {
    // private attributes
    Board newBoard = new Board();
    Piece quartoPiece = new Piece();
    PStatus pieceStatus;

    public Quarto() {
// Constructor

    }

// methods with business logic
    public boolean pass(int piece){
            if (piece<0 || piece>15) {
                System.out.println( "Non existant" );
                return false;
            }

            if ( newBoard.getPieceStatus()[piece] == pieceStatus.OFF_BOARD.getCode()) {
                newBoard.setPieceInHand(piece);
                newBoard.getPieceStatus()[piece] = pieceStatus.IN_HAND.getCode();
                newBoard.setRemainingPieces(-1);
                return true;
            } else {
                System.out.printf( "We could not pass piece #%d. The piece is %s\n", piece, ((newBoard.getPieceStatus()[piece]==pieceStatus.IN_HAND.getCode()) ? "in someones hand" : "already on the board"));
                return false;
            }
    }

    public boolean hasQuarto(){

    }

    public void play(int piece){
        if (quartoPiece.getPieceStat() != 1 || quartoPiece.getPieceStat() != 2){

        }
    }

    public String showAvailablePieces(){
        return quartoPiece.pieceStatus();
    }



// needed getters and setters

}