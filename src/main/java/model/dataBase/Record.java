package main.java.model.dataBase;

public class Record {
    private int id;
    private String username;
    private int score;

    public Record(int id, String username, int score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}

