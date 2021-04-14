package com.epam.web.dto;

import com.epam.web.enitity.*;

import java.sql.Date;
import java.util.List;

public class OrderDto {

    private final BookOrder bookOrder;
    private final BookDto bookDto;

    public OrderDto(BookOrder bookOrder, BookDto bookDto) {
        this.bookOrder = bookOrder;
        this.bookDto = bookDto;
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

    public String getTitleRu() {
        return bookDto.getTitleRu();
    }

    public String getDescriptionRu() {
        return bookDto.getDescriptionRu();
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
}
