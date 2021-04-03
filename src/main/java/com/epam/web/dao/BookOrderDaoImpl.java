package com.epam.web.dao;

import com.epam.web.enitity.BookOrder;
import com.epam.web.exceptions.DaoException;
import com.epam.web.mappers.BookOrderMapper;
import com.epam.web.mappers.RowMapper;

import java.sql.Connection;
import java.util.List;

public class BookOrderDaoImpl extends AbstractDao<BookOrder> implements BookOrderDao {

    private static final String TABLE_NAME = "book_orders";

    private static final String GET_ALL_BY_USER_ID = "select * from book_orders where user_id = ?";

    protected BookOrderDaoImpl(Connection connection) {
        super(connection, new BookOrderMapper(), TABLE_NAME);
    }

    @Override
    public void save(BookOrder entity) throws DaoException {

    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public List<BookOrder> getAllByUserId(long userId) throws DaoException {
        return executeQuery(GET_ALL_BY_USER_ID, userId);
    }
}
