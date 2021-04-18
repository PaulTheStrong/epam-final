package com.epam.web.dao;

import com.epam.web.enitity.Genre;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.GenreRowMapper;

import java.sql.Connection;
import java.util.List;

public class GenreDaoImpl extends AbstractDao<Genre> implements GenreDao {

    private static final String TABLE_NAME = "genres";

    private static final String GET_ALL_BY_BOOK_ID =
            "SELECT g.*\n" +
            "   FROM books as b \n" +
            "   INNER JOIN book_genre as b_g\n" +
            "   on b_g.book_id = b.id\n" +
            "   INNER JOIN genres as g \n" +
            "   on g.id = b_g.genre_id\n" +
            "   WHERE b.id = ?";

    private static final String DELETE_MAPPINGS_BY_BOOK_ID =
            "DELETE genre\n" +
                    "FROM book_genre genre\n" +
                    "INNER JOIN books\n" +
                    "ON books.id=genre.book_id\n" +
                    "WHERE book_id=?";

    private static final String INSERT_IF_NOT_EXISTS =
            "INSERT INTO genres (name)\n" +
            "SELECT * FROM (SELECT ?) AS tmp\n" +
            "WHERE NOT EXISTS (\n" +
            "    SELECT name FROM genres WHERE upper(name) = upper(?)\n" +
            ") LIMIT 1;";

    private static final String MAP_GENRE_WITH_BOOK =
            "INSERT INTO book_genre (genre_id,  book_id)\n" +
                    "\tSELECT id, ? FROM genres WHERE upper(name)=upper(?)";

    private static final String SELECT_ALL_WHERE_BOOK_ATTACHED =
                                "SELECT g.id AS id, g.name AS name " +
                                "FROM genres AS g " +
                                "INNER JOIN book_genre AS b_g " +
                                "ON b_g.genre_id=g.id " +
                                "GROUP BY name";

    public GenreDaoImpl(Connection connection) {
        super(connection, new GenreRowMapper(), TABLE_NAME);
    }

    @Override
    public List<Genre> findAllByBookId(long bookId) throws DaoException {
        return executeQuery(GET_ALL_BY_BOOK_ID, bookId);
    }

    public List<Genre> findAllWhereBookAttached() throws DaoException {
        return executeQuery(SELECT_ALL_WHERE_BOOK_ATTACHED);
    }

    @Override
    public void save(Genre entity) throws DaoException {

    }

    @Override
    public void deleteMappingsByBookId(long bookId) throws DaoException {
        execute(DELETE_MAPPINGS_BY_BOOK_ID, bookId);
    }

    @Override
    public void mapGenresWithBookId(List<String> genres, long bookId) throws DaoException {
        for (String genre : genres) {
            if (!genre.equals("")) {
                execute(MAP_GENRE_WITH_BOOK, bookId, genre);
            }
        }
    }

    @Override
    public void insertIfNotExist(List<String> genres) throws DaoException {
        for (String genre : genres) {
            if (!genre.equals("")) {
                execute(INSERT_IF_NOT_EXISTS, genre, genre);
            }
        }
    }

    @Override
    public void removeById(long id) {

    }

}
