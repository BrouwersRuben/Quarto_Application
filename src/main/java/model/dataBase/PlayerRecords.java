package main.java.model.dataBase;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Class to save a record by a specific player or to override an existing one with a better one.
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 * @version 1.0
 */
public class PlayerRecords {
    private int id;
    private String username;
    private int score;
    private int playerSelected;

    public PlayerRecords() {
    }

    public PlayerRecords(int id, String username, int score) {
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

    public int getPlayerSelected() { return playerSelected; }

    public void setPlayerSelected(int playerSelected) { this.playerSelected = playerSelected; }
}

