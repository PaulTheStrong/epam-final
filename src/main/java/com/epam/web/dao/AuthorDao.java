package com.epam.web.dao;

import com.epam.web.enitity.Author;
import com.epam.web.exception.DaoException;

import java.util.List;

public interface AuthorDao extends Dao<Author> {

    List<Author> findAllByBookId(long bookId) throws DaoException;

    void deleteMappingsByBookId(long bookId) throws DaoException;

    void mapAuthorsWithBookId(List<Author> authors, long bookId) throws DaoException;

    List<Author> findAllWhereBookAttached() throws DaoException;

    void insertIfNotExist(List<Author> authors) throws DaoException;

}
