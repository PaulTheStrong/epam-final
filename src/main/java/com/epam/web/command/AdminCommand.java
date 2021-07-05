package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dto.BookDto;
import com.epam.web.enitity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.BookService;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class AdminCommand implements Command {

    private static final String USERS = "users";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        BookService bookService = new BookService(daoHelperFactory);
        UserService userService = new UserService(daoHelperFactory);

        String edit = Optional.ofNullable(request.getParameter("edit")).orElse("books");
        if (edit.equals(USERS)) {
            List<User> users = userService.getAllUsers();
            request.setAttribute(USERS, users);
            return CommandResult.forward("WEB-INF/pages/admin-users.jsp");
        } else {
            List<BookDto> bookList = bookService.getAllBooks();
            request.setAttribute("bookList", bookList);
            return CommandResult.forward("WEB-INF/pages/admin-books.jsp");
        }
    }
}
