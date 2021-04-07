package com.epam.web.dto;

import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;
import com.epam.web.enitity.Genre;

import java.util.List;

public class BookDto {

    private Book book;
    private List<Genre> genres;
    private List<Author> authors;

    public BookDto(Book book, List<Genre> genres, List<Author> authors) {
        this.book = book;
        this.genres = genres;
        this.authors = authors;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
