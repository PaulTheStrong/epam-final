package com.epam.web.command;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.enitity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditUserCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService = new UserService(new DaoHelperFactory());

        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);

        return CommandResult.forward("WEB-INF/pages/edit-users.jsp");
    }
}
