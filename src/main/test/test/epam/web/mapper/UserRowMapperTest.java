package test.epam.web.mapper;

import com.epam.web.enitity.Role;
import com.epam.web.enitity.User;
import com.epam.web.mapper.UserRowMapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class UserRowMapperTest {

    private static final String ID_COLUMN = "id";
    private static final String ROLE_COLUMN = "role";
    private static final String LOGIN_COLUMN = "login";
    private static final String PASSWORD_HASH_COLUMN = "password_hash";
    private static final String NAME_COLUMN = "name";
    private static final String SURNAME_COLUMN = "surname";
    private static final String BLOCKED_COLUMN = "blocked";

    private static final String LOGIN_EXPECTED = "login";
    private static final String NAME_EXPECTED = "name";
    private static final String SURNAME_EXPECTED = "surname";
    private static final boolean BLOCKED_EXPECTED = true;
    private static final String ROLE_EXPECTED = "ADMIN";
    private static final long ID_EXPECTED = 1L;


    @Test
    public void testUserRowMap() throws SQLException {

        UserRowMapper userRowMapper = new UserRowMapper();
        ResultSet mock = Mockito.mock(ResultSet.class);
        Mockito.when(mock.getLong(ID_COLUMN)).thenReturn(ID_EXPECTED);
        Mockito.when(mock.getString(LOGIN_COLUMN)).thenReturn(LOGIN_EXPECTED);
        Mockito.when(mock.getString(NAME_COLUMN)).thenReturn(NAME_EXPECTED);
        Mockito.when(mock.getString(SURNAME_COLUMN)).thenReturn(SURNAME_EXPECTED);
        Mockito.when(mock.getBoolean(BLOCKED_COLUMN)).thenReturn(BLOCKED_EXPECTED);
        Mockito.when(mock.getString(ROLE_COLUMN)).thenReturn(ROLE_EXPECTED);

        User user = userRowMapper.map(mock);
        Assert.assertEquals(user.getId(), ID_EXPECTED);
        Assert.assertEquals(user.getLogin(), LOGIN_EXPECTED);
        Assert.assertEquals(user.getName(), NAME_EXPECTED);
        Assert.assertEquals(user.getSurname(), SURNAME_EXPECTED);
        Assert.assertEquals(user.isBlocked(), BLOCKED_EXPECTED);
        Assert.assertEquals(user.getRole(), Role.valueOf(ROLE_EXPECTED));
    }

}
