SELECT id, username, date_started, score, turns, time_played, game_difficulty, has_quarto, TO_CHAR(time_played/turns, '99.99') AS "Average Move Duration"
FROM game_data;

SELECT * FROM (SELECT time_spent FROM game_statistics WHERE id = 1 ORDER BY time_spent DESC) WHERE ROWNUM=1;

SELECT (SUM(time_spent)/3) FROM game_statistics WHERE id = 1;

SELECT time_spent FROM game_statistics WHERE id = 1;