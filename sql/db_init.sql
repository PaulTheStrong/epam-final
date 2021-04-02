create table books(
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title_en VARCHAR(255),
    title_ru VARCHAR(255),
    description_ru VARCHAR(255),
    description_en VARCHAR(255),
    image_path VARCHAR(255)
);

create table users(
  id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  login VARCHAR(50),
  password_hash VARCHAR(256),
  name VARCHAR(50),
  surname VARCHAR(50),
  role enum('ADMIN', 'LIBRARIAN', 'READER') DEFAULT 'reader'
);

create table book_orders(
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    book_id BIGINT,
    status ENUM('NEW', 'ACTIVE', 'FINISHED', 'DICLINED') DEFAULT 'NEW',
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

create table authors(
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    surname VARCHAR(255)
);

create table genres(
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255)
);

create table book_author(
    author_id BIGINT,
    book_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

create table book_genre(
   genre_id BIGINT,
   book_id BIGINT,
   FOREIGN KEY (genre_id) REFERENCES authors(id),
   FOREIGN KEY (book_id) REFERENCES books(id)
);