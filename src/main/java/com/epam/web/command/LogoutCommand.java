package com.epam.web.command;

import com.epam.web.enitity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LogoutCommand implements Command {


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Optional<User> user = Optional.ofNullable((User) session.getAttribute("user"));
        if (user.isPresent()) {
            session.invalidate();
            request.setAttribute("successMessage", "success.logoutSuccess");
        } else {
            request.setAttribute("errorMessage", "error.logoutError");
        }
        return CommandResult.forward("WEB-INF/pages/info.jsp");
    }
}
