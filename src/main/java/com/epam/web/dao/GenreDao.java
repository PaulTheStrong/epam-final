package com.epam.web.dao;

import com.epam.web.enitity.Genre;
import com.epam.web.exceptions.DaoException;

import java.util.List;

public interface GenreDao {

    List<Genre> getAllByBookId(long bookId) throws DaoException;

    List<Genre> getAll() throws DaoException;

}
