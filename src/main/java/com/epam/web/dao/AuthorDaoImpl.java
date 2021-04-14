package com.epam.web.dao;

import com.epam.web.enitity.Author;
import com.epam.web.exceptions.DaoException;
import com.epam.web.mappers.AuthorRowMapper;
import com.epam.web.mappers.RowMapper;

import java.sql.Connection;
import java.util.List;

public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao {

    private static final String TABLE_NAME = "authors";

    private static final String GET_ALL_BY_BOOK_ID =
            "SELECT a.*\n" +
            "    FROM books as b \n" +
            "    INNER JOIN book_author as b_a\n" +
            "    on b_a.book_id = b.id\n" +
            "    INNER JOIN authors as a \n" +
            "    on a.id = b_a.author_id\n" +
            "    WHERE b.id = ?";

    public AuthorDaoImpl(Connection connection) {
        super(connection, new AuthorRowMapper(), TABLE_NAME);
    }

    @Override
    public List<Author> findAllByBookId(long bookId) throws DaoException {
        return executeQuery(GET_ALL_BY_BOOK_ID, bookId);
    }

    @Override
    public void save(Author entity) throws DaoException {

    }

    @Override
    public void removeById(long id) {

    }
}
