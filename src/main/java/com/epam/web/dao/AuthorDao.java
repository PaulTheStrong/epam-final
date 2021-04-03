package com.epam.web.dao;

import com.epam.web.enitity.Author;
import com.epam.web.exceptions.DaoException;

import java.util.List;

public interface AuthorDao {

    List<Author> getAllByBookId(long bookId) throws DaoException;

    List<Author> getAll() throws DaoException;

}