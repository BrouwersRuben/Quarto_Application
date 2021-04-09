package main.java.model.dataBase;

import main.java.model.Quarto;
import oracle.jdbc.pool.OracleDataSource;

import java.io.File;
import java.sql.*;

public class SaveAndLoad {
    private static final File WALLET = new File("Wallet_QuartoDatabase");
    private static final String dbURL = "jdbc:oracle:thin:@quartodatabase_medium?TNS_ADMIN=" + WALLET.getAbsolutePath();
    private static OracleDataSource ods;
    private final String username = "QUARTOADMIN";
    private final String password = "Quarto_Game1";
    private Quarto game;


    public SaveAndLoad() { // Constructor for initializing the game session

    }

    public SaveAndLoad(Quarto game) { // Constructor for initializing and existing game session

    }

    //TODO: Make smaller and rename
    public void createTableIfDoesntExist() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();
            try {
                statement.executeQuery("SELECT * FROM game_data");


            } catch (SQLException e) {
                if (e.getErrorCode() == 942) {

                    try {
                        statement.execute("CREATE TABLE game_data(" +
                                "    id number(10) default game_data_id_seq.nextVal CONSTRAINT game_data_id_pk PRIMARY KEY," +
                                "    username varchar2(20) CONSTRAINT game_data_username_nn NOT NULL," +
                                "    time_played number(4) CONSTRAINT game_data_time_played_nn NOT NULL," +
                                "    game_difficulty number(1) CONSTRAINT game_data_game_difficulty_nn NOT NULL," +
                                "    has_quarto number(1) CONSTRAINT game_data_has_quarto_nn NOT NULL\n" +
                                ")");
//                      e.printStackTrace();
                    } catch (SQLException d) {
//                        d.printStackTrace();
                    }
                }
            }

            try {
                statement.executeQuery("SELECT * FROM board_data");


            } catch (SQLException e) {
                if (e.getErrorCode() == 942) {
                    statement.execute("CREATE TABLE board_data /* checks which board tiles are occupied */" +
                            "(" +
                            "    id number(10) default game_data_id_seq.currVal CONSTRAINT board_data_id_fk REFERENCES game_data (id) ON DELETE CASCADE,\n" +
                            "    row_1 varchar2(4) CONSTRAINT board_data_row_1_nn NOT NULL," +
                            "    row_2 varchar2(4) CONSTRAINT board_data_row_2_nn NOT NULL," +
                            "    row_3 varchar2(4) CONSTRAINT board_data_row_3_nn NOT NULL," +
                            "    row_4 varchar2(4) CONSTRAINT board_data_row_4_nn NOT NULL," +
                            "    CONSTRAINT board_data_rows_ck CHECK (regexp_like(row_1 || row_2 || row_3 || row_4, '^[01]{16}$'))" +
                            "    /* ^ - start of line | [01] 0's or 1's | {16} 16 consecutive times | $ - end of line */" +
                            ")");
                }
            }
            try {
                statement.executeQuery("SELECT * FROM piece_attributes");


            } catch (SQLException e) {
                if (e.getErrorCode() == 942) {
                    statement.execute("CREATE TABLE piece_attributes" +
                            "(" +
                            "    piece_ID number(2) CONSTRAINT piece_attributes_piece_ID_pk PRIMARY KEY," +
                            "    piece_status  number(1) CONSTRAINT piece_attributes_in_play_nn NOT NULL" +
                            ")");
                }
            }
            try {
                statement.executeQuery("SELECT * FROM pieces");
            } catch (SQLException e) {
                if (e.getErrorCode() == 942) {
                    statement.execute("CREATE TABLE pieces" +
                            "(" +
                            "    id number(10) default game_data_id_seq.currVal CONSTRAINT pieces_id_fk REFERENCES game_data (id) ON DELETE CASCADE," +
                            "    piece_ID number(2) CONSTRAINT pieces_piece_ID_fk REFERENCES piece_attributes (piece_ID) ON DELETE CASCADE," +
                            "    coordinates number(2) CONSTRAINT pieces_coordinates_nn NOT NULL" +
                            ")");
                }
            }
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
                            "    id number(10) default game_data_id_seq.currVal CONSTRAINT game_leaderboard_id_fk REFERENCES game_data (id) ON DELETE CASCADE," +
                            "    username varchar2(20) CONSTRAINT game_leaderboard_username_nn NOT NULL," +
                            "    top_score INTEGER CONSTRAINT game_leaderboard_top_score_nn NOT NULL" +
                            ")");
                }
            }

        } catch (SQLException e) {

            //   e.printStackTrace();
            System.out.println("Couldn't connect to database");
        }
    }

    public void saveGame() { // TODO: Implement game logic
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();


            statement.execute("DELETE FROM game_data " +
                    "WHERE LOWER(username)='something'"); // Human object username


            statement.execute("INSERT INTO game_data(ID, USERNAME, TIME_PLAYED, GAME_DIFFICULTY, HAS_QUARTO)" +
                    "values(default, 233, 2, 1, 1)");


            statement.close();
            connection.close();
            System.out.println("Your game was successfully saved.");

        } catch (SQLException e) {
            if (e.getErrorCode() == 942) {
                createTableIfDoesntExist();
                saveGame();

            } else {
//                e.printStackTrace();
                System.out.println("Something went wrong. Your game couldn't be saved.");
            }


        }

    }

    public boolean savedGameExists(String playerName) {

        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();

            ResultSet count = statement.executeQuery("SELECT COUNT(*) FROM game_data WHERE LOWER(username)='" + playerName.toLowerCase() + "'");

            return count.next() && count.getInt(1) > 0;


        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Something is wrong with the connection to the database. Your game will not be loaded. Sorry for the inconvenience.");
//            e.printStackTrace();
            return false;
        }
    }

    public Quarto loadGame(String playerName) { // Not sure yet, haven't checked

//        game = new Quarto(playerName);

        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();


            ResultSet gameTable = statement.executeQuery("SELECT id, username, time_played, game_difficulty, has_quarto " +
                    "FROM game_data " +
                    "WHERE LOWER(username)='" + playerName.toLowerCase() + "'");

            gameTable.next();

            String id = gameTable.getString("id");
            // Set values from the database into the game logic methods


        } catch (SQLException e) {
            if (e.getErrorCode() == 942) {
                createTableIfDoesntExist();
                loadGame(playerName);

            } else {
                // e.printStackTrace();
                System.out.println("Something is wrong with the connection to the database. Your game will not be loaded. Sorry for inconvenience.");
//                e.printStackTrace();
            }

        }
        return game;
    }


    // This method exists just in case we have to drop the tables, it's not being used for the game itself
    public void dropTables() {
        try {
            ods = new OracleDataSource();
            ods.setURL(dbURL);

            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();

            statement.execute("DROP TABLE game_data");
            statement.execute("DROP TABLE board_data");
            statement.execute("DROP TABLE piece_attributes");
            statement.execute("DROP TABLE pieces");
            statement.execute("DROP TABLE game_statistics");
            statement.execute("DROP TABLE game_leaderboard");

            System.out.println("Tables dropped.");

        } catch (SQLException e) {
            System.out.println("Tables weren't dropped");
        }
    }

}
