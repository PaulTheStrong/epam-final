package com.epam.web.mapper;

import com.epam.web.enitity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Translate a record from database into object
 * of type T
 * @param <T> the type of returned object.
 */
public interface RowMapper<T extends Identifiable> {

    T map(ResultSet resultSet) throws SQLException;

}
