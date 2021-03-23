package main.java.model.dataBase;

import oracle.jdbc.pool.OracleDataSource;
import java.io.File;
import java.sql.*;

public class Leaderboard { // Used for retrieving the leaderboard

    private static final File WALLET = new File("Wallet_QuartoDatabase");
    private static final String dbURL = "jdbc:oracle:thin:@quartodatabase_medium?TNS_ADMIN=" + WALLET.getAbsolutePath();
    private final String username = "QUARTOADMIN";
    private final String password = "Quarto_Game1";
    private static Connection connection = null;
	public static Statement statement = null;
    private static OracleDataSource ods;
    public static Record[] records;
    public static Statistics[] statistics;
    public static Statistics[] turnStats;
    public static long averageTime;

//    Human player = new Human(); TODO: you have to relocate all of these(I think), idk what they're doing here, this class will be used for loading the leaderboard for the leaderboard screen
//    Quarto game = new Quarto();
//    private final int gameTimerSeconds = game.getGameTimerSeconds();
    // TODO: Get the local date
    //private String date = Date.toString(Calendar.getInstance().getTime());
//    private final int amountOfTurns = game.getAmountOfTurns();
//    private final String name = player.getName();
//    private final double score = player.getScore();


    public void getRecords() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();
            int countRecords = 0;

            ResultSet count = statement.executeQuery("SELECT COUNT(*) FROM test_game_leaderboard");
            while (count.next()) {
                countRecords = count.getInt("COUNT(*)");
            }
            int maxFive = Math.min(countRecords, 5);
            records = new Record[maxFive];


            ResultSet rows = statement.executeQuery("SELECT * FROM test_game_leaderboard" +
                    " ORDER BY TOP_SCORE DESC");
            int i = 0;
            while (rows.next() && i < 5) {
                records[i] = new Record(rows.getInt("ID"), rows.getString("USERNAME"), rows.getInt("TOP_SCORE")); {
                };
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



    public void createRecordsTable() { // TODO: Test why this didn't work
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);
            connection = DriverManager.getConnection(dbURL, username, password);

            statement.execute("CREATE TABLE test_game_leaderboard"+
                    "(id NUMBER(10), " +
                    "username VARCHAR2(20),"+
                    "top_score INTEGER)");

            statement.close();
            connection.close();

        } catch (SQLException throwables) {
        }
    }

    public void getStatistics(int id) {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();

            ResultSet rows = statement.executeQuery("SELECT avg(EXTRACT(SECOND FROM (turn_start_time-turn_end_time)) AS difference FROM test_game_statistics WHERE id = "+id);
            averageTime = rows.getInt("difference");



//            int i = 0;
//            while (rows.next()) {
//                statistics[i] = new Statistics(rows.getInt("ID"), rows.getInt("TURN"), rows.getTimestamp("turn_start_time"),rows.getTimestamp("turn_end_time"), rows.getInt("score_for_turn"));
//                {
//                };
//                i++;
//            }
//
//            for (int j=0; j < statistics.length; j++) {
//                turnStats[j] = new Statistics(Statistics.getTurnDifference(statistics[j].getTurn_start_time(), statistics[j].getTurn_end_time()));
//                System.out.println(turnStats[j]);
//            }

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

            statement.execute("CREATE TABLE test_game_statistics"+
                    "(id NUMBER(10), " +
                    "turn NUMBER(2)," +
                    "turn_start_time timestamp  default SYSTIMESTAMP,"+
                    "turn_end_time   timestamp  default SYSTIMESTAMP,"+
                    "username VARCHAR2(20),"+
                    "top_score INTEGER)");

            statement.close();
            connection.close();

        } catch (SQLException throwables) {
        }
    }

	/*
	// TODO: This has to be more to our liking
	 public static void endGame() {
        try {
                    String insertPlayerData = "INSERT INTO INT_leaderboard (player_name, end_time, score) VALUES ('" + playerName + "', CURRENT_TIMESTAMP, " + gameScore + ")";
            statement.execute(insertPlayerData);
            System.out.println("Successfully inserted the player named " + playerName + " to the database.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	 */

}

