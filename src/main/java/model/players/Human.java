package main.java.model.players;

public class Human extends Player {

    private String name;
    private int score;
    private int turn;
    private final int difficulty = 0;
    private boolean hasQuarto = false;
    private boolean playerTurn;


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


    //Setters
    public void setName(String name) { this.name = name; }
    public void setScore(int score) { this.score = score; }
    public void setTurn(int turn) { this.turn = turn; }
    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
    public boolean setHasQuarto(boolean hasQuarto) { return this.hasQuarto = hasQuarto; }




}
