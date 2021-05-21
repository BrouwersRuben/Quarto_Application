package main.java.model.dataBase;

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
    private Timestamp turn_start_time;
    private Timestamp turn_end_time;
    private double score;
    private ArrayList<TurnStatistics> turnStatsArray = new ArrayList<>();
    private final GameStatistics gameStatistics = new GameStatistics();

    public TurnStatistics() {
    }

    public TurnStatistics(int turn, Timestamp turn_start_time, Timestamp turn_end_time, double score) { // removed , int score_for_turn, will make calculation in sql
        this.id = gameStatistics.getGameID(); // TODO: is this allowed ? otherwise I would be calling getGameID() after every move, when it's not really necessary
        this.turn = turn;
        this.turn_start_time = turn_start_time;
        this.turn_end_time = turn_end_time;
        this.score = score;
    }

    public void createTurnData(int turnID, Timestamp turnStart, Timestamp turnEnd) {
        long score = calculateScoreForMove(turnStart, turnEnd);
        TurnStatistics statistics = new TurnStatistics(turnID, turnStart, turnEnd, score);
        turnStatsArray.add(statistics);

    }

    // TODO: Make this more advanced, combined with AI rule based potentially.
    public long calculateScoreForMove(Timestamp turnStart, Timestamp turnEnd) {
        return (long) ((turnEnd.getTime()-turnStart.getTime())*0.5);
    }



    // Should create a method that puts the objects into the turnArray arraylist.

    public Timestamp setTimeStamp() { return new Timestamp(System.currentTimeMillis()); }

    public int getId() {
        return id;
    }

    public int getTurn() {
        return turn;
    }

    public double getScore() { return score; }

    public Timestamp getTurn_start_time() { return turn_start_time; }

    public Timestamp getTurn_end_time() { return turn_end_time; }

    public ArrayList<TurnStatistics> getTurnStatsArray() { return turnStatsArray; }
}
