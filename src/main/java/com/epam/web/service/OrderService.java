package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.dto.BookDto;
import com.epam.web.dto.BookOrderDto;
import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;
import com.epam.web.enitity.BookOrder;
import com.epam.web.enitity.Genre;
import com.epam.web.exceptions.DaoException;
import com.epam.web.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final DaoHelperFactory daoHelperFactory;

    public OrderService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<BookOrderDto> getAllByUserId(long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            BookDao bookDao = daoHelper.createBookDao();
            AuthorDao authorDao = daoHelper.createAuthorDao();
            GenreDao genreDao = daoHelper.createGenreDao();

            List<BookOrder> bookOrders = bookOrderDao.getAllByUserId(id);
            List<BookOrderDto> bookOrderDtos = new ArrayList<>();
            for (BookOrder bookOrder : bookOrders) {
                long bookId = bookOrder.getBookId();
                Book book = bookDao.getById(bookId).get();
                List<Genre> genres = genreDao.getAllByBookId(bookId);
                List<Author> authors = authorDao.getAllByBookId(bookId);
                BookDto bookDto = new BookDto(book, genres, authors);
                bookOrderDtos.add(new BookOrderDto(bookOrder, bookDto));
            }
            return bookOrderDtos;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

}
