SELECT id, username, date_started, score, turns, time_played, game_difficulty, has_quarto, TO_CHAR(time_played/turns, '99.99') AS "Average Move Duration"
FROM game_data;