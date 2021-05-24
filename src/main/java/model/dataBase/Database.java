package main.java.model.dataBase;
import oracle.jdbc.pool.OracleDataSource;
import java.io.File;
import java.sql.*;

/**
 * A class which is responsible for most of the database related
 * methods, such as connecting, disconnecting, creating tables, saving and loading games.
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 * @version 1.2
 */

public class Database {
    protected final File WALLET = new File("Wallet_QuartoDatabase");
    protected final String dbURL = "jdbc:oracle:thin:@quartodatabase_medium?TNS_ADMIN=" + WALLET.getAbsolutePath();
    protected OracleDataSource ods;
    protected Statement statement = null;
    protected Connection connection = null;
    protected final String username = "QUARTOADMIN";
    protected final String password = "Quarto_Game1";


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
        } catch (SQLException e) {
            e.printStackTrace();
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

    //TODO: CHANGE DATA TYPE PRECISION, GETTING ERRORS SOMETIMES, PROBABLY FOR SCORE
    /**
     * Makes sure the necessary tables for the game functionality are present and if they are not
     * then they are created.
     */
    public void createTableIfDoesntExist() {
        try {

            Statement statement = connection.createStatement();

            //TODO: try statement for this, which catches 2289 error.
//            statement.execute("CREATE SEQUENCE game_data_id_seq" +
//                    " START WITH 1" +
//                    " INCREMENT BY 1 NOCACHE" +
//                    " NOCYCLE");

            try {
                statement.executeQuery("SELECT * FROM game_data");
            } catch (SQLException e) {
                if (e.getErrorCode() == 942) {
                    statement.execute("CREATE TABLE game_data" +
                            "(" +
                            "    id number(10) default game_data_id_seq.nextVal CONSTRAINT game_data_id_pk PRIMARY KEY," +
                            "    username varchar2(20) CONSTRAINT game_data_username_nn NOT NULL," +
                            "    date_started timestamp CONSTRAINT game_data_date_nn NOT NULL," +
                            "    score number(8) CONSTRAINT game_data_score_nn NOT NULL," +
                            "    turns number(2) CONSTRAINT game_data_turn_nn NOT NULL," +
                            "    time_played number(4,2) CONSTRAINT game_data_time_played_nn NOT NULL," +
                            "    game_difficulty number(1) CONSTRAINT game_data_game_difficulty_nn NOT NULL," +
                            "    has_quarto number(1) CONSTRAINT game_data_has_quarto_nn NOT NULL" +
                            ")");
                }
            }

            try {
                statement.executeQuery("SELECT * FROM game_statistics");
            } catch (SQLException e) {
                if (e.getErrorCode() == 942) {
                    statement.execute("CREATE TABLE game_statistics" +
                            "(" +
                            "    id number(10) default game_data_id_seq.currVal CONSTRAINT statistics_id_fk REFERENCES game_data (id) ON DELETE CASCADE," +
                            "    turn number(2) CONSTRAINT statistics_turn_nn NOT NULL," +
                            "    turn_start_time timestamp default SYSTIMESTAMP CONSTRAINT statistics_turn_start_time_nn NOT NULL," +
                            "    turn_end_time timestamp default SYSTIMESTAMP CONSTRAINT statistics_turn_end_time_nn NOT NULL," +
                            "    time_spent number(3,2) CONSTRAINT statistics_time_spent_nn NOT NULL," +
                            "    score_for_turn number(6) CONSTRAINT statistics_score_for_turn_nn NOT NULL" +
                            ")");
                }
            }

        } catch (SQLException e) {

            //   e.printStackTrace() ;
            System.out.println("Couldn't connect to database");
        }
    }
}
