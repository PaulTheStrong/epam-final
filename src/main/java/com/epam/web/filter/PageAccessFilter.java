package com.epam.web.filter;

import com.epam.web.enitity.Role;
import com.epam.web.enitity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageAccessFilter implements Filter {

    public static final String GUEST = "guest";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initCommands();
    }

    private final Map<String, List<String>> accessibleCommands = new HashMap<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String role;
        if (user == null) {
            role = GUEST;
        } else {
            role = user.getRole().name();
        }
        String command = request.getParameter("command");
        if (!accessibleCommands.get(role).contains(command)) {
            if (role.equals(GUEST)) {
                request.getRequestDispatcher("WEB-INF/pages/login.jsp");
            } else {
                request.setAttribute("errorMessage", "error.notEnoughRights");
                request.getRequestDispatcher("WEB-INF/pages/info.jsp").forward(request, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private void initCommands() {
        accessibleCommands.put(GUEST, Arrays.asList("login", "pageNotFound"));
        accessibleCommands.put(Role.READER.name(), Arrays.asList("logout", "pageNotFound", "library", "showPage", "profile", "orderBook", "cancelOrder"));
        accessibleCommands.put(Role.LIBRARIAN.name(), Arrays.asList("logout", "pageNotFound", "library", "showPage", "librarian"));
        accessibleCommands.put(Role.ADMIN.name(), Arrays.asList("logout", "pageNotFound", "library", "showPage", "admin", "editBook", "editUser", "deleteBook"));
    }
}
