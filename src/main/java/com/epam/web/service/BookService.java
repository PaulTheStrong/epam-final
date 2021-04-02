package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;
import com.epam.web.enitity.Genre;
import com.epam.web.exceptions.DaoException;
import com.epam.web.exceptions.ServiceException;

import java.util.List;

public class BookService {

    private final DaoHelperFactory daoHelperFactory;

    public BookService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Book> getAllBooks() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            return bookDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Book> getBooksByAuthorId(long authorId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            return bookDao.getBooksByAuthorId(authorId);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Book> getBooksByGenreId(long authorId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            return bookDao.getBooksByGenreId(authorId);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Author> getAllAuthors() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AuthorDao authorDao = daoHelper.createAuthorDao();
            return authorDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Genre> getAllGenres() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            GenreDao genreDao = daoHelper.createGenreDao();
            return genreDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
