package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dto.OrderDto;
import com.epam.web.enitity.BookOrderStatus;
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

        String requestedStatus = Optional.ofNullable(request.getParameter("requestedStatus")).orElse("ORDERED");
        request.setAttribute("requestedStatus", requestedStatus);
        BookOrderStatus status = BookOrderStatus.valueOf(requestedStatus);

        OrderService orderService = new OrderService(new DaoHelperFactory());

        List<OrderDto> userOrders;
        userOrders = orderService.getOrdersByStatusAndUserId(status, userId);
        request.setAttribute("userOrders", userOrders);


        return CommandResult.forward("WEB-INF/pages/profile.jsp");
    }
}
