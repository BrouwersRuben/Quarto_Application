DROP TABLE game_data;
DROP TABLE turn_statistics;

CREATE SEQUENCE game_data_id_seq
    START WITH 1
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE TABLE game_data
(
    id              number(10) default game_data_id_seq.nextVal
        CONSTRAINT game_data_id_pk PRIMARY KEY,
    username        varchar2(20)
        CONSTRAINT game_data_username_nn NOT NULL,
    date_started    timestamp CONSTRAINT game_data_date_nn NOT NULL,
    score           number(8) CONSTRAINT game_data_score_nn NOT NULL,
    turns           number(2) CONSTRAINT game_data_turn_nn NOT NULL,
    time_played     number(4,2)
        CONSTRAINT game_data_time_played_nn NOT NULL,
    game_difficulty number(1)
        CONSTRAINT game_data_game_difficulty_nn NOT NULL,
    has_quarto      number(1)
        CONSTRAINT game_data_has_quarto_nn NOT NULL
);


CREATE TABLE turn_statistics
(
    id              number(10) default game_data_id_seq.currVal
        CONSTRAINT statistics_id_fk REFERENCES game_data (id) ON DELETE CASCADE,
    turn            number(2)
        CONSTRAINT statistics_turn_nn NOT NULL,
    turn_start_time timestamp  default SYSTIMESTAMP
        CONSTRAINT statistics_turn_start_time_nn NOT NULL,
    turn_end_time   timestamp  default SYSTIMESTAMP
        CONSTRAINT statistics_turn_end_time_nn NOT NULL,
    time_spent      number(6) CONSTRAINT statistics_time_spent_nn NOT NULL,
    score_for_turn  number(6)
        CONSTRAINT statistics_score_for_turn_nn NOT NULL
);


CREATE TABLE test_game_leaderboard
(
    id        number(10),
    username  VARCHAR2(20),
    top_score INTEGER
);

CREATE TABLE test_turn_statistics
(
    id              number(10),
    turn            number(2),
    turn_start_time timestamp  default SYSTIMESTAMP,
    turn_end_time   timestamp  default SYSTIMESTAMP,
    score_for_turn  number(4)
);

INSERT INTO test_game_leaderboard (id, username, top_score)
VALUES (1, 'username6', 999);
INSERT INTO TEST_GAME_LEADERBOARD (id, username, top_score)
VALUES (2, 'username1', 29);
INSERT INTO test_game_leaderboard (id, username, top_score)
VALUES (3, 'username2', 27);
INSERT INTO test_game_leaderboard (id, username, top_score)
VALUES (4, 'username3', 20);
INSERT INTO test_game_leaderboard (id, username, top_score)
VALUES (5, 'username4', 19);
INSERT INTO test_game_leaderboard (id, username, top_score)
VALUES (6, 'username5', 17);
