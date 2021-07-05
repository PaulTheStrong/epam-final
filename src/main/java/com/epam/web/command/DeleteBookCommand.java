package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class DeleteBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        BookService bookService = new BookService(new DaoHelperFactory());

        Optional<String> bookId = Optional.ofNullable(request.getParameter("bookId"));
        if (bookId.isPresent()) {
            long bookIdValue = Long.parseLong(bookId.get());
            bookService.deleteBookById(bookIdValue);
        }
        return CommandResult.redirect(request.getContextPath() + "/controller?command=admin");
    }
}
