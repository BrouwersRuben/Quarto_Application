package main.java.model.dataBase;

import main.java.model.Quarto;
import main.java.model.players.Human;
import oracle.jdbc.pool.OracleDataSource;
import java.io.File;
import java.sql.*;

public class Leaderboard {

    private static final File WALLET = new File("Wallet_QuartoDatabase");
    private static final String dbURL = "jdbc:oracle:thin:@quartodatabase_medium?TNS_ADMIN=" + WALLET.getAbsolutePath();
    private final String username = "QUARTOADMIN";
    private final String password = "Quarto_Game1";
    private static Connection connection = null;
	public static Statement statement = null;
    private static OracleDataSource ods;
    private Record[] records;

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

            ResultSet count = statement.executeQuery("SELECT COUNT(*) FROM game_leaderboard");
            while (count.next()) {
                countRecords = count.getInt("COUNT(*)");
            }
            int maxFive = Math.min(countRecords, 5);
            records = new Record[maxFive];


            ResultSet rows = statement.executeQuery("SELECT * FROM INT_leaderboard" +
                    " ORDER BY TOP_SCORE DESC");
            int i = 0;
            while (rows.next() && i < 5) {
                records[i] = new Record(rows.getString("PLAYER_NAME"), rows.getInt("TOP_SCORE"), rows.getString("DATE_SUBMITTED"));
                i++;
            }
            statement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (throwables.getErrorCode() == 942) {
                createTable();
                getRecords();
            }
        }
    }



    public void createRecordsTable() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);
            connection = DriverManager.getConnection(dbURL, username, password);

            statement.execute("CREATE TABLE game_leaderboard"+
                    "(username VARCHAR2(20),"+
                    "top_score INTEGER)");

        } catch (SQLException throwables) {
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

