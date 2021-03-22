DROP TABLE game_data;
DROP TABLE board_data;
DROP TABLE piece_attributes;
DROP TABLE pieces;
DROP TABLE game_statistics;

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
--     turn           number(2) CONSTRAINT game_data_turn_nn NOT NULL,  ARE THESE NECESSARY TO HAVE HERE ? dont think so
    time_played     number(4)
        CONSTRAINT game_data_time_played_nn NOT NULL,
    game_difficulty number(1)
        CONSTRAINT game_data_game_difficulty_nn NOT NULL,
    has_quarto      number(1)
        CONSTRAINT game_data_has_quarto_nn NOT NULL
);

CREATE TABLE board_data /* checks which board tiles are occupied */
(
    id    number(10) default game_data_id_seq.currVal
        CONSTRAINT board_data_id_fk REFERENCES game_data (id) ON DELETE CASCADE,
    row_1 varchar2(4)
        CONSTRAINT board_data_row_1_nn NOT NULL,
    row_2 varchar2(4)
        CONSTRAINT board_data_row_2_nn NOT NULL,
    row_3 varchar2(4)
        CONSTRAINT board_data_row_3_nn NOT NULL,
    row_4 varchar2(4)
        CONSTRAINT board_data_row_4_nn NOT NULL,
    CONSTRAINT board_data_rows_ck CHECK (regexp_like(row_1 || row_2 || row_3 || row_4, '^[01]{16}$')) /* check if curly braces are correct */
    /* ^ - start of line | [01] 0's or 1's | {16} 16 consecutive times | $ - end of line */
);

-- CREATE TABLE board_data_owner /* checks the owner of the board tiles */ NOT NECESSARY
-- (
--     gameID number(10) default game_data_id_seq.currVal CONSTRAINT board_data_fk REFERENCES game_data(gameID) ON DELETE CASCADE,
--     row_1  varchar2(4) CONSTRAINT board_data_owner_row_1_nn NOT NULL,
--     row_2  varchar2(4) CONSTRAINT board_data_owner_row_2_nn NOT NULL,
--     row_3  varchar2(4) CONSTRAINT board_data_owner_row_3_nn NOT NULL,
--     row_4  varchar2(4) CONSTRAINT board_data_owner_row_4_nn NOT NULL,
--     CONSTRAINT board_data_owner_rows_ck CHECK (regexp_like(row_1 || row_2 || row_3 || row_4, '^[01]{16}$'))
-- );

CREATE TABLE piece_attributes
(
    piece_ID number(2)
        CONSTRAINT piece_attributes_piece_ID_pk PRIMARY KEY, /* set as primary*/
    piece_status  number(1) /* in-play: 1 , out-of-play: -1, currently selected: 0*/
        CONSTRAINT piece_attributes_in_play_nn NOT NULL
);

CREATE TABLE pieces
(
    id          number(10) default game_data_id_seq.currVal
        CONSTRAINT pieces_id_fk REFERENCES game_data (id) ON DELETE CASCADE,
    piece_ID    number(2)
        CONSTRAINT pieces_piece_ID_fk REFERENCES piece_attributes (piece_ID) ON DELETE CASCADE,
    coordinates number(2)
        CONSTRAINT pieces_coordinates_nn NOT NULL
);

CREATE TABLE game_statistics
(
    id             number(10) default game_data_id_seq.currVal
        CONSTRAINT game_statistics_id_fk REFERENCES game_data (id) ON DELETE CASCADE,
    turn           number(2)
        CONSTRAINT game_statistics_turn_nn NOT NULL,
    turn_start_time timestamp default SYSTIMESTAMP CONSTRAINT statistics_turn_start_time_nn NOT NULL,
    turn_end_time timestamp default SYSTIMESTAMP CONSTRAINT statistics_turn_end_time_nn NOT NULL,
    score_for_turn number(4)
        CONSTRAINT game_statistics_score_for_turn_nn NOT NULL
);