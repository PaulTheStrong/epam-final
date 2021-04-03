package com.epam.web.dao;

import com.epam.web.enitity.BookOrder;
import com.epam.web.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookOrderDao extends Dao<BookOrder> {

    List<BookOrder> getAllByUserId(long userId) throws DaoException;

    @Override
    List<BookOrder> getAll() throws DaoException;

    @Override
    Optional<BookOrder> getById(long id) throws DaoException;

}
