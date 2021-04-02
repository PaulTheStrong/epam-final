package com.epam.web.dao;

import com.epam.web.conncetion.ConnectionPool;
import com.epam.web.exceptions.DaoException;

public class DaoHelperFactory {

    public DaoHelper create() throws DaoException {
        return new DaoHelper(ConnectionPool.getInstance());
    }

}
