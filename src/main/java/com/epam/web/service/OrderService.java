package com.epam.web.service;

import com.epam.web.dao.BookDao;
import com.epam.web.dao.BookOrderDao;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.enitity.BookOrder;
import com.epam.web.exceptions.DaoException;
import com.epam.web.exceptions.ServiceException;

import java.util.List;

public class OrderService {

    private final DaoHelperFactory daoHelperFactory;

    public OrderService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<BookOrder> getAllByUserId(long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            return bookOrderDao.getAllByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

}
