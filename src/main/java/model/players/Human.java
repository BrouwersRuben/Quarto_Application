package main.java.model.players;

public class Human extends Player {

    Player human = new Player();

    String name;
    protected int score;
    protected boolean startsFirst;
    //human.hasquarto;

    public Human() {

    }

    public Human(String name, int score, boolean startsFirst) {
        this.name = name;
        this.score = score;
        this.startsFirst = startsFirst;
    }

    //Getters
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public boolean isStartsFirst() {
        return startsFirst;     
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setStartsFirst(boolean startsFirst) {
        this.startsFirst = startsFirst;
    }


}
