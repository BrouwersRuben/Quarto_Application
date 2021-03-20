package main.java.model.leaderBoard;

import main.java.model.Quarto;
import main.java.model.players.Human;
import oracle.jdbc.pool.OracleDataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Leaderboard {

    private static final File WALLET = new File("Wallet_QuartoDatabase");
    private static final String dbURL = "jdbc:oracle:thin:@quartodatabase_medium?TNS_ADMIN=" + WALLET.getAbsolutePath();
    private final String username = "QUARTOADMIN";
    private final String password = "Quarto_Game1";
    private static Connection connection = null;
	public static Statement statement = null;
    private static OracleDataSource ods;

    Human player = new Human();
    Quarto game = new Quarto();

    private final int gameTimerSeconds = game.getGameTimerSeconds();
    // TODO: Get the local date
    //private String date = Date.toString(Calendar.getInstance().getTime());

    private final int amountOfTurns = game.getAmountOfTurns();
    private final String name = player.getName();
    private final double score = player.getScore();

    public void connectToDb() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);
            connection = DriverManager.getConnection(dbURL, username, password);
			if(connection != null) {
				System.out.println("Connected to the database.");

				/*Statement to make the table:
				Statement statement = connection.createStatement();
				statement.execute("CREATE TABLE INT_leaderboard"
						+ "(player_name VARCHAR2(20),"
						+ "top_score INTEGER," +
						"date_submitted DATE DEFAULT SYSDATE)");*/
			}
        } catch (SQLException throwables) {
        	throwables.printStackTrace();
        }
    }

	public void closeDb() {
		try {
			if(statement != null && !statement.isClosed()) {
                statement.close();
				System.out.println("Closed the database statements.");
			}
			if(connection != null && !connection.isClosed()) {
                connection.close();
				System.out.println("Closed the database connection.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
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

/* you have to type
	ods = new OracleDataSource();
	ods.setURL(dbURL);
	Connection connection = DriverManager.getConnection(dbURL, "QUARTOADMIN", "Quarto_Game1");
	Statement statement = connection.createStatement();
for every database related method
*/
