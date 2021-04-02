package com.epam.web.commands;

import com.epam.web.enitity.User;
import com.epam.web.exceptions.DaoException;
import com.epam.web.exceptions.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String LOGIN_PAGE = "WEB-INF/pages/login.jsp";
    private static final String MAIN_PAGE = "WEB-INF/pages/main.jsp";

    private static final String invalidUsernameOrPasswordError = "error.invalidUsernameOrPassword";


    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter("login");
        String password = request.getParameter("password");
        if (username == null && password == null) {
            return CommandResult.forward(LOGIN_PAGE);
        }
        Optional<User> optionalUser = userService.login(username, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return CommandResult.redirect(request.getContextPath() + "/controller?command=books");
        } else {
            request.setAttribute("errorMessage", invalidUsernameOrPasswordError); //ErrorMessage в интренационализацию | FMT
            String login = request.getParameter("login");
            request.setAttribute("login", login);
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
