package com.epam.web.dto;

import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;
import com.epam.web.enitity.Genre;

import java.util.List;

public class BookDto {

    private final Book book;
    private final List<Genre> genres;
    private final List<Author> authors;

    public BookDto(Book book, List<Genre> genres, List<Author> authors) {
        this.book = book;
        this.genres = genres;
        this.authors = authors;
    }

    public long getId() {
        return book.getId();
    }

    public String getTitle() {
        return book.getTitle();
    }

    public String getDescription() {
        return book.getDescription();
    }

    public int getQuantity() {
        return book.getQuantity();
    }

    public String getImagePath() {
        return book.getImagePath();
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
