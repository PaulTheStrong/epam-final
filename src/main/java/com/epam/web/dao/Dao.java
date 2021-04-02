package com.epam.web.dao;

import com.epam.web.enitity.Identifiable;
import com.epam.web.exceptions.DaoException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {

    List<T> getAll() throws DaoException;

    Optional<T> getById(long id) throws DaoException;

    void save(T entity) throws DaoException;

    void removeById(long id);


}
