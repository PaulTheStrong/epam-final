package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.dto.BookDto;
import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;
import com.epam.web.enitity.Genre;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

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

    public List<Author> getAllAuthorsWhereBookAttached() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AuthorDao authorDao = daoHelper.createAuthorDao();
            return authorDao.findAllWhereBookAttached();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Genre> getAllGenresWhereBookAttached() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            GenreDao genreDao = daoHelper.createGenreDao();
            return genreDao.findAllWhereBookAttached();
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

    public void save(Book book, List<Author> authors, List<String> genres) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            GenreDao genreDao = daoHelper.createGenreDao();
            AuthorDao authorDao = daoHelper.createAuthorDao();

            daoHelper.startTransaction();
            bookDao.save(book);

            genreDao.deleteMappingsByBookId(book.getId());
            genreDao.insertIfNotExist(genres);
            genreDao.mapGenresWithBookId(genres, book.getId());

            authorDao.deleteMappingsByBookId(book.getId());
            authorDao.insertIfNotExist(authors);
            authorDao.mapAuthorsWithBookId(authors, book.getId());

            daoHelper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteBookById(long bookId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao bookDao = daoHelper.createBookDao();
            GenreDao genreDao = daoHelper.createGenreDao();
            AuthorDao authorDao = daoHelper.createAuthorDao();
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();

            daoHelper.startTransaction();

            genreDao.deleteMappingsByBookId(bookId);
            authorDao.deleteMappingsByBookId(bookId);
            bookOrderDao.deleteMappingsByBookId(bookId);
            bookDao.removeById(bookId);
            daoHelper.endTransaction();

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    private List<BookDto> createBookDtoList(List<Book> books, DaoHelper daoHelper) throws ServiceException {
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : books) {
            bookDtoList.add(createBookDto(book, daoHelper));
        }
        return bookDtoList;
    }

    private BookDto createBookDto(Book book, DaoHelper daoHelper) throws ServiceException {
        GenreDao genreDao = daoHelper.createGenreDao();
        AuthorDao authorDao = daoHelper.createAuthorDao();

        try {
            long id = book.getId();

            List<Genre> genres = genreDao.findAllByBookId(id);
            List<Author> authors = authorDao.findAllByBookId(id);

            return new BookDto(book, genres, authors);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<BookDto> getById(long bookId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookDao dao = daoHelper.createBookDao();
            Optional<Book> bookOptional = dao.findById(bookId);
            Optional<BookDto> bookDtoOptional = Optional.empty();
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                BookDto bookDto = createBookDto(book, daoHelper);
                bookDtoOptional = Optional.of(bookDto);
            }
            return bookDtoOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Long getLastId() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            BookDao bookDao = daoHelper.createBookDao();
            Optional<Long> lastId = bookDao.findLastId();
            if (lastId.isPresent()) {
                return lastId.get();
            } else {
                throw new ServiceException("Last id not found!");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
