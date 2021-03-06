package com.epam.web.mapper;

import com.epam.web.enitity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";

    @Override
    public Genre map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID_COLUMN);
        String name = resultSet.getString(NAME_COLUMN);

        return new Genre(id, name);
    }
}
