package test.epam.web.mapper;

import com.epam.web.enitity.Book;
import com.epam.web.enitity.Role;
import com.epam.web.enitity.User;
import com.epam.web.mapper.BookRowMapper;
import com.epam.web.mapper.UserRowMapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapperTest {

    private static final String ID_COLUMN = "id";
    private static final String TITLE_COLUMN = "title";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String IMAGE_PATH_COLUMN = "image_path";
    private static final String QUANTITY_COLUMN = "quantity";

    private static final long ID_EXPECTED = 1L;
    private static final String TITLE_EXPECTED = "title";
    private static final String DESCRIPTION_EXPECTED = "description";
    private static final String IMAGE_PATH_EXPECTED = "image_path";
    private static final int QUANTITY_EXPECTED = 100;


    @Test
    public void testBookRowMap() throws SQLException {

        BookRowMapper bookRowMapper = new BookRowMapper();
        ResultSet mock = Mockito.mock(ResultSet.class);
        Mockito.when(mock.getLong(ID_COLUMN)).thenReturn(ID_EXPECTED);
        Mockito.when(mock.getString(DESCRIPTION_COLUMN)).thenReturn(DESCRIPTION_EXPECTED);
        Mockito.when(mock.getString(TITLE_COLUMN)).thenReturn(TITLE_EXPECTED);
        Mockito.when(mock.getString(IMAGE_PATH_COLUMN)).thenReturn(IMAGE_PATH_EXPECTED);
        Mockito.when(mock.getInt(QUANTITY_COLUMN)).thenReturn(QUANTITY_EXPECTED);

        Book book = bookRowMapper.map(mock);
        Assert.assertEquals(book.getId(), ID_EXPECTED);
        Assert.assertEquals(book.getDescription(), DESCRIPTION_EXPECTED);
        Assert.assertEquals(book.getImagePath(), IMAGE_PATH_EXPECTED);
        Assert.assertEquals(book.getTitle(), TITLE_EXPECTED);
        Assert.assertEquals(book.getQuantity(), QUANTITY_EXPECTED);
    }

}
