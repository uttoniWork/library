package project.library.dto.request;

import java.util.List;

public class BookRegistrationRequest {

    private long clientId;
    private final String title;
    private final String author;
    private final String coverImage;
    private final List<String> genres;
    private final String editor;
    private final short releaseYear;

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
