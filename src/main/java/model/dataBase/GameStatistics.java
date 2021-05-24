package main.java.model.dataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is used for the leaderboard screen and the win/loss screen to
 * display basic and advanced statistics.
 *
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 * @version 1.0
 */
public class GameStatistics extends Database { // Used for retrieving the leaderboard

    private List<Double> timeSpentOnTurn = new ArrayList<>();
    private List<PlayerRecords> records = new ArrayList<>();
    private long playerScore;
    private double playerAverageMoveTime;
    private double playerFastestMoveTime;
    private double playerSlowestMoveTime;


    public int getGameID() {

        int gameID = 69;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM (SELECT ID FROM game_data ORDER BY ID DESC) WHERE ROWNUM=1");

            if (resultSet.next()) {
                gameID = resultSet.getInt(1) + 1;
            } else { // for when there's no data
                gameID = 1;
            }

            System.out.println(gameID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return gameID;
    }

    public void saveGame(ArrayList<TurnData> array, int gameID, String username, int difficulty, boolean hasQuarto, Timestamp dateStarted) {

        try {

            System.out.println("Saving this shit game.");
            Statement statement = connection.createStatement();
            PreparedStatement gameStatistics = connection.prepareStatement("INSERT INTO game_statistics (id, turn, turn_start_time, turn_end_time, time_spent, score_for_turn) VALUES (?,?,?,?,?,?)");
            PreparedStatement gameData = connection.prepareStatement("INSERT INTO game_data (id, username, date_started, score, turns, time_played, game_difficulty, has_quarto) VALUES (?,?,?,?,?,?,?,?)");


            ResultSet totalScore = statement.executeQuery("SELECT SUM(score_for_turn) AS total_score FROM game_statistics WHERE ID='" + gameID + "'");
            totalScore.next();
            ResultSet timePlayed = statement.executeQuery("SELECT turn_start_time, turn_end_time, turn_end_time-turn_start_time AS difference FROM game_statistics");
            timePlayed.next();

            // Game data (for... game data?)
            playerScore = 0;
            double timeDifference = 0;

            for (int i = 0; i < array.size(); i++) {
                playerScore += array.get(i).getScore();
                timeDifference += array.get(i).getTimeDifference();
            }

            gameData.setInt(1, gameID);
            gameData.setString(2, username);
            gameData.setTimestamp(3, dateStarted);
            gameData.setLong(4, playerScore);
            gameData.setInt(5, array.size());
            gameData.setDouble(6, timeDifference);
            gameData.setInt(7, difficulty);

            System.out.println(hasQuarto);
            if (hasQuarto) {
                gameData.setInt(8, 1);
            } else {
                gameData.setInt(8, 0);
            }

            gameData.executeQuery();


            // Game statistics (for turns)
            for (int i = 0; i < array.size(); i++) {

                gameStatistics.setInt(1, gameID);
                gameStatistics.setInt(2, array.get(i).getTurn());
                gameStatistics.setTimestamp(3, array.get(i).getTurnStartTime());
                gameStatistics.setTimestamp(4, array.get(i).getTurnEndTime());
                gameStatistics.setDouble(5, array.get(i).getTimeDifference());
                gameStatistics.setLong(6, array.get(i).getScore());

                gameStatistics.executeQuery();

            }

            getStatistics(gameID);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method to retrieve detailed statistics about the players from the top 5 leaderboard
     *
     * @param id Unique game identifier
     */
    public void getStatistics(int id) {

        try {
            System.out.println("Entering this shit thing.");
            Statement statement = connection.createStatement();
            ResultSet turnCount = statement.executeQuery("SELECT COUNT(*) FROM game_statistics WHERE id = '"+id+"'");
            turnCount.next();
            ResultSet average = statement.executeQuery("SELECT (SUM(time_spent)/'"+turnCount.getInt(1)+"') FROM game_statistics WHERE ID = "+id);

            while (average.next()) {
                playerAverageMoveTime = average.getInt(1);
            }

            ResultSet timeSpent = statement.executeQuery("SELECT time_spent FROM game_statistics WHERE id = "+id);

            // Resetting array in case a instance is requested.
            timeSpentOnTurn.clear();

            while (timeSpent.next()) {
                System.out.println("Here is the request: "+timeSpent.getDouble(1));
                timeSpentOnTurn.add(timeSpent.getDouble(1));
            }
            System.out.println("turn stats: "+ timeSpentOnTurn);
            playerFastestMoveTime = Collections.min(timeSpentOnTurn);
            playerSlowestMoveTime = Collections.max(timeSpentOnTurn);

            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method for retrieving the top 5 leaderboard.
     */
    public void getLeaderboard() {
        try {

            Statement statement = connection.createStatement();

            ResultSet rows = statement.executeQuery("SELECT * FROM game_data" +
                    " ORDER BY score DESC");

            int i = 0;
            records.clear();
            while (rows.next() && i < 5) {
                records.add(new PlayerRecords(rows.getInt("id"), rows.getString("username"), rows.getInt("score")));
                i++;
            }
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Double> getTimeSpentOnTurn() { return timeSpentOnTurn; }

    public List<PlayerRecords> getRecords() { return records; }

    public double getPlayerAverageMoveTime() { return playerAverageMoveTime; }

    public double getPlayerFastestMoveTime() { return playerFastestMoveTime; }

    public double getPlayerSlowestMoveTime() { return playerSlowestMoveTime; }

    public long getPlayerScore() { return playerScore; }
}



