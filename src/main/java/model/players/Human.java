package main.java.model.players;

import java.sql.Timestamp;

public class Human extends Player {

    private String name;
    private int score;
    private int turn;
    private final int difficulty = 0;
    private boolean hasQuarto;
    private boolean playerTurn;
    private Timestamp dateStarted;
    private boolean firstMove;


    public Human() {

    }

    public Human(String name) {
        this.name = name;
    }



    //Getters
    public String getName() { return name; }
    public int getScore() { return score; }
    public int getTurn() { return turn; }
    public boolean getPlayerTurn() {
        return playerTurn;
    }
    public int getDifficulty() { return difficulty; }
    public boolean isHasQuarto() { return hasQuarto; }
    public Timestamp getDateStarted() { return dateStarted; }
    public boolean isFirstMove() { return firstMove; }

    //Setters
    public void setName(String name) { this.name = name; }
    public void setScore(int score) { this.score = score; }
    public void setTurn(int turn) { this.turn = turn; }
    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
    public void setHasQuarto(boolean hasQuarto) { this.hasQuarto = hasQuarto; }
    public void setDateStarted(Timestamp dateStarted) { this.dateStarted = dateStarted; }
    public void setFirstMove(boolean firstMove) { this.firstMove = firstMove; }
}
