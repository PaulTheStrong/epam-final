package com.epam.web.dao;

import com.epam.web.enitity.Identifiable;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * AbstractDao is a class with basic info logic for
 * making SQL Requests, creating statements, executing
 * them e.g.
 */
public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private final Connection connection;
    private final RowMapper<T> mapper;
    private final String tableName;

    protected AbstractDao(Connection connection, RowMapper<T> mapper, String tableName) {
        this.connection = connection;
        this.mapper = mapper;
        this.tableName = tableName;
    }

    /**
     * Binds all the needed params into prepared statement,
     * then executes it, returning List of found objects.
     * @param query SQL Query to be executed.
     * @param params Params to bind into query.
     * @return List of objects found in database.
     */
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

    /**
     * Binds param into query.
     */
    protected PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement;
    }

    /**
     * @return all the object in database in specified table Mapped by proper mapper.
     */
    public List<T> findAll() throws DaoException {
        String query = "SELECT * FROM " + tableName;
        return executeQuery(query);
    }

    /**
     * @return elementsOnPage objects starting from index pageIndex * elementsOnPage
     * @param pageIndex number of page
     * @param elementsOnPage count of elements on one page.
     */
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

    /**
     * Used to perform queries that return an one column
     * result with integer value. For example count() function.
     */
    protected long count(String query, Object... parameters) throws DaoException {
        try (PreparedStatement statement = createStatement(query, parameters);
            ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getLong("count");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Counts all records in specified database
     */
    @Override
    public long countAll() throws DaoException {
        String query = "SELECT COUNT(*) as count FROM " + tableName;
        return count(query);
    }

    /**
     * Checks if giver query returns one records.
     * If so, returns that record. If records more than one,
     * throws a DaoException. Otherwise returns Optional.empty().
     */
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

    /**
     * Executes given query with params and returns nothing.
     */
    protected void execute(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
