package br.com.bibliotech.domains;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String pages;
    private int genreId;
    private int authorId;
    private String deletedReason;

    public Book() {
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPages() {
        return pages;
    }
    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getGenreId() {
        return genreId;
    }
    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getDeletedReason() {
        return deletedReason;
    }
    public void setDeletedReason(String deletedReason) {
        this.deletedReason = deletedReason;
    }
}