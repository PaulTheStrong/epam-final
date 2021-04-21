package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.service.UserService;
import com.epam.web.validator.BookValidator;

public class CommandFactory {

    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";
    private static final String LIBRARY = "library";
    private static final String SHOW_PAGE = "showPage";
    private static final String PROFILE = "profile";
    private static final String ORDER_BOOK = "orderBook";
    private static final String LIBRARIAN = "librarian";
    private static final String ADMIN = "admin";
    private static final String EDIT_BOOK = "editBook";
    private static final String EDIT_USER = "editUser";

    public Command create(String type) {
        switch (type) {
            case LOGIN:
                 return new LoginCommand(new UserService(new DaoHelperFactory()));
            case LOGOUT:
                return new LogoutCommand();
            case LIBRARY:
                return new ShowLibraryCommand();
            case SHOW_PAGE:
                return new ShowPageCommand();
            case PROFILE:
                return new ProfileCommand();
            case ORDER_BOOK:
                return new OrderBookCommand();
            case LIBRARIAN:
                return new LibrarianCommand();
            case ADMIN:
                return new AdminCommand();
            case EDIT_BOOK:
                return new EditBookCommand(new BookValidator());
            case EDIT_USER:
                return new EditUserCommand();
            case "cancelOrder":
                return new CancelOrderCommand();
            case "deleteBook":
                return new DeleteBookCommand();
            default:
                throw new IllegalArgumentException("Unknown command: " +  type);
        }
    }

}
