package com.epam.web.dao;

import com.epam.web.enitity.Identifiable;
import com.epam.web.exceptions.DaoException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {

    List<T> findAll() throws DaoException;

    List<T> findRecordsOnPage(long pageIndex, long elementsOnPage) throws DaoException;

    Optional<T> findById(long id) throws DaoException;

    long countAll() throws DaoException;

    void save(T entity) throws DaoException;

    void removeById(long id) throws DaoException;


}
