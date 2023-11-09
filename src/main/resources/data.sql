-- Test data for "match" table
INSERT INTO match (description, match_date, match_time, team_a, team_b, sport)
VALUES ('Champions League Final', '2023-06-10', '19:00:00', 'Team A', 'Team B', 1),
       ('NBA Playoffs Game 5', '2023-06-15', '20:30:00', 'Lakers', 'Heat', 2),
       ('World Cup Qualifier', '2023-11-20', '18:00:00', 'Country X', 'Country Y', 3);

-- Assuming the IDs for the matches above are 1, 2, and 3 respectively, let's add some test data for "matchodds"
INSERT INTO match_odds (match_id, specifier, odds)
VALUES (1, 'Win-Lose', 1.95),
       (1, 'Draw', 3.50),
       (2, 'Over 200 Points', 1.85),
       (2, 'Under 200 Points', 1.95),
       (3, 'Qualifier Win', 2.20),
       (3, 'Qualifier Lose', 1.70);
