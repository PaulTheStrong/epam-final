package com.epam.web.mapper;

import com.epam.web.enitity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    private static final String ID_COLUMN = "id";
    private static final String TITLE_COLUMN = "title";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String IMAGE_PATH_COLUMN = "image_path";
    private static final String QUANTITY_COLUMN = "quantity";

    @Override
    public Book map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID_COLUMN);
        String title = resultSet.getString(TITLE_COLUMN);
        String description = resultSet.getString(DESCRIPTION_COLUMN);
        String imagePath = resultSet.getString(IMAGE_PATH_COLUMN);
        int quantity = resultSet.getInt(QUANTITY_COLUMN);

        return new Book(id, title, description, imagePath, quantity);
    }
}
