CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    year INT,
    isbn VARCHAR(50) UNIQUE
);
CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS book_categories (
    book_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (book_id, category_id),
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);
INSERT INTO books (title, author, year, isbn) VALUES
('Clean Code', 'Robert C. Martin', 2008, '9780132350884'),
('Effective Java', 'Joshua Bloch', 2018, '9780134685991'),
('The Pragmatic Programmer', 'Andrew Hunt', 1999, '9780201616224');
SELECT * FROM books;
SELECT year, COUNT(*) AS total_books
FROM books
GROUP BY year
ORDER BY year DESC;
SELECT * FROM books WHERE isbn = '9780132350884';
