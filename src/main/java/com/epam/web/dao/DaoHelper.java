package com.epam.web.dao;

import com.epam.web.conncetion.ConnectionPool;
import com.epam.web.conncetion.ProxyConnection;
import com.epam.web.exceptions.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) throws DaoException {
        this.connection = pool.getConnection();
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public BookDao createBookDao() {
        return new BookDaoImpl(connection, createAuthorDao(), createGenreDao());
    }

    public AuthorDao createAuthorDao() {
        return new AuthorDaoImpl(connection);
    }

    public GenreDao createGenreDao() {
        return new GenreDaoImpl(connection);
    }

    public void close() {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
