package com.epam.web.dao;

import com.epam.web.enitity.Genre;
import com.epam.web.exception.DaoException;

import java.util.List;

public interface GenreDao {

    List<Genre> findAllByBookId(long bookId) throws DaoException;

    List<Genre> findAll() throws DaoException;

    List<Genre> findAllWhereBookAttached() throws DaoException;

    void deleteMappingsByBookId(long bookId) throws DaoException;

    void mapGenresWithBookId(List<String> genres, long bookId) throws DaoException;

    void insertIfNotExist(List<String> genres) throws DaoException;

}
