package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.UserDaoImpl;
import com.epam.web.enitity.User;
import com.epam.web.exceptions.DaoException;
import com.epam.web.exceptions.ServiceException;

import java.util.Optional;

public class UserService {

    private final DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
