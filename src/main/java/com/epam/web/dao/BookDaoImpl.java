package com.epam.web.dao;

import com.epam.web.enitity.Book;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.BookRowMapper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl extends AbstractDao<Book> implements BookDao {

    private final static String TABLE_NAME = "books";

    private static final String GET_BOOKS_BY_AUTHOR_ID =
            "SELECT books.*\n" +
            "    FROM books\n" +
            "    INNER JOIN book_author\n" +
            "    ON book_author.book_id = books.id\n" +
            "    INNER JOIN authors as a\n" +
            "    ON a.id = book_author.author_id\n" +
            "    WHERE a.id = ?";

    private static final String GET_BOOKS_BY_GENRE_ID =
            "SELECT b.*\n" +
            "    FROM books as b \n" +
            "    INNER JOIN book_genre as b_g\n" +
            "    on b_g.book_id = b.id\n" +
            "    INNER JOIN genres as g\n" +
            "    on g.id = b_g.genre_id\n" +
            "    WHERE g.id = ?";

    private static final String COUNT_BOOKS_BY_AUTHOR_ID =
            "SELECT COUNT(books.id) as count\n" +
                    "    FROM books\n" +
                    "    INNER JOIN book_author\n" +
                    "    ON book_author.book_id = books.id\n" +
                    "    INNER JOIN authors as a\n" +
                    "    ON a.id = book_author.author_id\n" +
                    "    WHERE a.id = ?";

    private static final String COUNT_BOOKS_BY_GENRE_ID =
            "SELECT COUNT(b.id) as count\n" +
                    "    FROM books as b \n" +
                    "    INNER JOIN book_genre as b_g\n" +
                    "    on b_g.book_id = b.id\n" +
                    "    INNER JOIN genres as g\n" +
                    "    on g.id = b_g.genre_id\n" +
                    "    WHERE g.id = ?";

    private static final String INCREASE_QUANTITY_BY_ID = "UPDATE books SET quantity=quantity+1 WHERE id=?";
    private static final String DECREASE_QUANTITY_BY_ID = "UPDATE books SET quantity=quantity-1 WHERE id=?";

    private static final String SAVE_BOOK_WITH_ID = "UPDATE books SET title=?, description=?, quantity=?, image_path=? WHERE id=?";
    private static final String ADD_NEW_BOOK = "INSERT INTO books (title, description, quantity, image_path) VALUES (?, ?, ?, ?)";
    private static final String REMOVE_BY_ID = "DELETE FROM books WHERE id=?";
    private static final String FIND_LAST_ID = "SELECT max(id) as max FROM books";

    public BookDaoImpl(Connection connection, AuthorDao authorDao, GenreDao genreDao) {
        super(connection, new BookRowMapper(), TABLE_NAME);
    }

    @Override
    public List<Book> findBooksByAuthorId(long authorId) throws DaoException {
        return executeQuery(GET_BOOKS_BY_AUTHOR_ID, authorId);
    }

    @Override
    public List<Book> findBooksByGenreId(long genreId) throws DaoException {
        return executeQuery(GET_BOOKS_BY_GENRE_ID, genreId);
    }

    @Override
    public List<Book> findBooksByAuthorId(long authorId, long pageIndex, long elementsOnPage) throws DaoException {
        long startIndex = pageIndex * elementsOnPage;
        String query = GET_BOOKS_BY_AUTHOR_ID + " LIMIT " + startIndex + ", " + elementsOnPage;
        return executeQuery(query, authorId);
    }

    @Override
    public List<Book> findBooksByGenreId(long genreId, long pageIndex, long elementsOnPage) throws DaoException {
        long startIndex = pageIndex * elementsOnPage;
        String query = GET_BOOKS_BY_GENRE_ID + " LIMIT " + startIndex + ", " + elementsOnPage;
        return executeQuery(query, genreId);
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        throw new NotImplementedException();
    }

    @Override
    public List<Book> findBooksByDescription(String description) {
        throw new NotImplementedException();
    }

    @Override
    public long countBooksByAuthorId(long authorId) throws DaoException {
        return count(COUNT_BOOKS_BY_AUTHOR_ID, authorId);
    }

    @Override
    public long countBooksByGenreId(long genreId) throws DaoException {
        return count(COUNT_BOOKS_BY_GENRE_ID, genreId);
    }

    @Override
    public void save(Book entity) throws DaoException {
        if (entity.getId() != 0) {
            execute(SAVE_BOOK_WITH_ID, entity.getTitle(), entity.getDescription(), entity.getQuantity(), entity.getImagePath(), entity.getId());
        } else {
            execute(ADD_NEW_BOOK, entity.getTitle(), entity.getDescription(), entity.getQuantity(), entity.getImagePath());
        }
    }

    @Override
    public void removeById(long id) throws DaoException {
        execute(REMOVE_BY_ID, id);
    }


    public void increaseQuantityById(long id) throws DaoException {
        execute(INCREASE_QUANTITY_BY_ID, id);
    }

    public void decreaseQuantityById(long id) throws DaoException {
        execute(DECREASE_QUANTITY_BY_ID, id);
    }

    public Optional<Long> findLastId() throws DaoException {
        try(PreparedStatement preparedStatement = createStatement(FIND_LAST_ID);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(resultSet.getLong("max"));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
