SELECT id, username, date_started, score, turns, time_played, game_difficulty, (CASE WHEN has_quarto = 1 THEN 'YES' ELSE 'NO' END) has_quarto, TO_CHAR(time_played/turns, '99.99') AS "Average Move Duration"
FROM game_data;


SELECT SUM(MIN(time_spent))/COUNT(DISTINCT ID) FROM turn_statistics GROUP BY ID;
SELECT DISTINCT id, time_played from game_data ORDER BY id ASC;