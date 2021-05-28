package main.java.model.dataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This class is used for the leaderboard screen and the win/loss screen to
 * display basic and advanced statistics.
 *
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 * @version 1.0
 */
public class GameStatistics extends Database { // Used for retrieving the leaderboard

    private List<Double> playerTimeSpentOnTurn = new ArrayList<>();
    private List<Double> allGameTimeSpentOnTurn = new ArrayList<>();
    private List<Double> gamesPlayedDuration = new ArrayList<>();
    private List<PlayerRecords> records = new ArrayList<>();
    private String playerName;
    private int turn;
    private long playerScore;
    private double allGameAverageScore;
    private double playerAverageMoveTime;
    private double playerFastestMoveTime;
    private double playerSlowestMoveTime;
    private double allGameAverageMoveTime;
    private double allGameFastestMoveTime;
    private double allGameSlowestMoveTime;
    private int gameID;
    private double gameDurationPercentile;

    private boolean hasWon;





    public int getGameID() {

        try {
            Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM (SELECT ID FROM game_data ORDER BY ID DESC) WHERE ROWNUM=1");
                if (!resultSet.next()) {
                    gameID = 1;
                } else {
                    gameID = resultSet.getInt(1)+1;
                }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return gameID;
    }

    public void saveGame(ArrayList<TurnData> array, int gameID, String username, int difficulty, boolean hasQuarto, Timestamp dateStarted) {

        try {

            System.out.println("Saving the game.");
            PreparedStatement gameStatistics = connection.prepareStatement("INSERT INTO turn_statistics (id, turn, turn_start_time, turn_end_time, time_spent, score_for_turn) VALUES (?,?,?,?,?,?)");
            PreparedStatement gameData = connection.prepareStatement("INSERT INTO game_data (id, username, date_started, score, turns, time_played, game_difficulty, has_quarto) VALUES (?,?,?,?,?,?,?,?)");

            // Game data (for... game data?)
            playerName = username;
            playerScore = 0;
            double timeDifference = 0;

            for (TurnData turnData : array) {
                playerScore += turnData.getScore();
                timeDifference += turnData.getTimeDifference();
            }

            gameData.setInt(1, gameID);
            gameData.setString(2, username);
            gameData.setTimestamp(3, dateStarted);
            gameData.setInt(5, array.size());
            gameData.setDouble(6, timeDifference);
            gameData.setInt(7, difficulty);

            System.out.println(hasQuarto);
            if (hasQuarto) {
                gameData.setLong(4, (50000-playerScore));
                gameData.setInt(8, 1);
            } else {
                gameData.setInt(8, 0);
                gameData.setLong(4, 0);
            }

            gameData.executeQuery();


            // Game statistics (for turns)
            for (TurnData turnData : array) {

                gameStatistics.setInt(1, gameID);
                gameStatistics.setInt(2, turnData.getTurn());
                gameStatistics.setTimestamp(3, turnData.getTurnStartTime());
                gameStatistics.setTimestamp(4, turnData.getTurnEndTime());
                gameStatistics.setDouble(5, turnData.getTimeDifference());
                gameStatistics.setDouble(6, turnData.getScore());

                gameStatistics.executeQuery();

            }

            getStatistics(gameID);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method to retrieve detailed statistics about the players from the top 5 leaderboard
     *
     * @param id Unique game identifier
     */
    public void getStatistics(int id) {

        try {
            Statement statement = connection.createStatement();

            ResultSet username = statement.executeQuery("SELECT username FROM game_data WHERE id = "+id);
            username.next();
            playerName = username.getString(1);

            ResultSet scoreQuery = statement.executeQuery("SELECT score FROM game_data WHERE id = "+id);
            scoreQuery.next();
            playerScore = scoreQuery.getLong(1);

            ResultSet playerAverageMoveTimeRS = statement.executeQuery("SELECT SUM(time_spent)/COUNT(DISTINCT turn) FROM turn_statistics WHERE id = "+id);
            playerAverageMoveTimeRS.next();
            playerAverageMoveTime = playerAverageMoveTimeRS.getDouble(1);

            // Resetting array in case a instance is requested.
            playerTimeSpentOnTurn.clear();
            allGameTimeSpentOnTurn.clear();

            ResultSet timeSpent = statement.executeQuery("SELECT time_spent FROM turn_statistics WHERE id = " + id);

            turn = 0;
            while (timeSpent.next()) {
                playerTimeSpentOnTurn.add(timeSpent.getDouble(1));
                turn++;
            }

            for (int i=1; i<=turn; i++) {
                ResultSet temp = statement.executeQuery("SELECT COUNT(*) FROM turn_statistics WHERE turn = "+i);
                temp.next();
                ResultSet temporary = statement.executeQuery("SELECT SUM(time_spent)/'"+temp.getInt(1)+"' FROM turn_statistics WHERE turn = "+i);
                temporary.next();
                allGameTimeSpentOnTurn.add(temporary.getDouble(1));
            }

            playerFastestMoveTime = Collections.min(playerTimeSpentOnTurn);
            playerSlowestMoveTime = Collections.max(playerTimeSpentOnTurn);

            ResultSet gameDurations = statement.executeQuery("SELECT DISTINCT id, time_played from game_data ORDER BY id ASC");
            gamesPlayedDuration.clear();
            while(gameDurations.next()) {
                gamesPlayedDuration.add(gameDurations.getDouble(2));
            }

            ResultSet percentileRS = statement.executeQuery("SELECT id, time_played, percentile FROM (SELECT id, time_played, PERCENT_RANK() OVER (ORDER by time_played ASC) *100 AS percentile FROM game_data) WHERE id = "+id);
            percentileRS.next();
            gameDurationPercentile = percentileRS.getDouble(3);

            ResultSet allGameAverageScoreRS = statement.executeQuery("SELECT ROUND(SUM(score)/COUNT(*), 2) from game_data WHERE score!=0");
            allGameAverageScoreRS.next();
            this.allGameAverageScore = allGameAverageScoreRS.getDouble(1);

            ResultSet allGameAverageTurnRS = statement.executeQuery("SELECT SUM(time_spent)/COUNT(TURN) FROM turn_statistics");
            allGameAverageTurnRS.next();
            allGameAverageMoveTime = allGameAverageTurnRS.getDouble(1);

            ResultSet allGameFastestTurnRS = statement.executeQuery("SELECT SUM(MIN(time_spent))/COUNT(DISTINCT ID) FROM turn_statistics GROUP BY ID");
            allGameFastestTurnRS.next();
            allGameFastestMoveTime = allGameFastestTurnRS.getDouble(1);

            ResultSet allGameSlowestTurnRS = statement.executeQuery("SELECT SUM(MAX(time_spent))/COUNT(DISTINCT ID) FROM turn_statistics GROUP BY ID");
            allGameSlowestTurnRS.next();
            allGameSlowestMoveTime = allGameSlowestTurnRS.getDouble(1);

            ResultSet hasWonRS = statement.executeQuery("SELECT has_quarto FROM game_data WHERE id = "+id);
            hasWonRS.next();

            if(hasWonRS.getInt(1) == 1) {
                hasWon = true;
            } else {
                hasWon = false;
            }

            System.out.println("HAS WON = "+hasWon);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method for retrieving the top 5 leaderboard.
     */
    public void getLeaderboard() {
        try {

            Statement statement = connection.createStatement();

            ResultSet rows = statement.executeQuery("SELECT * FROM game_data" +
                    " ORDER BY score DESC");

            int i = 0;
            records.clear();
            while (rows.next() && i < 5) {
                records.add(new PlayerRecords(rows.getInt("id"), rows.getString("username"), rows.getInt("score")));
                i++;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<Double> getPlayerTimeSpentOnTurn() {
        return playerTimeSpentOnTurn;
    }

    public List<Double> getAllGameTimeSpentOnTurn() {
        return allGameTimeSpentOnTurn;
    }

    public List<PlayerRecords> getRecords() {
        return records;
    }

    public double getPlayerAverageMoveTime() {
        return playerAverageMoveTime;
    }

    public double getPlayerFastestMoveTime() {
        return playerFastestMoveTime;
    }

    public double getPlayerSlowestMoveTime() {
        return playerSlowestMoveTime;
    }

    public long getPlayerScore() {
        return playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getAllGameAverageScore() {
        return allGameAverageScore;
    }

    public double getAllGameAverageMoveTime() {
        return allGameAverageMoveTime;
    }

    public double getAllGameFastestMoveTime() {
        return allGameFastestMoveTime;
    }

    public double getAllGameSlowestMoveTime() {
        return allGameSlowestMoveTime;
    }

    public List<Double> getGamesPlayedDuration() {
        return gamesPlayedDuration;
    }

    public boolean isHasWon() { return hasWon; }

    public double getGameDurationPercentile() { return gameDurationPercentile; }
}



