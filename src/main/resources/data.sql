INSERT INTO CLUB (CODE, BASED_COUNTRY, CLUB_NAME, REGULATION_LEAGUE) VALUES
  ('LFC', 'England', 'Liverpool Football Club', 'English Premier League'),
  ('PSG', 'France', 'Paris Saint Germaint', 'French First League');

INSERT INTO PLAYER (PLAYER_NAME, POB, DOB, HEIGHT, WEIGHT, FOOT, CREATED_AT) VALUES
('Ryan Thebloez', 'Kuningan', '1989-09-08', 185, 75, 'Both', CURRENT_DATE()),
('Messi', 'Argentina', '1990-01-02', 177, 65, 'Left', CURRENT_DATE()),
('Cristiano Ronaldo', 'Portugal', '1991-02-04', 177, 65, 'Both', CURRENT_DATE()),
('Buffon', 'Italy', '1985-10-02', 177, 65, 'Left', CURRENT_DATE()),
('Moh. Salah', 'Egypt', '1994-01-02', 177, 65, 'Left', CURRENT_DATE());

insert into PLAYER_POSITION (`POSITION`, ID_PLAYER) values ('AMF', 1),
('ST', 2),
('AMF', 2),
('AMR', 2);
