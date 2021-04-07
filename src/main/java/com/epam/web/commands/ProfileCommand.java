package com.epam.web.commands;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dto.BookOrderDto;
import com.epam.web.enitity.BookOrder;
import com.epam.web.enitity.User;
import com.epam.web.exceptions.ServiceException;
import com.epam.web.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfileCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long userId = user.getId();

        OrderService orderService = new OrderService(new DaoHelperFactory());

        List<BookOrderDto> userOrders = orderService.getAllByUserId(userId);
        request.setAttribute("userOrders", userOrders);

        return CommandResult.forward("WEB-INF/pages/profile.jsp");
    }
}
