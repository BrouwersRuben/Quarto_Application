package main.java.model.board;

import main.java.model.pieces.*;

import java.util.ArrayList;

/**
 * This class keeps track of the remaining pieces from which
 * the players can choose to make their next move.
 * @version 1.0
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 */
public class RemainingPieces {
    public static String placeHolder = "media/images/0.png"; // TODO: I DIDN'T WANT TO MESS WITH THIS BEING A PRIVATE FINAL, IT'S A NUISANCE
    private final ArrayList<Integer> remainingPieces = new ArrayList<Integer>();
    private final ArrayList<Pieces> pieces = new ArrayList<Pieces>();
    ArrayList<Integer> remainingPiecesClone = new ArrayList<Integer>();



    public void fillRemainingPieces() {
        for (int i=1; i<17; i++) {
            remainingPieces.add(i);
        }
        System.out.println("\nCreating new remaining pieces arraylist\n"+remainingPieces);
    }

    public void fillPieces() {
        pieces.add(new Pieces("media/images/1.png", PieceColor.BLUE, PieceShape.ROUND, PieceFill.FILLED, PieceLength.SHORT));
        pieces.add(new Pieces("media/images/2.png", PieceColor.BLUE, PieceShape.ROUND, PieceFill.HOLLOW, PieceLength.SHORT));
        pieces.add(new Pieces("media/images/3.png", PieceColor.BLUE, PieceShape.ROUND, PieceFill.FILLED, PieceLength.TALL));
        pieces.add(new Pieces("media/images/4.png", PieceColor.BLUE, PieceShape.ROUND, PieceFill.HOLLOW, PieceLength.TALL));
        pieces.add(new Pieces("media/images/5.png", PieceColor.BLUE, PieceShape.TRIANGLE, PieceFill.FILLED, PieceLength.SHORT));
        pieces.add(new Pieces("media/images/6.png", PieceColor.BLUE, PieceShape.TRIANGLE, PieceFill.HOLLOW, PieceLength.SHORT));
        pieces.add(new Pieces("media/images/7.png", PieceColor.BLUE, PieceShape.TRIANGLE, PieceFill.FILLED, PieceLength.TALL));
        pieces.add(new Pieces("media/images/8.png", PieceColor.BLUE, PieceShape.TRIANGLE, PieceFill.HOLLOW, PieceLength.TALL));
        pieces.add(new Pieces("media/images/9.png", PieceColor.GREEN, PieceShape.ROUND, PieceFill.FILLED, PieceLength.SHORT));
        pieces.add(new Pieces("media/images/10.png", PieceColor.GREEN, PieceShape.ROUND, PieceFill.HOLLOW, PieceLength.SHORT));
        pieces.add(new Pieces("media/images/11.png", PieceColor.GREEN, PieceShape.ROUND, PieceFill.FILLED, PieceLength.TALL));
        pieces.add(new Pieces("media/images/12.png", PieceColor.GREEN, PieceShape.ROUND, PieceFill.HOLLOW, PieceLength.TALL));
        pieces.add(new Pieces("media/images/13.png", PieceColor.GREEN, PieceShape.TRIANGLE, PieceFill.FILLED, PieceLength.SHORT));
        pieces.add(new Pieces("media/images/14.png", PieceColor.GREEN, PieceShape.TRIANGLE, PieceFill.HOLLOW, PieceLength.SHORT));
        pieces.add(new Pieces("media/images/15.png", PieceColor.GREEN, PieceShape.TRIANGLE, PieceFill.FILLED, PieceLength.TALL));
        pieces.add(new Pieces("media/images/16.png", PieceColor.GREEN, PieceShape.TRIANGLE, PieceFill.HOLLOW, PieceLength.TALL));
    }

    public ArrayList<Integer> getRemainingPieces() {
        return remainingPieces;
    }

    public ArrayList<Pieces> getPieces() { return pieces; }



    public String getPlaceHolder() { return placeHolder; }


    public ArrayList<Integer> getRemainingPiecesClone() { return remainingPiecesClone; }

    public void setRemainingPiecesClone(ArrayList<Integer> remainingPiecesClone) { this.remainingPiecesClone = (ArrayList<Integer>) remainingPiecesClone.clone(); }
}
