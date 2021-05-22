package main.java.model.dataBase;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * The class which saves turn based statistics for the end screen and the advanced statistics screen.
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 * @version 1.0
 */
public class TurnStatistics {
    private int id;
    private int turn;
    private Timestamp turnStartTime;
    private Timestamp turnEndTime;
    private long timeDifference;
    private long score;
    private ArrayList<TurnStatistics> turnStatsArray = new ArrayList<>();

    public TurnStatistics() {
    }

    public TurnStatistics(int id, int turn, Timestamp turnStartTime, Timestamp turnEndTime, long timeDifference, long score) { // removed , int score_for_turn, will make calculation in sql
        this.id = id;
        this.turn = turn;
        this.turnStartTime = turnStartTime;
        this.turnEndTime = turnEndTime;
        this.timeDifference = timeDifference;
        this.score = score;
    }

    public void createTurnData(int id, int turn, Timestamp turnStartTime, Timestamp turnEndTime) {
        long difference = turnEndTime.getTime()-turnStartTime.getTime();
        long score = calculateScoreForMove(difference);
        TurnStatistics statistics = new TurnStatistics(id, turn, turnStartTime, turnEndTime, difference, score);
        turnStatsArray.add(statistics);

    }

    // TODO: Make this more advanced, combined with AI rule based potentially.
    public long calculateScoreForMove(long difference) {
        return difference;
    }



    // Should create a method that puts the objects into the turnArray arraylist.

    public Timestamp createTimestamp() { return new Timestamp(System.currentTimeMillis()); }

    public int getId() {
        return id;
    }

    public int getTurn() {
        return turn;
    }

    public long getScore() { return score; }

    public Timestamp getTurnStartTime() { return turnStartTime; }

    public Timestamp getTurnEndTime() { return turnEndTime; }

    public long getTimeDifference() { return timeDifference; }

    public ArrayList<TurnStatistics> getTurnStatsArray() { return turnStatsArray; }
}
