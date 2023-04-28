package project.library.dto.request;

import project.library.model.Genre;

import java.util.List;

public class BookRegistrationRequest {

    private Long clientId;
    private String title;
    private String author;
    private String coverImage;
    private List<String> genres;
    private String editor;
    private short releaseYear;

    public BookRegistrationRequest() {
    }

    public BookRegistrationRequest(long clientId, String title, String author, String coverImage, List<String> genres, String editor, short releaseYear) {
        this.clientId = clientId;
        this.title = title;
        this.author = author;
        this.coverImage = coverImage;
        this.genres = genres;
        this.editor = editor;
        this.releaseYear = releaseYear;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getEditor() {
        return editor;
    }

    public short getReleaseYear() {
        return releaseYear;
    }
}
