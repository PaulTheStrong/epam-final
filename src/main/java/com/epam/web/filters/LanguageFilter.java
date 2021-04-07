package com.epam.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class LanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String lang = request.getParameter("lang");
        if (lang != null) {
            HttpSession session = request.getSession();
            session.setAttribute("language", lang);
        }
        filterChain.doFilter(servletRequest, servletResponse);
     }

    @Override
    public void destroy() {

    }
}
