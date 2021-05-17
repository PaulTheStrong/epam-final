package com.epam.web.command;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.enitity.Role;
import com.epam.web.enitity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class EditUserCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService = new UserService(new DaoHelperFactory());

        String idString = request.getParameter("id");
        String action = request.getParameter("action");
        if (idString != null && action != null) {
            long id = Long.parseLong(idString);
            Optional<User> userOptional = userService.getById(id);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (user.getRole() == Role.ADMIN) {
                    request.setAttribute("errorMessage", "error.adminModification");
                } else {
                    if (action.equals("block")) {
                        user.setBlocked(true);
                        userService.save(user);
                    } else if (action.equals("unblock")) {
                        user.setBlocked(false);
                        userService.save(user);
                    } else {
                        request.setAttribute("errorMessage", "error.invalidAction");
                    }
                }
            } else {
                request.setAttribute("errorMessage", "error.noSuchUser");
            }
        }

        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);

        return CommandResult.forward("WEB-INF/pages/edit-user.jsp");
    }
}
