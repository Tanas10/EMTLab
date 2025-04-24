DROP MATERIALIZED VIEW IF EXISTS books_per_author_view;

CREATE MATERIALIZED VIEW books_per_author_view AS
SELECT
    a.id AS author_id,
    CONCAT(a.name, ' ', a.surname) AS author_name,
    COUNT(b.id) AS book_count
FROM book b
         JOIN author a ON b.author_id = a.id
GROUP BY a.id, a.name, a.surname;
