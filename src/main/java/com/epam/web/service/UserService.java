package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.UserDao;
import com.epam.web.enitity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.List;
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

    public void register(User user) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            UserDao userDao = daoHelper.createUserDao();
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> getById(long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> getAllUsers() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void save(User user) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            UserDao userDao = daoHelper.createUserDao();
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
