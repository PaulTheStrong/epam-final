package com.epam.web.dto;

import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;
import com.epam.web.enitity.BookOrder;
import com.epam.web.enitity.Genre;

import java.util.List;

public class BookOrderDto {

    private BookOrder bookOrder;
    private BookDto bookDto;

    public BookOrderDto(BookOrder bookOrder, BookDto bookDto) {
        this.bookOrder = bookOrder;
        this.bookDto = bookDto;
    }

    public BookOrder getBookOrder() {
        return bookOrder;
    }

    public void setBookOrder(BookOrder bookOrder) {
        this.bookOrder = bookOrder;
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }
}
