DELETE FROM episode;
DELETE FROM tv_show_actors;
DELETE FROM actor;
DELETE FROM tv_show_genres;
DELETE FROM genre;
DELETE FROM tv_show;

INSERT INTO tv_show(id, name, version) VALUES
    (1, 'Succession', 0),
    (2, 'Barry', 0);

INSERT INTO genre(id, name, version) VALUES
    (1, 'Comedy', 0),
    (2, 'Drama', 0),
    (3, 'Crime', 0);

INSERT INTO tv_show_genres(tv_shows_id, genres_id) VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 2),
    (2, 3);

INSERT INTO actor(id, name, version) VALUES
    (1, 'Nicholas Braun', 0),
    (2, 'Brian Cox', 0),
    (3, 'Kieran Culkin', 0),
    (4, 'Peter Friedman', 0),
    (5, 'Matthew Macfadyen', 0),
    (6, 'Alan Ruck', 0),
    (7, 'Sarah Snook', 0),
    (8, 'Jeremy Strong', 0),
    (9, 'J. Smith-Cameron', 0),
    (10, 'Dagmara Dominczyk', 0),
    (11, 'David Rasche', 0),
    (12, 'Rob Yang', 0),
    (13, 'Scott Nicholson', 0),
    (14, 'Arian Moayed', 0),
    (15, 'Fisher Stevens', 0),
    (16, 'Juliana Canfield', 0),
    (17, 'Bill Hader', 0),
    (18, 'Stephen Root', 0),
    (19, 'Sarah Goldberg', 0),
    (20, 'Anthony Carrigan', 0),
    (21, 'Henry Winkler', 0),
    (22, 'Darrell Britt-Gibson', 0),
    (23, 'Andy Carey', 0),
    (24, 'Rightor Doyle', 0),
    (25, 'Alejandro Furth', 0),
    (26, 'Kirby Howell-Baptiste', 0),
    (27, 'John Pirruccello', 0),
    (28, 'Darcy Carden', 0),
    (29, 'Paula Newsome', 0),
    (30, 'Glenn Fleshler', 0),
    (31, 'Michael Irby', 0),
    (32, 'Patricia Faasua', 0),
    (33, 'Turhan Troy Caylak', 0),
    (34, 'Nick Gracer', 0);

INSERT INTO tv_show_actors(tv_shows_id, actors_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (1, 7),
    (1, 8),
    (1, 9),
    (1, 10),
    (1, 11),
    (1, 12),
    (1, 13),
    (1, 14),
    (1, 15),
    (1, 16),
    (2, 17),
    (2, 18),
    (2, 19),
    (2, 20),
    (2, 21),
    (2, 22),
    (2, 23),
    (2, 24),
    (2, 25),
    (2, 26),
    (2, 27),
    (2, 28),
    (2, 29),
    (2, 30),
    (2, 31),
    (2, 32),
    (2, 33),
    (2, 34);

INSERT INTO episode(id, name, version, episode, release_date, season, tv_show_id) VALUES
    (1, 'Celebration', 0, 1, '2018-06-04', 1, 1),
    (2, 'Shit Show at the Fuck Factory', 0, 2, '2018-06-11', 1, 1),
    (3, 'Lifeboats', 0, 3, '2018-06-18', 1, 1),
    (4, 'Sad Sack Wasp Trap', 0, 4, '2018-06-25', 1, 1),
    (5, 'I Went to Market', 0, 5, '2018-07-02', 1, 1),
    (6, 'Which Side Are You On?', 0, 6, '2018-07-09', 1, 1),
    (7, 'Austerlitz', 0, 7, '2018-07-16', 1, 1),
    (8, 'Prague', 0, 8, '2018-07-23', 1, 1),
    (9, 'Pre-Nuptial', 0, 9, '2018-07-30', 1, 1),
    (10, 'Nobody Is Ever Missing', 0, 10, '2018-08-06', 1, 1),
    (11, 'The Summer Palace', 0, 1, '2019-08-12', 2, 1),
    (12, 'Vaulter', 0, 2, '2019-08-19', 2, 1),
    (13, 'Hunting', 0, 3, '2019-08-26', 2, 1),
    (14, 'Safe Room', 0, 4, '2019-09-02', 2, 1),
    (15, 'Tern Haven', 0, 5, '2019-09-09', 2, 1),
    (16, 'Argestes', 0, 6, '2019-09-16', 2, 1),
    (17, 'Return', 0, 7, '2019-09-23', 2, 1),
    (18, 'Dundee', 0, 8, '2019-09-30', 2, 1),
    (19, 'DC', 0, 9, '2019-10-07', 2, 1),
    (20, 'This Is Not for Tears', 0, 10, '2019-10-14', 2, 1),
    (21, 'Secession', 0, 1, '2021-10-18', 3, 1),
    (22, 'Mass in Time of War', 0, 2, '2021-10-25', 3, 1),
    (23, 'The Disruption', 0, 3, '2021-10-01', 3, 1),
    (24, 'Lion in the Meadow', 0, 4, '2021-10-08', 3, 1),
    (25, 'Retired Janitors of Idaho', 0, 5, '2021-11-15', 3, 1),
    (26, 'What It Takes', 0, 6, '2021-11-22', 3, 1),
    (27, 'Too Much Birthday', 0, 7, '2021-11-29', 3, 1),
    (28, 'Chiantishire', 0, 8, '2021-12-06', 3, 1),
    (29, 'Chapter One: Make Your Mark', 0, 1, '2018-03-26', 1, 2),
    (30, 'Chapter Two: Use It', 0, 2, '2018-04-02', 1, 2),
    (31, 'Chapter Three: Make The Unsafe Choice', 0, 3, '2018-04-09', 1, 2),
    (32, 'Chapter Four: Commit...To YOU', 0, 4, '2018-04-16', 1, 2),
    (33, 'Chapter Five: Do Your Job', 0, 5, '2018-04-23', 1, 2),
    (34, 'Chapter Six: Listen with Your Ears, React with Your Face', 0, 6, '2018-04-30', 1, 2),
    (35, 'Chapter Seven: Loud, Fast, and Keep Going', 0, 7, '2018-05-07', 1, 2),
    (36, 'Chapter Eight: Know Your Truth', 0, 8, '2018-05-14', 1, 2),
    (37, 'The Show Must Go On, Probably?', 0, 1, '2019-04-01', 2, 2),
    (38, 'The Power of No', 0, 2, '2019-04-08', 2, 2),
    (39, 'Past = Present x Future Over Yesterday', 0, 3, '2019-04-15', 2, 2),
    (40, 'What?!', 0, 4, '2019-04-22', 2, 2),
    (41, 'ronny/lily', 0, 5, '2019-04-29', 2, 2),
    (42, 'The Truth Has a Ring to It', 0, 6, '2019-05-06', 2, 2),
    (43, 'The Audition', 0, 7, '2019-05-13', 2, 2),
    (44, 'berkman > block', 0, 8, '2019-05-20', 2, 2);