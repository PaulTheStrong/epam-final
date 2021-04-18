package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.enitity.Role;
import com.epam.web.enitity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if (request.getMethod().equals("POST")) {
            String login = request.getParameter("login");
            boolean ok = true;
            if (login.isEmpty()) {
                ok = false;
                request.setAttribute("loginError", "error.register.login");
            }

            String name = request.getParameter("name");
            if (name.isEmpty()) {
                ok = false;
                request.setAttribute("nameError", "error.register.name");
            }

            String surname = request.getParameter("surname");
            if (surname.isEmpty()) {
                ok = false;
                request.setAttribute("surnameError", "error.register.surname");
            }

            String password = request.getParameter("password");
            String confirmation = request.getParameter("password-confirm");

            if (password.isEmpty()) {
                ok = false;
                request.setAttribute("passwordError", "error.register.password.emptyPassword");
            } else if (!verify(password)) {
                ok = false;
                request.setAttribute("passwordError", "error.register.password.passwordRestriction");
            } else if (!password.equals(confirmation)) {
                ok = false;
                request.setAttribute("passwordError", "error.register.password.notEquals");
            }

            User user = new User(0, login, name, surname, Role.READER);
            request.setAttribute("user", user);

            if (ok) {
                UserService userService = new UserService(new DaoHelperFactory());
                userService.register(user);
                return CommandResult.redirect(request.getContextPath() + "/controller?command=login");
            } else {
                return CommandResult.forward("WEB-INF/pages/register.jsp");
            }
        }
        return CommandResult.forward("WEB-INF/pages/register.jsp");
    }

    private boolean verify(String password) {
        return password.length() >= 6;
    }
}
