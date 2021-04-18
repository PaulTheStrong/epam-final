package com.epam.web.filter;

import com.epam.web.enitity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Page access filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String command = request.getParameter("command");
        if (user == null && !("login".equals(command) || "register".equals(command))) {
            servletRequest.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(servletRequest, servletResponse);
            return;
        }

        if (user != null && ("login".equals(command) || "register".equals(command))) {
            request.setAttribute("errorMessage", "error.relogin");
            servletRequest.getRequestDispatcher("WEB-INF/pages/info.jsp").forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
