package main.java.model.pieces;

import main.java.model.board.PieceStatus;

public class Piece {

    protected int pieceStat;
    PieceStatus pStatus;


    public String pieceStatus(){
        if (pieceStat == pStatus.IN_HAND.getCode()){
            return "The piece is in someones hand";
        } else if (pieceStat == pStatus.IN_PLAY.getCode()){
            return "The piece is already on the board";
        } else {
            return "the piece is free to play";
        }
    }

    public int getPieceStat() {
        return pieceStat;
    }
    public void setPieceStat(int pieceStat) {
        this.pieceStat = pieceStat;
    }
}
