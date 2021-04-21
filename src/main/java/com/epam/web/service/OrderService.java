package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.dto.BookDto;
import com.epam.web.dto.OrderDto;
import com.epam.web.enitity.*;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private final DaoHelperFactory daoHelperFactory;

    public OrderService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<BookOrder> getById(long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            return bookOrderDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<OrderDto> getOrdersByStatus(BookOrderStatus status) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            List<BookOrder> orders = bookOrderDao.findAllByStatus(status);
            return fillOrderDtos(daoHelper, orders);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<OrderDto> getOrdersByStatusAndUserId(BookOrderStatus status, long userId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            List<BookOrder> orders = bookOrderDao.findAllByStatusAndUserId(status, userId);
            return fillOrderDtos(daoHelper, orders);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateOrder(BookOrder bookOrder) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            BookDao bookDao = daoHelper.createBookDao();
            BookOrderStatus orderStatus = bookOrder.getOrderStatus();
            if (orderStatus == BookOrderStatus.RETURNED) {
                daoHelper.startTransaction();
                bookOrderDao.save(bookOrder);
                bookDao.increaseQuantityById(bookOrder.getBookId());
                daoHelper.endTransaction();
            } else {
                bookOrderDao.save(bookOrder);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<OrderDto> getAllByUserId(long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();

            List<BookOrder> bookOrders = bookOrderDao.findAllByUserId(id);
            return fillOrderDtos(daoHelper, bookOrders);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<OrderDto> getOrdersOnPageByUserId(long userId, long pageIndex, long elementsOnPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            List<BookOrder> bookOrders = bookOrderDao.findRecordsOnPageByUserId(userId, pageIndex, elementsOnPage);
            return fillOrderDtos(daoHelper, bookOrders);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void createNewOrder(long userId, long bookId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {

            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            BookDao bookDao = daoHelper.createBookDao();
            daoHelper.startTransaction();

            bookOrderDao.createNewOrder(userId, bookId);
            bookDao.decreaseQuantityById(bookId);

            daoHelper.endTransaction();

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
    
    public void cancelOrder(BookOrder order) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            BookDao bookDao = daoHelper.createBookDao();
            long bookId = order.getBookId();
            long orderId = order.getId();

            daoHelper.startTransaction();
            bookDao.increaseQuantityById(bookId);
            bookOrderDao.removeById(orderId);
            daoHelper.endTransaction();

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void removeById(long orderId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            BookOrderDao bookOrderDao = daoHelper.createBookOrderDao();
            bookOrderDao.removeById(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private List<OrderDto> fillOrderDtos(DaoHelper daoHelper, List<BookOrder> bookOrders) throws DaoException {
        List<OrderDto> bookOrderDtos = new ArrayList<>();
        BookDao bookDao = daoHelper.createBookDao();
        UserDao userDao = daoHelper.createUserDao();
        GenreDao genreDao = daoHelper.createGenreDao();
        AuthorDao authorDao = daoHelper.createAuthorDao();
        for (BookOrder bookOrder : bookOrders) {
            long bookId = bookOrder.getBookId();
            long userId = bookOrder.getUserId();
            Book book = bookDao.findById(bookId).get();
            User user = userDao.findById(userId).get();
            List<Genre> genres = genreDao.findAllByBookId(bookId);
            List<Author> authors = authorDao.findAllByBookId(bookId);
            BookDto bookDto = new BookDto(book, genres, authors);
            bookOrderDtos.add(new OrderDto(bookOrder, bookDto, user));
        }
        return bookOrderDtos;
    }
}
