package com.epam.web.dao;

import com.epam.web.enitity.User;
import com.epam.web.exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    Optional<User> findById(long userId) throws DaoException;

    void addUser(User user, String password) throws DaoException;

}
