package com.epam.web.dto;

import com.epam.web.enitity.*;

import java.util.Date;
import java.util.List;

public class OrderDto {

    private final BookOrder bookOrder;
    private final BookDto bookDto;
    private final User user;

    public OrderDto(BookOrder bookOrder, BookDto bookDto, User user) {
        this.bookOrder = bookOrder;
        this.bookDto = bookDto;
        this.user = user;
    }

    public long getId() {
        return bookOrder.getId();
    }

    public long getUserId() {
        return bookOrder.getUserId();
    }

    public long getBookId() {
        return bookOrder.getBookId();
    }

    public BookOrderStatus getOrderStatus() {
        return bookOrder.getOrderStatus();
    }

    public Date getStartDate() {
        return bookOrder.getStartDate();
    }

    public Date getEndDate() {
        return bookOrder.getEndDate();
    }

    public String getTitle() {
        return bookDto.getTitle();
    }

    public String getDescription() {
        return bookDto.getDescription();
    }

    public String getImagePath() {
        return bookDto.getImagePath();
    }

    public List<Genre> getGenres() {
        return bookDto.getGenres();
    }

    public List<Author> getAuthors() {
        return bookDto.getAuthors();
    }

    public String getUserName() {
        return user.getName();
    }

    public String getUserSurname() {
        return user.getSurname();
    }

    public Role getUserRole() {
        return user.getRole();
    }
}
