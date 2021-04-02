package com.epam.web.commands;

import com.epam.web.conncetion.ConnectionPool;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.UserDaoImpl;
import com.epam.web.service.UserService;

public class CommandFactory {

    public Command create(String type) {
        switch (type) {
            case "login":
                 return new LoginCommand(new UserService(new DaoHelperFactory()));
            case "logout":
                return new LogoutCommand();
            case "books":
                return new GetBooksCommand();
            case "showPage":
                return new ShowPageCommand();
            default:
                throw new IllegalArgumentException("Unknown comand" +  type);
        }
    }

}
