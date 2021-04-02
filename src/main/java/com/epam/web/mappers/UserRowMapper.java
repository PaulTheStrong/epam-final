package com.epam.web.mappers;

import com.epam.web.enitity.Role;
import com.epam.web.enitity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class UserRowMapper implements RowMapper<User> {

    private static final String ID_COLUMN = "id";
    private static final String ROLE_COLUMN = "role";
    private static final String LOGIN_COLUMN = "login";
    private static final String PASSWORD_HASH_COLUMN = "password_hash";
    private static final String NAME_COLUMN = "name";
    private static final String SURNAME_COLUMN = "surname";

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Role role = Role.valueOf(resultSet.getString(ROLE_COLUMN).toUpperCase(Locale.ROOT));
        long id = resultSet.getLong(ID_COLUMN);
        String login = resultSet.getString(LOGIN_COLUMN);
        String name = resultSet.getString(NAME_COLUMN);
        String surname = resultSet.getString(SURNAME_COLUMN);
        return new User(id, login, name, surname, role);
    }
}
