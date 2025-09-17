INSERT INTO users (name, email, password_hash, created_at)
VALUES ('Alice', 'alice@example.com', 'hashed_pwd', CURRENT_TIMESTAMP);

INSERT INTO movies (title, language, genre, duration)
VALUES ('Inception', 'English', 'Sci-Fi', 148);

INSERT INTO theaters (name, city)
VALUES ('PVR Mall', 'Delhi');

INSERT INTO shows (movie_id, theater_id, show_time, screen_no)
VALUES (1, 1, '2025-09-18 18:00:00', 'Screen 1');

INSERT INTO seats (show_id, seat_number, status) VALUES (1, 'A1', 'AVAILABLE');
INSERT INTO seats (show_id, seat_number, status) VALUES (1, 'A2', 'AVAILABLE');
