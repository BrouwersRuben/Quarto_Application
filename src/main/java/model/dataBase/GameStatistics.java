package main.java.model.dataBase;

import java.sql.*;
import java.util.ArrayList;
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

    public static List<Integer> turnStats = new ArrayList<>();
    public static Record[] records;
    public static long averageTime;


    /**
     * Method for retrieving the top 5 leaderboard.
     */
    public void getLeaderboard() {
        try {

            Statement statement = connection.createStatement();
            int countRecords = 0;

            ResultSet count = statement.executeQuery("SELECT COUNT (*) FROM test_game_leaderboard");
            while (count.next()) {
                countRecords = count.getInt("COUNT(*)");
            }
            int maxFive = Math.min(countRecords, 5);
            records = new Record[maxFive];


            ResultSet rows = statement.executeQuery("SELECT * FROM test_game_leaderboard" +
                    " ORDER BY TOP_SCORE DESC");
            int i = 0;
            while (rows.next() && i < 5) {
                records[i] = new Record(rows.getInt("ID"), rows.getString("USERNAME"), rows.getInt("TOP_SCORE"));
                {
                }
                i++;
            }
            statement.close();

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

            ResultSet average = statement.executeQuery("SELECT AVG(CAST(turn_end_time AS DATE)- CAST(turn_start_time AS DATE))*24*60*60 AS turn FROM test_game_statistics WHERE ID = " + id);

            while (average.next()) {
                averageTime = average.getInt("turn");
            }

            ResultSet turns = statement.executeQuery(
                    "SELECT (CAST(turn_end_time AS DATE)- CAST(turn_start_time AS DATE))*24*60*60 AS seconds FROM test_game_statistics WHERE ID = " + id);

            turnStats.clear();
            int i = 0;
            while (turns.next()) {
                turnStats.add(turns.getInt("seconds"));
                i++;
            }

            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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

    public void saveGameStatistics(ArrayList<TurnStatistics> array, int gameID, String username, int difficulty, boolean hasQuarto, Timestamp dateStarted) {

        try {

            Statement statement = connection.createStatement();

            PreparedStatement gameStatistics = connection.prepareStatement("INSERT INTO game_statistics (id, turn, turn_start_time, turn_end_time, time_spent, score_for_turn) VALUES (?,?,?,?,?,?)");
            PreparedStatement gameData = connection.prepareStatement("INSERT INTO game_data (id, username, date_started, score, turns, time_played, game_difficulty, has_quarto) VALUES (?,?,?,?,?,?,?,?)");


            ResultSet totalScore = statement.executeQuery("SELECT SUM(score_for_turn) AS total_score FROM game_statistics WHERE ID='" + gameID + "'");
            totalScore.next();
            ResultSet turnCount = statement.executeQuery("SELECT COUNT(*) AS turnCount from user_tab_columns WHERE table_name = 'game_statistics'");
            turnCount.next();
            ResultSet timePlayed = statement.executeQuery("SELECT turn_start_time, turn_end_time, turn_end_time-turn_start_time AS difference FROM game_statistics");
            timePlayed.next();

            // Game data (for... game data?)
            long scoreSum = 0;
            double timeDifference = 0;

            for (int i = 0; i < array.size(); i++) {
                scoreSum += array.get(i).getScore();
                timeDifference += array.get(i).getTimeDifference();
            }

            gameData.setInt(1, gameID);
            gameData.setString(2, username);
            gameData.setTimestamp(3, dateStarted);
            gameData.setLong(4, scoreSum);
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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}



