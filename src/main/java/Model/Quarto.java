package main.java.Model;

import main.java.Model.Board.Board;
import main.java.Model.Board.PStatus;

public class Quarto {
    // private attributes
    Board newBoard = new Board();
    PStatus pieceStatus;

    public Quarto() {
// Constructor

    }

// methods with business logic
    public boolean play(int piece){
        return true;
    }


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
        return ;
}



// needed getters and setters

}