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



    public void fillRemainingPieces() {
        for (int i=1; i<17; i++) {
            remainingPieces.add(i);
        }
        System.out.println("\nCreating new remaining pieces arraylist\n"+remainingPieces);
    }

    public void fillPieces() {
        pieces.add(new Pieces("media/images/1.png", pieceColor.BLUE, pieceShape.ROUND, pieceFill.FILLED, pieceLength.SHORT));
        pieces.add(new Pieces("media/images/2.png", pieceColor.BLUE, pieceShape.ROUND, pieceFill.HOLLOW, pieceLength.SHORT));
        pieces.add(new Pieces("media/images/3.png", pieceColor.BLUE, pieceShape.ROUND, pieceFill.FILLED, pieceLength.TALL));
        pieces.add(new Pieces("media/images/4.png", pieceColor.BLUE, pieceShape.ROUND, pieceFill.HOLLOW, pieceLength.TALL));
        pieces.add(new Pieces("media/images/5.png", pieceColor.BLUE, pieceShape.TRIANGLE, pieceFill.FILLED, pieceLength.SHORT));
        pieces.add(new Pieces("media/images/6.png", pieceColor.BLUE, pieceShape.TRIANGLE, pieceFill.HOLLOW, pieceLength.SHORT));
        pieces.add(new Pieces("media/images/7.png", pieceColor.BLUE, pieceShape.TRIANGLE, pieceFill.FILLED, pieceLength.TALL));
        pieces.add(new Pieces("media/images/8.png", pieceColor.BLUE, pieceShape.TRIANGLE, pieceFill.HOLLOW, pieceLength.TALL));
        pieces.add(new Pieces("media/images/9.png", pieceColor.GREEN, pieceShape.ROUND, pieceFill.FILLED, pieceLength.SHORT));
        pieces.add(new Pieces("media/images/10.png", pieceColor.GREEN, pieceShape.ROUND, pieceFill.HOLLOW, pieceLength.SHORT));
        pieces.add(new Pieces("media/images/11.png", pieceColor.GREEN, pieceShape.ROUND, pieceFill.FILLED, pieceLength.TALL));
        pieces.add(new Pieces("media/images/12.png", pieceColor.GREEN, pieceShape.ROUND, pieceFill.HOLLOW, pieceLength.TALL));
        pieces.add(new Pieces("media/images/13.png", pieceColor.GREEN, pieceShape.TRIANGLE, pieceFill.FILLED, pieceLength.SHORT));
        pieces.add(new Pieces("media/images/14.png", pieceColor.GREEN, pieceShape.TRIANGLE, pieceFill.HOLLOW, pieceLength.SHORT));
        pieces.add(new Pieces("media/images/15.png", pieceColor.GREEN, pieceShape.TRIANGLE, pieceFill.FILLED, pieceLength.TALL));
        pieces.add(new Pieces("media/images/16.png", pieceColor.GREEN, pieceShape.TRIANGLE, pieceFill.HOLLOW, pieceLength.TALL));
    }

    public ArrayList<Integer> getRemainingPieces() {
        return remainingPieces;
    }

    public ArrayList<Pieces> getPieces() { return pieces; }

    public String getPlaceHolder() { return placeHolder; }
}
