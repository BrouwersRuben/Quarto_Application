package main.java.Model.LeaderBoard;

public class Leaderboard {
	//private String name = Human.this.getName();
	//private double score = Human.this.getScore();
	private String date;

	//TODO: Database Stuff

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
