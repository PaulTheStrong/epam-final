package com.epam.web.dao;

import com.epam.web.enitity.Book;
import com.epam.web.exceptions.DaoException;
import com.epam.web.mappers.BookRowMapper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
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

    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    public BookDaoImpl(Connection connection, AuthorDao authorDao, GenreDao genreDao) {
        super(connection, new BookRowMapper(), TABLE_NAME);
        this.genreDao = genreDao;
        this.authorDao = authorDao;
    }

    @Override
    public List<Book> getBooksByAuthorId(long authorId) throws DaoException {
        List<Book> books = executeQuery(GET_BOOKS_BY_AUTHOR_ID, authorId);
        for (Book book : books) {
            book.setGenres(genreDao.getAllByBookId(book.getId()));
            book.setAuthors(authorDao.getAllByBookId(book.getId()));
        }
        return books;
    }

    @Override
    public List<Book> getBooksByGenreId(long genreId) throws DaoException {
        List<Book> books = executeQuery(GET_BOOKS_BY_GENRE_ID, genreId);
        for (Book book : books) {
            book.setGenres(genreDao.getAllByBookId(book.getId()));
            book.setAuthors(authorDao.getAllByBookId(book.getId()));
        }
        return books;
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
    public void save(Book entity) throws DaoException {
        throw new NotImplementedException();
    }

    @Override
    public void removeById(long id) {
        throw new NotImplementedException();
    }

    @Override
    public List<Book> getAll() throws DaoException {
        List<Book> books = super.getAll();
        for (Book book : books) {
            book.setGenres(genreDao.getAllByBookId(book.getId()));
            book.setAuthors(authorDao.getAllByBookId(book.getId()));
        }
        return books;
    }

    @Override
    public Optional<Book> getById(long id) throws DaoException {
        Optional<Book> book = super.getById(id);
        if (book.isPresent()) {
            book.get().setAuthors(authorDao.getAllByBookId(id));
            book.get().setGenres(genreDao.getAllByBookId(id));
        }
        return book;
    }
}
