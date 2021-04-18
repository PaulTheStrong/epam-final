package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.service.UserService;
import com.epam.web.validator.BookValidator;

public class CommandFactory {

    public Command create(String type) {
        switch (type) {
            case "login":
                 return new LoginCommand(new UserService(new DaoHelperFactory()));
            case "logout":
                return new LogoutCommand();
            case "library":
                return new ShowLibraryCommand();
            case "showPage":
                return new ShowPageCommand();
            case "profile":
                return new ProfileCommand();
            case "orderBook":
                return new OrderBookCommand();
            case "librarian":
                return new LibrarianCommand();
            case "admin":
                return new AdminCommand();
            case "editBook":
                return new EditBookCommand(new BookValidator());
            default:
                throw new IllegalArgumentException("Unknown command: " +  type);
        }
    }

}
