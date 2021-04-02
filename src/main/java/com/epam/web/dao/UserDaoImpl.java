package com.epam.web.dao;

import com.epam.web.enitity.User;
import com.epam.web.exceptions.DaoException;
import com.epam.web.mappers.UserRowMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private final static String TABLE_NAME = "users";
    private final static String LOGIN_QUERY = "SELECT * FROM users WHERE login=? AND password_hash=MD5(?)";

    public UserDaoImpl(Connection connection) {
        super(connection, new UserRowMapper(), TABLE_NAME);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(
                LOGIN_QUERY,
                login,
                password);
    }

    @Override
    public void save(User entity) throws DaoException {

    }

    @Override
    public void removeById(long id) {

    }
}
