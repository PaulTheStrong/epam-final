package com.epam.web.dao;

import com.epam.web.enitity.Book;
import com.epam.web.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    List<Book> getBooksByAuthorId(long authorId) throws DaoException;
    List<Book> getBooksByGenreId(long genreId) throws DaoException;

    List<Book> getBooksByAuthorId(long authorId, long pageIndex, long elementsOnPage) throws DaoException;
    List<Book> getBooksByGenreId(long genreId, long pageIndex, long elementsOnPage) throws DaoException;

    List<Book> getBooksByTitle(String title);
    List<Book> getBooksByDescription(String description);

    List<Book> getRecordsOnPage(long pageIndex, long elementsOnPage) throws DaoException;

    List<Book> getAll() throws DaoException;
    Optional<Book> getById(long id) throws DaoException;

    long countAll() throws DaoException;
    long countBooksByGenreId(long genreId) throws DaoException;
    long countBooksByAuthorId(long bookId) throws DaoException;

}
