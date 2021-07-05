package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.enitity.BookOrder;
import com.epam.web.enitity.BookOrderStatus;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CancelOrderCommand implements Command {

    private static final String ORDER_CANCEL_SUCCESS = "order.cancel.success";
    private static final String SUCCESS_MESSAGE = "successMessage";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        OrderService orderService = new OrderService(new DaoHelperFactory());

        Optional<String> orderIdString = Optional.ofNullable(request.getParameter("orderId"));
        if (orderIdString.isPresent()) {
            long orderId = Long.parseLong(orderIdString.get());
            Optional<BookOrder> orderOptional = orderService.getById(orderId);
            if (orderOptional.isPresent()) {
                BookOrder bookOrder = orderOptional.get();
                if (bookOrder.getOrderStatus() == BookOrderStatus.ORDERED) {
                    orderService.cancelOrder(bookOrder);
                    request.setAttribute(SUCCESS_MESSAGE, ORDER_CANCEL_SUCCESS);
                }
            }
        }

        return CommandResult.forward("WEB-INF/pages/profile.jsp");
    }
}
