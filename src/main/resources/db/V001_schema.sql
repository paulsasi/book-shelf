CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    nationality VARCHAR(100),

    CONSTRAINT author_name_surname_key UNIQUE (name, surname)
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    release_date TIMESTAMP WITH TIME ZONE NOT NULL,
    topic VARCHAR(100),
    author_id BIGINT,

    CONSTRAINT book_author_id_fkey FOREIGN KEY (author_id) REFERENCES author(id),
    CONSTRAINT book_name_author_key UNIQUE (name, author_id)
);
