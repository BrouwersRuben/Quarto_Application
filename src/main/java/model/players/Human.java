package main.java.model.players;

public class Human extends Player {

    protected int score;
    protected boolean startsFirst;
    Player human = new Player();
    String name;
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

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isStartsFirst() {
        return startsFirst;
    }

    public void setStartsFirst(boolean startsFirst) {
        this.startsFirst = startsFirst;
    }


}
