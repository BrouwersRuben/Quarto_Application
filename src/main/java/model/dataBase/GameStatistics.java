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

    private List<Double> playerTimeSpentOnTurn = new ArrayList<>();
    private List<Double> overallTimeSpentOnTurn = new ArrayList<>();
    private List<PlayerRecords> records = new ArrayList<>();
    private String playerName;
    private int turn;
    private long playerScore;
    private double allGameAverageScore;
    private double allGameAverageTimePerTurn;
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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return gameID;
    }

    public void saveGame(ArrayList<TurnData> array, int gameID, String username, int difficulty, boolean hasQuarto, Timestamp dateStarted) {

        try {

            System.out.println("Saving this shit game.");
            PreparedStatement gameStatistics = connection.prepareStatement("INSERT INTO game_statistics (id, turn, turn_start_time, turn_end_time, time_spent, score_for_turn) VALUES (?,?,?,?,?,?)");
            PreparedStatement gameData = connection.prepareStatement("INSERT INTO game_data (id, username, date_started, score, turns, time_played, game_difficulty, has_quarto) VALUES (?,?,?,?,?,?,?,?)");

            // Game data (for... game data?)
            playerName = username;
            playerScore = 0;
            double timeDifference = 0;

            for (TurnData turnData : array) {
                playerScore += turnData.getScore();
                timeDifference += turnData.getTimeDifference();
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
            for (TurnData turnData : array) {

                gameStatistics.setInt(1, gameID);
                gameStatistics.setInt(2, turnData.getTurn());
                gameStatistics.setTimestamp(3, turnData.getTurnStartTime());
                gameStatistics.setTimestamp(4, turnData.getTurnEndTime());
                gameStatistics.setDouble(5, turnData.getTimeDifference());
                gameStatistics.setLong(6, turnData.getScore());

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
            Statement statement = connection.createStatement();

            ResultSet username = statement.executeQuery("SELECT username FROM game_data WHERE id = "+id);
            username.next();
            playerName = username.getString(1);

            ResultSet scoreQuery = statement.executeQuery("SELECT score FROM game_data WHERE id = "+id);
            scoreQuery.next();
            playerScore = scoreQuery.getLong(1);

            ResultSet turnCount = statement.executeQuery("SELECT COUNT(*) FROM game_statistics WHERE id = '" + id + "'");
            turnCount.next();
            ResultSet average = statement.executeQuery("SELECT (SUM(time_spent)/'" + turnCount.getInt(1) + "') FROM game_statistics WHERE ID = " + id);
            average.next();

            playerAverageMoveTime = average.getDouble(1);

            // Resetting array in case a instance is requested.
            playerTimeSpentOnTurn.clear();
            overallTimeSpentOnTurn.clear();

            ResultSet timeSpent = statement.executeQuery("SELECT time_spent FROM game_statistics WHERE id = " + id);

            turn = 0;
            while (timeSpent.next()) {
                playerTimeSpentOnTurn.add(timeSpent.getDouble(1));
                turn++;
            }

            for (int i=1; i<=turn; i++) {
                ResultSet temp = statement.executeQuery("SELECT COUNT(*) FROM game_statistics WHERE turn = "+i);
                temp.next();
                ResultSet temporary = statement.executeQuery("SELECT SUM(time_spent)/'"+temp.getInt(1)+"' FROM game_statistics WHERE turn = "+i);
                temporary.next();
                overallTimeSpentOnTurn.add(temporary.getDouble(1));
            }

            playerFastestMoveTime = Collections.min(playerTimeSpentOnTurn);
            playerSlowestMoveTime = Collections.max(playerTimeSpentOnTurn);


            ResultSet overallAverageScore = statement.executeQuery("SELECT ROUND(SUM(score)/COUNT(*), 2) from game_data");
            overallAverageScore.next();
            allGameAverageScore = overallAverageScore.getDouble(1);


            // TODO: shorten this
            ResultSet totalTurnCount = statement.executeQuery("SELECT COUNT(*) FROM game_statistics");
            totalTurnCount.next();
            int temporary = totalTurnCount.getInt(1);

            ResultSet totalTimePlayed = statement.executeQuery("SELECT SUM(time_played) FROM game_data");
            totalTimePlayed.next();
            allGameAverageTimePerTurn = totalTimePlayed.getDouble(1) / temporary;


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


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Double> getPlayerTimeSpentOnTurn() {
        return playerTimeSpentOnTurn;
    }

    public List<Double> getOverallTimeSpentOnTurn() {
        return overallTimeSpentOnTurn;
    }

    public List<PlayerRecords> getRecords() {
        return records;
    }

    public double getPlayerAverageMoveTime() {
        return playerAverageMoveTime;
    }

    public double getPlayerFastestMoveTime() {
        return playerFastestMoveTime;
    }

    public double getPlayerSlowestMoveTime() {
        return playerSlowestMoveTime;
    }

    public long getPlayerScore() {
        return playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getAllGameAverageScore() {
        return allGameAverageScore;
    }

    public double getAllGameAverageTimePerTurn() {
        return allGameAverageTimePerTurn;
    }
}



