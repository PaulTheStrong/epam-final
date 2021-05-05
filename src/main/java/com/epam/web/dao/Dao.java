package com.epam.web.dao;

import com.epam.web.enitity.Identifiable;
import com.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Interface, that contains mostly often used method to access database.
 * @param <T> Any class that has getId() method.
 */
public interface Dao<T extends Identifiable> {

    /**
     * @return List of all records in database
     * @throws DaoException when SQLException happens.
     */
    List<T> findAll() throws DaoException;

    /**
     * @return list containing <b>elementsOnPage</b> elements with offset <b>pageIndex * elementsOnPage</b>.
     * @throws DaoException when SQLException happens.
     */
    List<T> findRecordsOnPage(long pageIndex, long elementsOnPage) throws DaoException;

    /**
     * @param id - identifier of record in database.
     * @return Optional with record, if it exists in database, otherwise Optional.empty().
     * @throws DaoException when SQLException happens.
     */
    Optional<T> findById(long id) throws DaoException;

    /**
     * @return quantity of all records in database.
     * @throws DaoException when SQLException happens.
     */
    long countAll() throws DaoException;

    /**
     * Create new record in database if record with entity.getId() doesn't exists. Otherwise update
     * this record.
     * @param entity - object to store.
     * @throws DaoException when SQLException happens.
     */
    void save(T entity) throws DaoException;

    /**
     * Remove record in database with specified id if exists.
     * @param id - identifier of deleted record.
     * @throws DaoException when SQLException happens
     */
    void removeById(long id) throws DaoException;
}
