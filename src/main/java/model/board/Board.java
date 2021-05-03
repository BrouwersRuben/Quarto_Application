package main.java.model.board;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private ArrayList<Integer> piecesOnBoard = new ArrayList<>();
    private int[] pieceStatus = new int[16];
    private ArrayList<ArrayList<Integer>> usedTiles = new ArrayList<>();

    // 16n's - 0's representing an empty board, if a piece is added n is changed to 1
    private ArrayList<Integer> boardStatus = new ArrayList<>(Collections.nCopies(16,0));

    // winning lines which we'll compare with the boardStatus

    private final ArrayList<ArrayList<Integer>> winningLines = new ArrayList<>();


    public void fillWinningLines() {
        for (String line : lines) {
            ArrayList<Integer> temporary = new ArrayList<Integer>();
            for (int j = 0; j < line.length(); j++) {
                if (Integer.parseInt(String.valueOf(line.charAt(j))) == 1) {

                    temporary.add(j);
                }
            }
            winningLines.add(temporary);
        }

        System.out.println("Winning lines: \n"+winningLines);
    }


    String lines[] = {
            // diagonals
            "1000010000100001",
            "0001001001001000",
            // rows
            "1111000000000000",
            "0000111100000000",
            "0000000011110000",
            "0000000000001111",
            // columns
            "1000100010001000",
            "0100010001000100",
            "0010001000100010",
            "0001000100010001"
};


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

    public ArrayList<Integer> getBoardStatus() {
        return boardStatus;
    }

    public ArrayList<ArrayList<Integer>> getWinningLines() {
        return winningLines;
    }


}
