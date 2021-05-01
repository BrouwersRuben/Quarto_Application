package main.java.model.board;

import java.util.ArrayList;

public class Board {
    private ArrayList<Integer> piecesOnBoard = new ArrayList<>();
    private int[] pieceStatus = new int[16];
    private ArrayList<Integer> remainingPieces = new ArrayList<Integer>(); // TODO: PLACE IT IN THE CORRECT PLACE. maybe Board.class ?
    private ArrayList<ArrayList<Integer>> usedTiles = new ArrayList<>();
    private ArrayList<String> remainingTiles = new ArrayList<String>();

//    public void fillNewTileStatus() { // 16 tiles, 2 values for (1 - status, 2 - piece ID)
//        for (int i=0; i<16; i++) {
//            usedTiles.add(new ArrayList<>());
//            for (int j=0; j<2; j++) {
//                usedTiles.get(i).add(j, -1);
//            }
//        }
//        System.out.println("\nCreating new board tile arraylist");
//        System.out.println("Size: "+ usedTiles.size());
//        System.out.println("Size of first array: "+ usedTiles.get(0).size());
//        System.out.println(usedTiles);
//        setUsedTiles(usedTiles);
//    }

    public void fillRemainingPieces() {
        for (int i=1; i<17; i++) {
            remainingPieces.add(i);
        }
        System.out.println("\nCreating new remaining pieces arraylist\n"+remainingPieces);
    }






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

    public ArrayList<Integer> getRemainingPieces() {
        return remainingPieces;
    }

    public void setRemainingPieces(ArrayList<Integer> remainingPieces) {
        this.remainingPieces = remainingPieces;
    }

    public ArrayList<ArrayList<Integer>> getUsedTiles() {
        return usedTiles;
    }

    public void setUsedTiles(ArrayList<ArrayList<Integer>> usedTiles) {
        this.usedTiles = usedTiles;
    }

    public ArrayList<String> getRemainingTiles() {
        return remainingTiles;
    }

    public void setRemainingTiles(ArrayList<String> remainingTiles) {
        this.remainingTiles = remainingTiles;
    }
}
