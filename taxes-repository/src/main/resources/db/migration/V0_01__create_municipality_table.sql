CREATE TABLE municipality (
                       id INT GENERATED ALWAYS AS IDENTITY NOT NULL,
                       name varchar(255) NOT NULL,
                       PRIMARY KEY(id)
                     );