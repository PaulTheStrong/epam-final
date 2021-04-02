package com.epam.web.mappers;

import com.epam.web.enitity.Author;
import com.epam.web.enitity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String SURNAME_COLUMN = "surname";

    @Override
    public Author map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID_COLUMN);
        String name = resultSet.getString(NAME_COLUMN);
        String surname = resultSet.getString(SURNAME_COLUMN);

        return new Author(id, name, surname);
    }
}
