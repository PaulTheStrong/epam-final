package test.epam.web.mapper;

import com.epam.web.enitity.Genre;
import com.epam.web.mapper.GenreRowMapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapperTest {

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";

    private static final long ID_EXPECTED = 1L;
    private static final String NAME_EXPECTED = "name";


    @Test
    public void testGenreRowMap() throws SQLException {

        GenreRowMapper genreRowMapper = new GenreRowMapper();
        ResultSet mock = Mockito.mock(ResultSet.class);
        Mockito.when(mock.getLong(ID_COLUMN)).thenReturn(ID_EXPECTED);
        Mockito.when(mock.getString(NAME_COLUMN)).thenReturn(NAME_EXPECTED);

        Genre genre = genreRowMapper.map(mock);
        Assert.assertEquals(genre.getId(), ID_EXPECTED);
        Assert.assertEquals(genre.getName(), NAME_EXPECTED);
    }

}
