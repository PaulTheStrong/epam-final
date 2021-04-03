package com.epam.web.mappers;

import com.epam.web.enitity.BookOrder;
import com.epam.web.enitity.BookOrderStatus;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class BookOrderMapper implements RowMapper<BookOrder> {

    private static final String ID_COLUMN = "id";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String BOOK_ID_COLUMN = "book_id";
    private static final String STATUS_COLUMN = "status";
    private static final String START_DATE_COLUMN = "start_date";
    private static final String END_DATE_COLUMN = "end_date";


    @Override
    public BookOrder map(ResultSet resultSet) throws SQLException {

        long bookOrderId = resultSet.getLong(ID_COLUMN);
        long userId = resultSet.getLong(USER_ID_COLUMN);
        long bookId = resultSet.getLong(BOOK_ID_COLUMN);
        Date startDate = resultSet.getDate(START_DATE_COLUMN);
        Date endDate = resultSet.getDate(END_DATE_COLUMN);
        BookOrderStatus bookOrderStatus = BookOrderStatus.valueOf(resultSet.getString(STATUS_COLUMN).toUpperCase(Locale.ROOT));

        return new BookOrder(bookOrderId, userId, bookId, bookOrderStatus, startDate, endDate);
    }
}
