CREATE TABLE taxes (
                       id INT GENERATED ALWAYS AS IDENTITY NOT NULL,
                       rate NUMERIC (5, 2) NOT NULL,
                       municipality_id INT NOT NULL,
                       date_from DATE NOT NULL,
                       date_to DATE NOT NULL,
                       PRIMARY KEY(id)
                     );