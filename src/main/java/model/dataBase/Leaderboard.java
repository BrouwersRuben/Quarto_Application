package main.java.model.dataBase;

import oracle.jdbc.pool.OracleDataSource;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO: PLACE CONSTRAINTS ON TABLE CREATION METHODS AND PLACE THE MISSING TABLES
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

//    public void connectToDb() {
//        try {
//            ods = new OracleDataSource();
//            ods.setURL(dbURL);
//            connection = DriverManager.getConnection(dbURL, username, password);
//            if (connection != null) {
//                System.out.println("Connected to the database.");
//
//                //Statement to make the table:
////                Statement statement = connection.createStatement();
////                statement.execute("CREATE TABLE INT_leaderboard"
////                        + "(player_name VARCHAR2(20),"
////                        + "top_score INTEGER," +
////                        "date_submitted DATE DEFAULT SYSDATE)");
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    // TODO: CHANGE IT TO THE REAL LEADERBOARD ONCE WORKING
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
//            if (throwables.getErrorCode() == 942) {
//                createLeaderboardTable();
//                getRecords();
//            }
        }
    }


//    public void createLeaderboardTable() {
//        try {
//            ods = new OracleDataSource();
//            ods.setURL(dbURL);
//            connection = DriverManager.getConnection(dbURL, username, password);
//
//            statement.execute("CREATE TABLE game_leaderboard" +
//                    "(id number(10) default game_data_id_seq.currVal" +
//                    " CONSTRAINT game_leaderboard_id_fk REFERENCES game_data (id) ON DELETE CASCADE, " +
//                    "username VARCHAR2(20)" +
//                    " CONSTRAINT game_leaderboard_username_nn NOT NULL," +
//                    "top_score number(4)" +
//                    " CONSTRAINT game_leaderboard_top_score_nn NOT NULL)");
//
//            statement.close();
//            connection.close();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

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
//            if (throwables.getErrorCode() == 942) {
//                createStatisticsTable();
//                getRecords();
//            }
        }
    }

//    private void createStatisticsTable() {
//        try {
//            ods = new OracleDataSource();
//            ods.setURL(dbURL);
//            connection = DriverManager.getConnection(dbURL, username, password);
//
//            statement.execute("CREATE TABLE game_statistics" +
//                    "(id number(10) default game_data_id_seq.currVal" +
//                    " CONSTRAINT game_statistics_id_fk REFERENCES game_data (id) ON DELETE CASCADE, " +
//                    "turn number(2)" +
//                    " CONSTRAINT game_statistics_turn_nn NOT NULL," +
//                    "turn_start_time timestamp  default SYSTIMESTAMP" +
//                    " CONSTRAINT statistics_turn_start_time_nn NOT NULL," +
//                    "turn_end_time   timestamp  default SYSTIMESTAMP" +
//                    " CONSTRAINT statistics_turn_end_time_nn NOT NULL," +
//                    "score_for_turn number(4)" +
//                    " CONSTRAINT game_statistics_score_for_turn_nn NOT NULL)");
//
//            statement.close();
//            connection.close();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    public void closeDb() {
//        try {
//            if (statement != null && !statement.isClosed()) {
//                statement.close();
//                System.out.println("Closed the database statements.");
//            }
//            if (connection != null && !connection.isClosed()) {
//                connection.close();
//                System.out.println("Closed the database connection.");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

}

