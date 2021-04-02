package com.epam.web.commands;

import com.epam.web.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = request.getParameter("page");
        return CommandResult.forward(page);
    }
}
