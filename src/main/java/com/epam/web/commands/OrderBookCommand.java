package com.epam.web.commands;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.enitity.Book;
import com.epam.web.exceptions.ServiceException;
import com.epam.web.service.BookService;
import com.epam.web.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class OrderBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if (!request.getMethod().equals("POST")) {
            return CommandResult.redirect(request.getContextPath() + "/controller?command=library");
        }

        HttpSession session = request.getSession();

        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        OrderService orderService = new OrderService(daoHelperFactory);
        BookService bookService = new BookService(daoHelperFactory);
        long userId = Long.parseLong(request.getParameter("userId"));
        long bookId = Long.parseLong(request.getParameter("bookId"));
        orderService.createNewOrder(userId, bookId);
        Optional<Book> bookOptional = bookService.getById(bookId);
        bookOptional.ifPresent(book -> session.setAttribute("orderedBook", book));

        return CommandResult.redirect(request.getContextPath() + "/controller?command=library");
    }
}
