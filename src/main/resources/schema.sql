CREATE OR REPLACE FORCE VIEW game_statistics AS
SELECT
    ga.player_id,
    ga.pool_size,
    ga.code_length,
    COUNT(*) AS games_played,
    AVG(st.guess_count) AS avg_guess_count,
    AVG(st.duration) AS avg_duration
FROM
    game AS ga
        JOIN guess AS gs
             ON gs.game_id = ga.game_id
                 AND gs.correct = ga.code_length
        JOIN (
        SELECT
            gs.game_id,
            COUNT(*) AS guess_count,
            DATEDIFF('MS', MIN(gs.created), MAX(gs.created)) AS duration
        FROM
            guess AS gs
        GROUP BY
            gs.game_id
    ) as st
             ON st.game_id = ga.game_id
GROUP BY
    ga.player_id,
    ga.pool_size,
    ga.code_length;