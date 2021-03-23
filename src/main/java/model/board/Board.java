package main.java.model.board;

import java.util.ArrayList;

public class Board {
    private final int columns = 4;
    private final int rows = 4;
    private ArrayList<Integer> piecesOnBoard = new ArrayList<>();
    private int remainingPieces = 16;
    private int remainingSpots = 0;
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
    public ArrayList<Integer> getPiecesOnBoard() {
        return piecesOnBoard;
    }

    //Setters
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

    public void setPiecesOnBoard(int id) {
        piecesOnBoard.add(id);
    }
}
