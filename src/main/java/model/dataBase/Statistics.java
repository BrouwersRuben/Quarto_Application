package main.java.model.dataBase;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Statistics {
    private int id;
    private int turn;
    private Timestamp turn_start_time;
    private Timestamp turn_end_time;
    private ArrayList<Statistics> turnArray = new ArrayList<>();


    public Statistics(int id, int turn, Timestamp turn_start_time, Timestamp turn_end_time) { // removed , int score_for_turn, will make calculation in sql
        this.id = id;
        this.turn = turn;
        this.turn_start_time = turn_start_time;
        this.turn_end_time = turn_end_time;
    }

    public Statistics(Timestamp turn_start_time, Timestamp turn_end_time) {
        this.turn_start_time = turn_start_time;
        this.turn_end_time = turn_end_time;
    }

    public Statistics(long turnDifference) {
    }

    public Timestamp setTimeStamp() { return new Timestamp(System.currentTimeMillis()); }

    public static long getTurnDifference(Timestamp start, Timestamp end) {
        return start.getTime() - end.getTime();
    }

    public int getId() {
        return id;
    }

    public int getTurn() {
        return turn;
    }

    public Timestamp getTurn_start_time() { return turn_start_time; }

    public Timestamp getTurn_end_time() { return turn_end_time; }

    public void setTurn_start_time(Timestamp turn_start_time) { this.turn_start_time = turn_start_time; }

    public void setTurn_end_time(Timestamp turn_end_time) { this.turn_end_time = turn_end_time; }
}
