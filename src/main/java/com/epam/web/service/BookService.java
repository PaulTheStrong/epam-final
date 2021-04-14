package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.dto.BookDto;
import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;
import com.epam.web.enitity.Genre;
import com.epam.web.exceptions.DaoException;
import com.epam.web.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookService {

    private final DaoHelperFactory daoHelperFactory;

    public BookService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<BookDto> getAllBooks() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            List<Book> books = bookDao.findAll();
            return createBookDtoList(books, daoHelper);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<BookDto> getBooksOnPage(long pageIndex, long elementsOnPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            List<Book> booksByAuthorId = bookDao.findRecordsOnPage(pageIndex, elementsOnPage);
            return createBookDtoList(booksByAuthorId, daoHelper);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<BookDto> getBooksByAuthorId(long authorId, long pageIndex, long elementsOnPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            List<Book> booksByAuthorId = bookDao.findBooksByAuthorId(authorId, pageIndex, elementsOnPage);
            return createBookDtoList(booksByAuthorId, daoHelper);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<BookDto> getBooksByGenreId(long authorId, long pageIndex, long elementsOnPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            List<Book> booksByGenreId = bookDao.findBooksByGenreId(authorId, pageIndex, elementsOnPage);
            return createBookDtoList(booksByGenreId, daoHelper);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Author> getAllAuthors() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AuthorDao authorDao = daoHelper.createAuthorDao();
            return authorDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Genre> getAllGenres() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            GenreDao genreDao = daoHelper.createGenreDao();
            return genreDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public long countBooks() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            return bookDao.countAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public long countBooksByGenreId(long genreId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            return bookDao.countBooksByGenreId(genreId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public long countBooksByAuthorId(long authorId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            return bookDao.countBooksByAuthorId(authorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    private List<BookDto> createBookDtoList(List<Book> books, DaoHelper daoHelper) throws DaoException {
        List<BookDto> bookDtoList = new ArrayList<>();

        GenreDao genreDao = daoHelper.createGenreDao();
        AuthorDao authorDao = daoHelper.createAuthorDao();

        for (Book book : books) {
            long id = book.getId();
            List<Genre> genres = genreDao.findAllByBookId(id);
            List<Author> authors = authorDao.findAllByBookId(id);
            bookDtoList.add(new BookDto(book, genres, authors));
        }
        return bookDtoList;
    }

    public Optional<Book> getById(long bookId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao dao = daoHelper.createBookDao();
            return dao.findById(bookId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
