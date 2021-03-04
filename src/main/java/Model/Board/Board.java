package main.java.Model.Board;

import main.java.Model.Pieces.Pieces;

public class Board {
    private final int columns = 4;
    private final int rows = 4;
    private String[][] board = new String[columns][rows];
    private main.java.Model.Pieces.Pieces[] pieces = new Pieces[16];
    private int remainingPieces = 16;
    private int remainingSpots = 16;
    private int[] pieceStatus = new int[16];
    private int pieceInHand = 16;
    private final int inPlay = 1, offBoard = 0, inHand = 2;

    //Getters
    public String[][] getBoard() {
        return board;
    }
    public Pieces[] getPieces() {
        return pieces ;
    }
    public int getRemainingPieces() {
        return remainingPieces;
    }
    public int getRemainingSpots() {
        return remainingSpots;
    }

    //Setters
    public void setBoard(String[][] board) {
        this.board = board;
    }
    public void setPeice(Pieces[] piece) {
        this.pieces = piece ;
    }
    public void setRemainingPieces(int remainingPieces) {
        this.remainingPieces = remainingPieces;
    }
    public void setRemainingSpots(int remainingSpots) {
        this.remainingSpots = remainingSpots;
    }

    //GameMethods
    public boolean pass(int piece){
        if (piece<0 || piece>15) {
            System.out.println( "Non existant" );
            return false;
        }

        if ( pieceStatus[piece] == offBoard ) {
            pieceInHand = piece;
            pieceStatus[piece] = inHand;
            remainingPieces--;

            return true;
        }
        else {
            System.out.printf( "We could not pass piece #%d. The piece is %s\n", piece, ((pieceStatus[piece]==inHand) ? "in someones hand" : "already on the board"));
            return false;
        }
    }
}
