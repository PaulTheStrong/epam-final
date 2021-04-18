package com.epam.web;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.command.CommandResult;
import com.epam.web.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class Controller extends HttpServlet {

    private static final CommandFactory commandFactory = new CommandFactory();

    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }

    public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandType = request.getParameter("command");
        request.setCharacterEncoding("UTF-8");
        if("".equals(commandType)) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        Command command = commandFactory.create(commandType);
        CommandResult result = null;
        try {
            result = command.execute(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        boolean isRedirect = result.isRedirect();
        String page = result.getPage();
        if (!isRedirect) {
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            response.sendRedirect(page);
        }
    }
}
