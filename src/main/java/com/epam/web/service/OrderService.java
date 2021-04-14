package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.dto.BookDto;
import com.epam.web.dto.OrderDto;
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

    public List<OrderDto> getAllByUserId(long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            BookDao bookDao = daoHelper.createBookDao();
            AuthorDao authorDao = daoHelper.createAuthorDao();
            GenreDao genreDao = daoHelper.createGenreDao();

            List<BookOrder> bookOrders = bookOrderDao.findAllByUserId(id);
            List<OrderDto> bookOrderDtos = fillOrderDtos(bookDao, authorDao, genreDao, bookOrders);
            return bookOrderDtos;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<OrderDto> getOrdersOnPageByUserId(long userId, long pageIndex, long elementsOnPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            BookDao bookDao = daoHelper.createBookDao();
            AuthorDao authorDao = daoHelper.createAuthorDao();
            GenreDao genreDao = daoHelper.createGenreDao();

            List<BookOrder> bookOrders = bookOrderDao.findRecordsOnPage(pageIndex, elementsOnPage);
            return fillOrderDtos(bookDao, authorDao, genreDao, bookOrders);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void createNewOrder(long userId, long bookId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {

            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            bookOrderDao.createNewOrder(userId, bookId);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public long countOrdersByUserId(long userId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            return bookOrderDao.countAllByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private List<OrderDto> fillOrderDtos(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao, List<BookOrder> bookOrders) throws DaoException {
        List<OrderDto> bookOrderDtos = new ArrayList<>();
        for (BookOrder bookOrder : bookOrders) {
            long bookId = bookOrder.getBookId();
            Book book = bookDao.findById(bookId).get();
            List<Genre> genres = genreDao.findAllByBookId(bookId);
            List<Author> authors = authorDao.findAllByBookId(bookId);
            BookDto bookDto = new BookDto(book, genres, authors);
            bookOrderDtos.add(new OrderDto(bookOrder, bookDto));
        }
        return bookOrderDtos;
    }
}
