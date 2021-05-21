package main.java.model.dataBase;

import main.java.model.Quarto;
import oracle.jdbc.pool.OracleDataSource;

import java.io.File;
import java.sql.*;

/**
 * A class which is responsible for most of the database related
 * methods, such as connecting, disconnecting, creating tables, saving and loading games.
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 * @version 1.1
 */

public class Database {
    private static final File WALLET = new File("Wallet_QuartoDatabase");
    private static final String dbURL = "jdbc:oracle:thin:@quartodatabase_medium?TNS_ADMIN=" + WALLET.getAbsolutePath();
    private static OracleDataSource ods;
    public static Statement statement = null;
    private static Connection connection = null;
    private final String username = "QUARTOADMIN";
    private final String password = "Quarto_Game1";
    private Quarto game;


    public Database() { // Constructor for initializing the game session

    }

    /**
     * Establishes a connection between the database and the application.
     */
    public void connectToDb() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);
            connection = DriverManager.getConnection(dbURL, username, password);
            if (connection != null) {
                System.out.println("Connected to the database.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Terminates the connection between the database and the application.
     */
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

    /**
     * Makes sure the necessary tables for the game functionality are present and if they are not
     * then they are created.
     */
    public void createTableIfDoesntExist() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();

            //TODO: try statement for this, which catches 2289 error.
//            statement.execute("CREATE SEQUENCE game_data_id_seq" +
//                    " START WITH 1" +
//                    " INCREMENT BY 1 NOCACHE" +
//                    " NOCYCLE");


            try {
                statement.executeQuery("SELECT * FROM game_statistics");
            } catch (SQLException e) {
                if (e.getErrorCode() == 942) {
                    statement.execute("CREATE TABLE game_statistics" +
                            "(" +
                            "    id number(10) default game_data_id_seq.currVal CONSTRAINT game_statistics_id_fk REFERENCES game_data (id) ON DELETE CASCADE," +
                            "    turn number(2) CONSTRAINT game_statistics_turn_nn NOT NULL," +
                            "    turn_start_time timestamp default SYSTIMESTAMP CONSTRAINT statistics_turn_start_time_nn NOT NULL," +
                            "    turn_end_time timestamp default SYSTIMESTAMP CONSTRAINT statistics_turn_end_time_nn NOT NULL," +
                            "    score_for_turn number(4) CONSTRAINT game_statistics_score_for_turn_nn NOT NULL" +
                            ")");
                }
            }

            try {
                statement.executeQuery("SELECT * FROM game_leaderboard");
            } catch (SQLException e) {
                if (e.getErrorCode() == 942) {
                    statement.execute("CREATE TABLE game_leaderboard" +
                            "(" +
                            "    id number(10) default game_data_id_seq.currVal " +
                            " CONSTRAINT game_leaderboard_id_fk REFERENCES game_data (id) ON DELETE CASCADE," +
                            "    username varchar2(20) " +
                            " CONSTRAINT game_leaderboard_username_nn NOT NULL," +
                            "    top_score number(4)" +
                            " CONSTRAINT game_leaderboard_top_score_nn NOT NULL" +
                            ")");
                }
            }

        } catch (SQLException e) {

            //   e.printStackTrace();
            System.out.println("Couldn't connect to database");
        }
    }

    /**
     * A method to drop tables just in case it is necessary.
     */
    public void dropTables() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();

            statement.execute("DROP TABLE game_statistics");
            statement.execute("DROP TABLE game_leaderboard");

            System.out.println("Tables dropped.");

        } catch (SQLException e) {
            System.out.println("Tables weren't dropped");
        }
    }

}
