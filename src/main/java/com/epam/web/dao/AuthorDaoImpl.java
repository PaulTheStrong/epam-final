package com.epam.web.dao;

import com.epam.web.enitity.Author;
import com.epam.web.enitity.Genre;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.AuthorRowMapper;

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

    private static final String DELETE_MAPPINGS_BY_BOOK_ID =
            "DELETE author\n" +
                    "FROM book_author author\n" +
                    "INNER JOIN books\n" +
                    "ON books.id=author.book_id\n" +
                    "WHERE book_id=?";

    private static final String INSERT_IF_NOT_EXISTS =
            "INSERT INTO authors (name, surname)\n" +
                    "SELECT * FROM (SELECT ? as _name, ? as _surname) AS tmp\n" +
                    "WHERE NOT EXISTS (\n" +
                    "    SELECT name, surname FROM authors WHERE upper(name) = upper(?) and upper(surname)=upper(?)\n" +
                    ") LIMIT 1";

    private static final String MAP_AUTHOR_WITH_BOOK =
            "INSERT INTO book_author (author_id,  book_id)\n" +
                    "\tSELECT id, ? FROM authors WHERE upper(name)=upper(?) AND upper(surname)=upper(?)";

    private static final String SELECT_ALL_WHERE_BOOK_ATTACHED =
            "SELECT a.id AS id, a.name AS name, a.surname as surname " +
                    "FROM authors AS A " +
                    "INNER JOIN book_author AS b_a " +
                    "ON b_a.author_id=a.id " +
                    "GROUP BY name, surname";

    public AuthorDaoImpl(Connection connection) {
        super(connection, new AuthorRowMapper(), TABLE_NAME);
    }

    @Override
    public List<Author> findAllByBookId(long bookId) throws DaoException {
        return executeQuery(GET_ALL_BY_BOOK_ID, bookId);
    }

    public List<Author> findAllWhereBookAttached() throws DaoException {
        return executeQuery(SELECT_ALL_WHERE_BOOK_ATTACHED);
    }

    @Override
    public void save(Author entity) throws DaoException {

    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public void deleteMappingsByBookId(long bookId) throws DaoException {
        execute(DELETE_MAPPINGS_BY_BOOK_ID, bookId);
    }

    @Override
    public void mapAuthorsWithBookId(List<Author> authors, long bookId) throws DaoException {
        for (Author author : authors) {
            execute(MAP_AUTHOR_WITH_BOOK, bookId, author.getName(), author.getSurname());
        }
    }

    @Override
    public void insertIfNotExist(List<Author> authors) throws DaoException {
        for (Author author : authors) {
            execute(INSERT_IF_NOT_EXISTS, author.getName(), author.getSurname(), author.getName(), author.getSurname());
        }
    }
}
