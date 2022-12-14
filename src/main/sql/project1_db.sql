PROJECT1: CREATE TABLE Person (
                        id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        full_name varchar(100) NOT NULL UNIQUE,
                        year_of_birth int NOT NULL
);

CREATE TABLE Book (
                      id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                      title varchar(100) NOT NULL,
                      author varchar(100) NOT NULL,
                      year int NOT NULL,
                      person_id int REFERENCES Person(id) ON DELETE SET NULL
);
PROJECT2: CREATE TABLE Person (
                      id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                      full_name varchar(100) NOT NULL,
                      year_of_birth int NOT NULL
);

CREATE TABLE Book (
                      id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                      person_id int REFERENCES Person(id) ON DELETE SET NULL,
                      title varchar(100) NOT NULL,
                      author varchar(100) NOT NULL,
                      year int NOT NULL
);

INSERT INTO Person(id, name, age) VALUES (1, 'Test person', 20);
SELECT * FROM Person;
DROP TABLE Person;
