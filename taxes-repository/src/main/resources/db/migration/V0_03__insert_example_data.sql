INSERT INTO municipality (name) VALUES ('Copenhagen');

INSERT INTO taxes (rate, municipality_id, date_from, date_to) VALUES
    (0.2, 1, '2020-01-01', '2020-12-31'),
    (0.4, 1, '2020-05-01', '2020-05-31'),
    (0.1, 1, '2020-01-01', '2020-01-01'),
    (0.1, 1, '2020-12-25', '2020-12-25');
