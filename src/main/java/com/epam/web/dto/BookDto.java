package com.epam.web.dto;

import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;
import com.epam.web.enitity.Genre;

import java.util.List;

public class BookDto {

    private final Book book;
    private List<Genre> genres;
    private List<Author> authors;

    public BookDto(Book book, List<Genre> genres, List<Author> authors) {
        this.book = book;
        this.genres = genres;
        this.authors = authors;
    }

    public long getId() {
        return book.getId();
    }

    public void setId(long id) {
        book.setId(id);
    }

    public String getTitleRu() {
        return book.getTitleRu();
    }

    public void setTitleRu(String titleRu) {
        book.setTitleRu(titleRu);
    }

    public String getTitleEn() {
        return book.getTitleEn();
    }

    public void setTitleEn(String titleEn) {
        book.setTitleEn(titleEn);
    }

    public String getDescriptionRu() {
        return book.getDescriptionRu();
    }

    public void setDescriptionRu(String descriptionRu) {
        book.setDescriptionRu(descriptionRu);
    }

    public String getDescriptionEn() {
        return book.getDescriptionEn();
    }

    public void setDescriptionEn(String descriptionEn) {
        book.setDescriptionEn(descriptionEn);
    }

    public String getImagePath() {
        return book.getImagePath();
    }

    public void setImagePath(String imagePath) {
        book.setImagePath(imagePath);
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
