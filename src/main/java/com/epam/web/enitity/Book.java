package com.epam.web.enitity;

import java.util.List;

public class Book implements Identifiable {

    private long id;

    private String titleRu;
    private String titleEn;
    private String descriptionRu;
    private String descriptionEn;
    private String imagePath;

    List<Author> authors;
    List<Genre> genres;

    public Book(long id, String titleRu, String titleEn, String descriptionRu, String descriptionEn, String imagePath, List<Author> authors, List<Genre> genres) {
        this.id = id;
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.descriptionRu = descriptionRu;
        this.descriptionEn = descriptionEn;
        this.imagePath = imagePath;
        this.genres = genres;
        this.authors = authors;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
