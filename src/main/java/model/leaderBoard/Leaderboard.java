package main.java.model.leaderBoard;

import main.java.model.players.Human;
import main.java.model.Quarto;
import oracle.jdbc.pool.OracleDataSource;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class Leaderboard {

	Human player = new Human();
	Quarto game = new Quarto();

	private int gametimer = game.getGameTimerSeconds();
	private int amountOfTurns = game.getAmountOfTurns();
	private String name = player.getName();
	private double score = player.getScore();
	//private String date = Calendar.getInstance().getTime();

	//TODO: Database Stuff

	private static final File WALLET = new File("Wallet_QuartoDatabase");
	private static String dbURL = "jdbc:oracle:thin:@quartodatabase_medium?TNS_ADMIN="+WALLET.getAbsolutePath();
	private static OracleDataSource ods;

	/* you have to type

			ods = new OracleDataSource();
			ods.setURL(dbURL);
			Connection connection = DriverManager.getConnection(dbURL, "QUARTOADMIN", "Quarto_Game1");
			Statement statement = connection.createStatement();

			for every database related method
	 */

	public void createTable() {
		try {
			ods = new OracleDataSource();
			ods.setURL(dbURL);
			Connection connection = DriverManager.getConnection(dbURL, "QUARTOADMIN", "Quarto_Game1");
			Statement statement = connection.createStatement();

			statement.execute("CREATE TABLE INT_leaderboard"
					+ "(player_name VARCHAR2(20),"
					+ "top_score INTEGER," +
					"date_submitted DATE DEFAULT SYSDATE)");

		} catch (SQLException throwables) {
		}
	}

	/*
	    public static void main(String[] args) {
        try {
            String db_url = "jdbc:oracle:thin:@Group7Integration_medium?TNS_ADMIN=./db";
            String username = "INTEGRATION PROJECT";
            String password = "Group7Integration";

            conn = DriverManager.getConnection(db_url, username, password);
            if(conn != null) {
                System.out.println("Connected to the database.");
                statement = conn.createStatement();

                // Creating the table for the leaderboard
//                 statement.execute("CREATE TABLE INT_leaderboard (player_name varchar2(25), end_time timestamp not null, score number not null)");

                // Creating the table for save games
//                statement.execute("");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeDb() {
        try {
            if(statement != null && !statement.isClosed()) {
                statement.close();
                System.out.println("Closed database statements.");
            }
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Closed database connection.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void endGame() {
        try {
        //TODO: This has to be more to our liking

            String insertPlayerData = "INSERT INTO INT_leaderboard (player_name, end_time, score) VALUES ('" + playerName + "', CURRENT_TIMESTAMP, " + gameScore + ")";
            statement.execute(insertPlayerData);
            System.out.println("Successfully inserted the player named " + playerName + " to the database.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	 */
	
}
