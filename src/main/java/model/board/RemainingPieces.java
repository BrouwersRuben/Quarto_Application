package main.java.model.board;

import java.util.ArrayList;

/**
 * This class keeps track of the remaining pieces from which
 * the players can choose to make their next move.
 * @version 1.0
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 */
public class RemainingPieces {
    private final ArrayList<Integer> remainingPieces = new ArrayList<Integer>();

    public void fillRemainingPieces() {
        for (int i=1; i<17; i++) {
            remainingPieces.add(i);
        }
        System.out.println("\nCreating new remaining pieces arraylist\n"+remainingPieces);
    }

    public ArrayList<Integer> getRemainingPieces() {
        return remainingPieces;
    }
}
