package com.epam.web.dao;

import com.epam.web.enitity.Book;
import com.epam.web.enitity.BookOrder;
import com.epam.web.enitity.BookOrderStatus;
import com.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookOrderDao extends Dao<BookOrder> {

    List<BookOrder> findAllByUserId(long userId) throws DaoException;

    List<BookOrder> findAllByStatus(BookOrderStatus status) throws DaoException;

    List<BookOrder> findRecordsOnPageByUserId(long userId, long pageIndex, long elementsOnPage) throws DaoException;

    List<BookOrder> findAllByStatusAndUserId(BookOrderStatus status, long userId) throws DaoException;

    void createNewOrder(long userId, long bookId) throws DaoException;

    long countAllByUserId(long userId) throws DaoException;

    void deleteMappingsByBookId(long bookId) throws DaoException;
}
