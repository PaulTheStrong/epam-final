package com.epam.web;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandler extends HttpServlet {

    private static final Logger LOGGER = Logger.getRootLogger();

    // Method to handle GET method request.
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Analyze the servlet exception
        Throwable throwable = (Throwable)
                request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)
                request.getAttribute("javax.servlet.error.status_code");

        LOGGER.warn((throwable != null ? throwable.getMessage() : " ") + " Status code: " + statusCode);
        request.setAttribute("statusCode", statusCode);
        request.getRequestDispatcher("WEB-INF/pages/error.jsp").forward(request, response);

    }

    // Method to handle POST method request.
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}