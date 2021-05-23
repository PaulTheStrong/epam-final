package com.epam.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter that sets right encoding so that every
 * character is shown in correct way.
 */
public class CharsetFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("requestEncoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
