package main.java.model.pieces;

import main.java.model.board.PieceStatus;

public class Piece {

    protected int pieceStat;
    PieceStatus pStatus;


    public int pieceStatus(){
        if (pieceStat == pStatus.IN_HAND.getCode()){
            return 0;
        } else if (pieceStat == pStatus.IN_PLAY.getCode()){
            return 1;
        } else {
            return 2;
        }
    }

    public int getPieceStat() {
        return pieceStat;
    }
    public void setPieceStat(int pieceStat) {
        this.pieceStat = pieceStat;
    }
}
