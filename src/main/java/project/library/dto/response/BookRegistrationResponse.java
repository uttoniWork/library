package project.library.dto.response;

import project.library.model.Genre;

import java.util.List;

public class BookRegistrationResponse {

    private String title;
    private String author;
    private String coverImage;
    private List<Genre> genres;
    private String editor;
    private Short releaseYear;

    public BookRegistrationResponse() {
    }

    public BookRegistrationResponse(String title, String author, String coverImage, List<Genre> genres, String editor, Short releaseYear) {
        this.title = title;
        this.author = author;
        this.coverImage = coverImage;
        this.genres = genres;
        this.editor = editor;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }
}
