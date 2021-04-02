package com.epam.web.dao;

import com.epam.web.enitity.Genre;
import com.epam.web.exceptions.DaoException;
import com.epam.web.mappers.GenreRowMapper;
import com.epam.web.mappers.RowMapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl extends AbstractDao<Genre> implements GenreDao {

    private static final String TABLE_NAME = "genres";

    private static final String GET_ALL_BY_AUTHOR_ID =
            "SELECT g.*\n" +
            "   FROM books as b \n" +
            "   INNER JOIN book_genre as b_g\n" +
            "   on b_g.book_id = b.id\n" +
            "   INNER JOIN genres as g \n" +
            "   on g.id = b_g.genre_id\n" +
            "   WHERE b.id = ?";

    public GenreDaoImpl(Connection connection) {
        super(connection, new GenreRowMapper(), TABLE_NAME);
    }

    @Override
    public List<Genre> getAllByBookId(long bookId) throws DaoException {
        return executeQuery(GET_ALL_BY_AUTHOR_ID, bookId);
    }

    @Override
    public void save(Genre entity) throws DaoException {

    }

    @Override
    public void removeById(long id) {

    }
}
