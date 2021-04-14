package com.epam.web.dao;

import com.epam.web.enitity.Identifiable;
import com.epam.web.exceptions.DaoException;
import com.epam.web.mappers.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private final Connection connection;
    private final RowMapper<T> mapper;
    private final String tableName;

    protected AbstractDao(Connection connection, RowMapper<T> mapper, String tableName) {
        this.connection = connection;
        this.mapper = mapper;
        this.tableName = tableName;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = createStatement(query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                result.add(entity);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    protected PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement;
    }

    public List<T> findAll() throws DaoException {
        String query = "SELECT * FROM " + tableName;
        return executeQuery(query);
    }

    @Override
    public List<T> findRecordsOnPage(long pageIndex, long elementsOnPage) throws DaoException {
        long startIndex = pageIndex * elementsOnPage;
        String query = "SELECT * FROM " + tableName + " LIMIT " + startIndex + ", " + elementsOnPage;
        return executeQuery(query);
    }

    @Override
    public Optional<T> findById(long id) throws DaoException {
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        return executeForSingleResult(query, id);
    }

    protected long count(String query, Object... parameters) throws DaoException {
        try (PreparedStatement statement = createStatement(query, parameters);
            ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getLong("count");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public long countAll() throws DaoException {
        String query = "SELECT COUNT(*) as count FROM " + tableName;
        return count(query);
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> entities = executeQuery(query, params);
        if (entities.size() > 1) {
            throw new DaoException("There are more then one records in the table");
        } else if (entities.size() == 1) {
            return Optional.of(entities.get(0));
        } else {
            return Optional.empty();
        }
    }
}
