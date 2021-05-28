
/* QUERY FOR JSON */
SELECT id, username, date_started, score, turns, time_played, game_difficulty, (CASE WHEN has_quarto = 1 THEN 'YES' ELSE 'NO' END) has_quarto, TO_CHAR(time_played/turns, '99.99') AS "Average Move Duration"
FROM game_data;



/* PERCENTILE QUERY */
SELECT id, time_played, percentile FROM (SELECT id, time_played, PERCENT_RANK() OVER (ORDER by time_played ASC) *100 AS percentile FROM game_data) WHERE id = 5;