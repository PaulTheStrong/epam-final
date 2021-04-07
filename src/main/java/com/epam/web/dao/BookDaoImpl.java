package com.epam.web.dao;

import com.epam.web.enitity.Book;
import com.epam.web.exceptions.DaoException;
import com.epam.web.mappers.BookRowMapper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


    public BookDaoImpl(Connection connection, AuthorDao authorDao, GenreDao genreDao) {
        super(connection, new BookRowMapper(), TABLE_NAME);
    }

    @Override
    public List<Book> getBooksByAuthorId(long authorId) throws DaoException {
        return executeQuery(GET_BOOKS_BY_AUTHOR_ID, authorId);
    }

    @Override
    public List<Book> getBooksByGenreId(long genreId) throws DaoException {
        return executeQuery(GET_BOOKS_BY_GENRE_ID, genreId);
    }

    @Override
    public List<Book> getBooksByAuthorId(long authorId, long pageIndex, long elementsOnPage) throws DaoException {
        long startIndex = pageIndex * elementsOnPage;
        String query = GET_BOOKS_BY_AUTHOR_ID + " LIMIT " + startIndex + ", " + elementsOnPage;
        return executeQuery(query, authorId);
    }

    @Override
    public List<Book> getBooksByGenreId(long genreId, long pageIndex, long elementsOnPage) throws DaoException {
        long startIndex = pageIndex * elementsOnPage;
        String query = GET_BOOKS_BY_GENRE_ID + " LIMIT " + startIndex + ", " + elementsOnPage;
        return executeQuery(query, genreId);
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        throw new NotImplementedException();
    }

    @Override
    public List<Book> getBooksByDescription(String description) {
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
        throw new NotImplementedException();
    }

    @Override
    public void removeById(long id) {
        throw new NotImplementedException();
    }
}
