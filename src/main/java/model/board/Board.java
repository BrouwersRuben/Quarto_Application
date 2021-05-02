package main.java.model.board;

import java.util.ArrayList;

public class Board {
    private ArrayList<Integer> piecesOnBoard = new ArrayList<>();
    private int[] pieceStatus = new int[16];
    private ArrayList<ArrayList<Integer>> usedTiles = new ArrayList<>();

    //Getters
    public int[] getPieceStatus() {
        return pieceStatus;
    }
    public ArrayList<Integer> getPiecesOnBoard() {
        return piecesOnBoard;
    }

    //Setters
    public void setPieceStatus(int[] pieceStatus) {
        this.pieceStatus = pieceStatus;
    }
    public void setPiecesOnBoard(int id) {
        piecesOnBoard.add(id);
    }

    // GETTERS AND SETTERS

    public ArrayList<ArrayList<Integer>> getUsedTiles() {
        return usedTiles;
    }
}
