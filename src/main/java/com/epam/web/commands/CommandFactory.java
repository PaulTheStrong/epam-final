package com.epam.web.commands;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.service.UserService;

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
            default:
                throw new IllegalArgumentException("Unknown command: " +  type);
        }
    }

}
