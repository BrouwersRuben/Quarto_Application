package main.java.model.dataBase;

import oracle.jdbc.pool.OracleDataSource;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void connectToDb() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);
            connection = DriverManager.getConnection(dbURL, username, password);
            if (connection != null) {
                System.out.println("Connected to the database.");

                //Statement to make the table:
//                Statement statement = connection.createStatement();
//                statement.execute("CREATE TABLE INT_leaderboard"
//                        + "(player_name VARCHAR2(20),"
//                        + "top_score INTEGER," +
//                        "date_submitted DATE DEFAULT SYSDATE)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getRecords() {
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
            if (throwables.getErrorCode() == 942) {
                createRecordsTable();
                getRecords();
            }
        }
    }


    public void createRecordsTable() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);
            connection = DriverManager.getConnection(dbURL, username, password);

            statement.execute("CREATE TABLE test_game_leaderboard" +
                    "(id NUMBER(10), " +
                    "username VARCHAR2(20)," +
                    "top_score INTEGER)");

            statement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadStatistics(int id) {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
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
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (throwables.getErrorCode() == 942) {
                createStatisticsTable();
                getRecords();
            }
        }
    }

    private void createStatisticsTable() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);
            connection = DriverManager.getConnection(dbURL, username, password);

            statement.execute("CREATE TABLE test_game_statistics" +
                    "(id NUMBER(10), " +
                    "turn NUMBER(2)," +
                    "turn_start_time timestamp  default SYSTIMESTAMP," +
                    "turn_end_time   timestamp  default SYSTIMESTAMP," +
                    "username VARCHAR2(20)," +
                    "top_score INTEGER)");

            statement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeDb() {
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
                System.out.println("Closed the database statements.");
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Closed the database connection.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}

