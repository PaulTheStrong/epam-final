package com.epam.web.mappers;

import com.epam.web.dao.AuthorDao;
import com.epam.web.dao.AuthorDaoImpl;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookRowMapper implements RowMapper<Book> {

    private static final String ID_COLUMN = "id";
    private static final String TITLE_EN_COLUMN = "title_en";
    private static final String TITLE_RU_COLUMN = "title_ru";
    private static final String DESCRIPTION_RU_COLUMN = "description_ru";
    private static final String DESCRIPTION_EN_COLUMN = "description_en";
    private static final String IMAGE_PATH_COLUMN = "image_path";

    @Override
    public Book map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID_COLUMN);
        String title_en = resultSet.getString(TITLE_EN_COLUMN);
        String title_ru = resultSet.getString(TITLE_RU_COLUMN);
        String description_ru = resultSet.getString(DESCRIPTION_RU_COLUMN);
        String description_en = resultSet.getString(DESCRIPTION_EN_COLUMN);
        String image_path = resultSet.getString(IMAGE_PATH_COLUMN);

        return new Book(id, title_ru, title_en, description_ru, description_en, image_path);
    }
}
