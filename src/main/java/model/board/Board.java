package main.java.model.board;

import main.java.model.pieces.Pieces;

public class Board {
    private final int columns = 4;
    private final int rows = 4;
    private String[][] board = new String[columns][rows];
    private Pieces[] pieces = new Pieces[16];
    private int remainingPieces = 16;
    private int remainingSpots = 16;
    private int[] pieceStatus = new int[16];
    private int pieceInHand = 16;
    PieceStatus pStatus;

    //Getters
    public int getColumns() {
        return columns;
    }
    public int getRows() {
        return rows;
    }
    public String[][] getBoard() {
        return board;
    }
    public Pieces[] getPieces() {
        return pieces;
    }
    public int getRemainingPieces() {
        return remainingPieces;
    }
    public int getRemainingSpots() {
        return remainingSpots;
    }
    public int[] getPieceStatus() {
        return pieceStatus;
    }
    public int getPieceInHand() {
        return pieceInHand;
    }

    //Setters
    public void setBoard(String[][] board) {
        this.board = board;
    }
    public void setPieces(Pieces[] pieces) {
        this.pieces = pieces;
    }
    public void setRemainingPieces(int remainingPieces) {
        this.remainingPieces = remainingPieces;
    }
    public void setRemainingSpots(int remainingSpots) {
        this.remainingSpots = remainingSpots;
    }
    public void setPieceStatus(int[] pieceStatus) {
        this.pieceStatus = pieceStatus;
    }
    public void setPieceInHand(int pieceInHand) {
        this.pieceInHand = pieceInHand;
    }

}
