package main.java.model.dataBase;

import oracle.jdbc.pool.OracleDataSource;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for the leaderboard screen and the win/loss screen to
 * display basic and advanced statistics.
 * @author Rodžers Ušackis
 * @version 1.0
 */
public class Leaderboard { // Used for retrieving the leaderboard

    private static final File WALLET = new File("Wallet_QuartoDatabase");
    private static final String dbURL = "jdbc:oracle:thin:@quartodatabase_medium?TNS_ADMIN=" + WALLET.getAbsolutePath();
    public static Statement statement = null;
    public static Record[] records;
    public static List<Integer> turnStats = new ArrayList<>();
    public static long averageTime;
    private static Connection connection = null;
    private static OracleDataSource ods;
    private final String username = "QUARTOADMIN";
    private final String password = "Quarto_Game1";


    /**
     * Method for retrieving the top 5 leaderboard.
     */
    public void getLeaderboard() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
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
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method to retrieve detailed statistics about the players from the top 5 leaderboard
     * @param id Unique game identifier
     */
    public void getStatistics(int id) {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();

            ResultSet average = statement.executeQuery("SELECT AVG(CAST(turn_end_time AS DATE)- CAST(turn_start_time AS DATE))*24*60*60 AS turn FROM game_statistics WHERE ID = " + id);

            while (average.next()) {
                averageTime = average.getInt("turn");
            }

            ResultSet turns = statement.executeQuery(
                    "SELECT (CAST(turn_end_time AS DATE)- CAST(turn_start_time AS DATE))*24*60*60 AS seconds FROM game_statistics WHERE ID = " + id);

            turnStats.clear();
            int i = 0;
            while (turns.next()) {
                turnStats.add(turns.getInt("seconds"));
                i++;
            }

            statement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

