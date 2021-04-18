package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dto.OrderDto;
import com.epam.web.enitity.BookOrder;
import com.epam.web.enitity.BookOrderStatus;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class LibrarianCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        String requestedStatus = Optional.ofNullable(request.getParameter("requestedStatus")).orElse("ORDERED");
        request.setAttribute("requestedStatus", requestedStatus);

        OrderService orderService = new OrderService(daoHelperFactory);
        String method = request.getMethod();

        if (method.equals("POST")) {
            return post(request, requestedStatus, orderService);
        } else {
            List<OrderDto> orderDtos = orderService.getOrdersByStatus(BookOrderStatus.valueOf(requestedStatus));
            request.setAttribute("userOrders", orderDtos);
            return CommandResult.forward("WEB-INF/pages/librarian.jsp");
        }
    }

    private CommandResult post(HttpServletRequest request, String requestedStatus, OrderService orderService) throws ServiceException {
        long orderId = Long.parseLong(request.getParameter("orderId"));
        Optional<BookOrder> optionalOrder = orderService.getById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new ServiceException("Order with id " + orderId + " not found!");
        }
        BookOrder order = optionalOrder.get();
        BookOrderStatus newStatus = BookOrderStatus.valueOf(request.getParameter("newStatus"));
        if (order.getOrderStatus() == BookOrderStatus.ORDERED && newStatus == BookOrderStatus.IN_HAND) {
            order.setStartDate(new Date());
            order.setEndDate(Date.from(LocalDateTime.now()
                    .plusMonths(1)
                    .atZone(ZoneId.systemDefault())
                    .toInstant()));
        }
        order.setOrderStatus(newStatus);
        orderService.updateOrder(order);
        return CommandResult.redirect(request.getContextPath() + "/controller?command=librarian&requestedStatus=" + requestedStatus);
    }
}
