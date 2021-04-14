package com.epam.web.dao;

import com.epam.web.enitity.Genre;
import com.epam.web.exceptions.DaoException;

import java.util.List;

public interface GenreDao {

    List<Genre> findAllByBookId(long bookId) throws DaoException;

    List<Genre> findAll() throws DaoException;

}
