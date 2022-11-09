USE testservlet;

CREATE TABLE IF NOT EXISTS employees (
    id INT NOT NULL,
    age INT NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

INSERT INTO employees VALUES (104, 35, 'Peter', 'Johnson')