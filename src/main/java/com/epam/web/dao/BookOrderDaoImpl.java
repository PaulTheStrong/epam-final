package com.epam.web.dao;

import com.epam.web.enitity.BookOrder;
import com.epam.web.exceptions.DaoException;
import com.epam.web.mappers.BookOrderMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookOrderDaoImpl extends AbstractDao<BookOrder> implements BookOrderDao {

    private static final String TABLE_NAME = "book_orders";

    private static final String FIND_ALL_BY_USER_ID = "select * from book_orders where user_id = ?";
    private static final String CREATE_NEW_ORDER = "INSERT INTO book_orders (user_id, book_id) VALUES (?, ?)";
    private static final String COUNT_ALL_BY_USER_ID = "SELECT count(*) as count from book_orders where user_id = ?";

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
    public List<BookOrder> findAllByUserId(long userId) throws DaoException {
        return executeQuery(FIND_ALL_BY_USER_ID, userId);
    }

    @Override
    public void createNewOrder(long userId, long bookId) throws DaoException {
        try (PreparedStatement statement = createStatement(CREATE_NEW_ORDER, userId, bookId)) {
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public long countAllByUserId(long userId) throws DaoException{
        return count(COUNT_ALL_BY_USER_ID, userId);
    }
}
