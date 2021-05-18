package main.java.model.players;

public class Human extends Player {

    protected int score;
    protected boolean playerTurn;
    Player human = new Player();
    String name;
    //human.hasquarto;


    public Human() {

    }

    public Human(String name) {
        this.name = name;
    }

    //Getters
    public String getName() { return name; }
    public int getScore() { return score; }
    public boolean getPlayerTurn() {
        return playerTurn;
    }

    //Setters
    public void setName(String name) { this.name = name; }
    public void setScore(int score) { this.score = score; }
    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }





}
