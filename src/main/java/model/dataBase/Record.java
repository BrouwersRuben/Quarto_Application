package main.java.model.dataBase;

public class Record {
    private String username;
    private int score;
    private String date;

    public Record(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}

