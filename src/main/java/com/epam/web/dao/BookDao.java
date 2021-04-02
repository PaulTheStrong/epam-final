package com.epam.web.dao;

import com.epam.web.enitity.Book;
import com.epam.web.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    List<Book> getBooksByAuthorId(long authorId) throws DaoException;
    List<Book> getBooksByGenreId(long genreId) throws DaoException;
    List<Book> getBooksByTitle(String title);
    List<Book> getBooksByDescription(String description);

    List<Book> getAll() throws DaoException;

}
