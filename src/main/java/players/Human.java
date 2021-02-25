package main.java.players;

public class Human {
    protected String name;
    protected int score;
    protected boolean startsFirst;

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
    public void setScore(int score) {
        this.score = score;
    }
    public void setStartsFirst(boolean startsFirst) {
        this.startsFirst = startsFirst;
    }
}
