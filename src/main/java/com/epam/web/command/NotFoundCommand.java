package com.epam.web.command;

import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotFoundCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.setAttribute("errorMessage", "error.pageNotExist");
        return CommandResult.forward("WEB-INF/pages/info.jsp");
    }
}
