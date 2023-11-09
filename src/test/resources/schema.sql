-- Schema for "match" table
CREATE TABLE IF NOT EXISTS match
(
    id          INT          NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    match_date  DATE         NOT NULL,
    match_time  TIME         NOT NULL,
    team_a      VARCHAR(50)  NOT NULL,
    team_b      VARCHAR(50)  NOT NULL,
    sport       INT          NOT NULL,
    PRIMARY KEY (id)
);

-- Schema for "match_odds" table
CREATE TABLE IF NOT EXISTS match_odds
(
    id        INT           NOT NULL AUTO_INCREMENT,
    match_id  INT           NOT NULL,
    specifier VARCHAR(50)   NOT NULL,
    odds      DECIMAL(5, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (match_id) REFERENCES match (id) ON DELETE CASCADE
);
