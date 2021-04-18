package com.epam.web.dao;

import com.epam.web.enitity.Author;
import com.epam.web.enitity.Genre;
import com.epam.web.exception.DaoException;

import java.util.List;

public interface AuthorDao {

    List<Author> findAllByBookId(long bookId) throws DaoException;

    List<Author> findAll() throws DaoException;

    void deleteMappingsByBookId(long bookId) throws DaoException;

    void mapAuthorsWithBookId(List<String> names, List<String> surnames, long bookId) throws DaoException;

    List<Author> findAllWhereBookAttached() throws DaoException;

    void insertIfNotExist(List<String> names, List<String> surnames) throws DaoException;

}
