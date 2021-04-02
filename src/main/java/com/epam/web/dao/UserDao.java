package com.epam.web.dao;

import com.epam.web.enitity.User;
import com.epam.web.exceptions.DaoException;

import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

}
