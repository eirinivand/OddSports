-- Create 'match' table
CREATE TABLE IF NOT EXISTS match
(
    id          SERIAL PRIMARY KEY,
    description TEXT,
    match_date  DATE,
    match_time  TIME,
    team_a      VARCHAR(255),
    team_b      VARCHAR(255),
    sport       SMALLINT
);

-- Create 'matchodds' table
CREATE TABLE IF NOT EXISTS match_odds
(
    id        SERIAL PRIMARY KEY,
    match_id  INTEGER REFERENCES match (id),
    specifier VARCHAR(50),
    odds      NUMERIC(5, 2), -- Assuming we want to store odds as a numeric with two decimal places
    CONSTRAINT fk_team
        FOREIGN KEY (match_id)
            REFERENCES match (id)
            ON DELETE SET NULL

);
