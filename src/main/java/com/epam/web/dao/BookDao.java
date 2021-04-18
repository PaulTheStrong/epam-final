package com.epam.web.dao;

import com.epam.web.enitity.Book;
import com.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    List<Book> findBooksByAuthorId(long authorId) throws DaoException;
    List<Book> findBooksByGenreId(long genreId) throws DaoException;

    List<Book> findBooksByAuthorId(long authorId, long pageIndex, long elementsOnPage) throws DaoException;
    List<Book> findBooksByGenreId(long genreId, long pageIndex, long elementsOnPage) throws DaoException;

    List<Book> findBooksByTitle(String title);
    List<Book> findBooksByDescription(String description);

    List<Book> findRecordsOnPage(long pageIndex, long elementsOnPage) throws DaoException;

    List<Book> findAll() throws DaoException;
    Optional<Book> findById(long id) throws DaoException;

    long countAll() throws DaoException;
    long countBooksByGenreId(long genreId) throws DaoException;
    long countBooksByAuthorId(long bookId) throws DaoException;

    Optional<Long> findLastId() throws DaoException;

    void increaseQuantityById(long id) throws DaoException;
    void decreaseQuantityById(long id) throws DaoException;

    void save(Book book) throws DaoException;
}
