package com.epam.web.dao;

import com.epam.web.enitity.BookOrder;
import com.epam.web.enitity.BookOrderStatus;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.BookOrderRowMapper;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookOrderDaoImpl extends AbstractDao<BookOrder> implements BookOrderDao {

    private static final String TABLE_NAME = "book_orders";

    private static final String FIND_ALL_BY_USER_ID = "select * from book_orders where user_id = ? ORDER BY FIELD(status, 'ORDERED', 'READ_ROOM', 'IN_HAND', 'RETURNED')";
    private static final String COUNT_ALL_BY_USER_ID = "SELECT count(*) as count from book_orders where user_id = ?";
    private static final String FIND_ALL_ON_PAGE_BY_USER_ID = "SELECT * FROM book_orders WHERE user_id = ? ORDER BY FIELD(status, 'ORDERED', 'READ_ROOM', 'IN_HAND', 'RETURNED') LIMIT ?, ? ";
    private static final String FIND_ALL_BY_STATUS = "SELECT * FROM book_orders WHERE status = ?";
    private static final String FIND_ALL_BY_STATUS_AND_USER_ID = "SELECT * FROM book_orders WHERE status = ? AND user_id = ?";
    private static final String FIND_OVERDUED = "select * from book_orders where end_date<CURDATE() and status = 'IN_HAND'";
    private static final String FIND_OVERDUED_BY_USER_ID = "select * from book_orders where end_date<CURDATE() AND user_id = ? and status = 'IN_HAND'";
    private static final String CREATE_NEW_ORDER = "INSERT INTO book_orders (user_id, book_id) VALUES (?, ?)";
    private static final String UPDATE_STATUS = "UPDATE book_orders SET status = ?, start_date = ?, end_date = ? WHERE id = ?";
    private static final String REMOVE_BY_ID = "DELETE FROM book_orders WHERE id = ?";
    private static final String DELETE_MAPPINGS_BY_BOOK_ID =
            "DELETE _order\n" +
                    "FROM book_orders _order\n" +
                    "INNER JOIN books\n" +
                    "ON books.id=_order.book_id\n" +
                    "WHERE book_id=?";

    protected BookOrderDaoImpl(Connection connection) {
        super(connection, new BookOrderRowMapper(), TABLE_NAME);
    }

    @Override
    public void save(BookOrder entity) throws DaoException {
        execute(UPDATE_STATUS, entity.getOrderStatus().name(), entity.getStartDate(), entity.getEndDate(), entity.getId());
    }

    @Override
    public void removeById(long id) throws DaoException {
        execute(REMOVE_BY_ID, id);
    }

    @Override
    public List<BookOrder> findAllByUserId(long userId) throws DaoException {
        return executeQuery(FIND_ALL_BY_USER_ID, userId);
    }

    @Override
    public void createNewOrder(long userId, long bookId) throws DaoException {
        execute(CREATE_NEW_ORDER, userId, bookId);
    }

    public long countAllByUserId(long userId) throws DaoException{
        return count(COUNT_ALL_BY_USER_ID, userId);
    }

    @Override
    public void deleteMappingsByBookId(long bookId) throws DaoException {
        execute(DELETE_MAPPINGS_BY_BOOK_ID, bookId);
    }

    public List<BookOrder> findAllByStatus(BookOrderStatus status) throws DaoException {
        if (status != BookOrderStatus.OVERDUED) {
            return executeQuery(FIND_ALL_BY_STATUS, status.name());
        } else {
            return executeQuery(FIND_OVERDUED);
        }
    }

    public List<BookOrder> findAllByStatusAndUserId(BookOrderStatus status, long userId) throws DaoException {
        if (status != BookOrderStatus.OVERDUED) {
            return executeQuery(FIND_ALL_BY_STATUS_AND_USER_ID, status.name(), userId);
        } else {
            return executeQuery(FIND_OVERDUED_BY_USER_ID, userId);
        }
    }

    public List<BookOrder> findRecordsOnPageByUserId(long userId, long pageIndex, long elementsOnPage) throws DaoException {
        long startIndex = pageIndex * elementsOnPage;
        return executeQuery(FIND_ALL_ON_PAGE_BY_USER_ID, userId, startIndex, elementsOnPage);
    }
}
