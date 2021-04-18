package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dto.OrderDto;
import com.epam.web.enitity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ProfileCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long userId = user.getId();

        OrderService orderService = new OrderService(new DaoHelperFactory());

        long page = Long.parseLong(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int elementsOnPage = 5;
        boolean isLast;

        List<OrderDto> userOrders;
        userOrders = orderService.getOrdersOnPageByUserId(userId, page - 1, elementsOnPage);
        isLast = orderService.countOrdersByUserId(userId) <= page * elementsOnPage;
        request.setAttribute("userOrders", userOrders);

        request.setAttribute("currentPage", page);
        request.setAttribute("isLast", isLast);

        return CommandResult.forward("WEB-INF/pages/profile.jsp");
    }
}
