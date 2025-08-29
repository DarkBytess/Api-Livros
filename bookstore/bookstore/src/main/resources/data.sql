-- Clear existing data
DELETE FROM books;

-- Insert sample books
INSERT INTO books (title, author, price, publish_year) VALUES
('O Senhor dos Anéis', 'J.R.R. Tolkien', 120.0, 1954),
('1984', 'George Orwell', 50.0, 1949),
('Dom Casmurro', 'Machado de Assis', 30.0, 1899),
('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 40.0, 1943);