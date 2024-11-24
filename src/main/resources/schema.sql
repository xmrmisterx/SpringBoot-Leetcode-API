DROP TABLE IF EXISTS Problem;

CREATE TABLE IF NOT EXISTS Problem(
        id INT NOT NULL,
        name VARCHAR(255) NOT NULL,
        link VARCHAR(255) NOT NULL,
        type VARCHAR(30) NOT NULL,
        date_solved timestamp NOT NULL,
        notes VARCHAR(255) NOT NULL,
        PRIMARY KEY (id)
        );
