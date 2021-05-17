package com.epam.web.dao;

import com.epam.web.enitity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.UserRowMapper;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private final static String TABLE_NAME = "users";
    private final static String LOGIN_QUERY = "SELECT * FROM users WHERE login=? AND password_hash=MD5(?)";
    private final static String ADD_NEW_USER = "INSERT INTO users (login, name, surname, password_hash) VALUES (?, ?, ?, MD5(?))";
    private final static String UPDATE_USER = "UPDATE users SET login=?, name=?, surname=?, role=?, blocked=? WHERE id=?";

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
        execute(UPDATE_USER, entity.getLogin(), entity.getName(), entity.getSurname(), entity.getRole().toString(), entity.isBlocked(), entity.getId());
    }

    @Override
    public void addUser(User user, String password) throws DaoException {
        execute(ADD_NEW_USER, user.getLogin(), user.getName(), user.getSurname(), password);
    }

    @Override
    public void removeById(long id) {
        throw new UnsupportedOperationException();
    }
}
