package main.java.model.board;

import java.util.ArrayList;
import java.util.Collections;

public class Pieces {
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
