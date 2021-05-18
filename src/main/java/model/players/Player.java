package main.java.model.players;

/**
 * This is the main class of the players of the game, these classes are made to store data on these players, like:
 * Is it a computer, the name, the time played...
 * @version 1.0
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 */
public class Player {
    private boolean hasQuarto = false;

    //Setters
    public boolean setHasQuarto(boolean hasQuarto) {
        return this.hasQuarto = hasQuarto;
    }

    //Getters
    public boolean isHasQuarto() {
        return hasQuarto;
    }



}
