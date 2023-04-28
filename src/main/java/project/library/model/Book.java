package project.library.model;

import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.List;

@Node("book")
public class Book {

    @Id
    @GeneratedValue
    private Long bookId;
    @Property
    private String title;
    @Property
    private String author;
    @Property
    private String coverImage;
    @Relationship(type = "EH_DO_GENERO", direction = Relationship.Direction.OUTGOING)
    private List<Genre> genres;
    @Property
    private String editor;
    @Property
    private Short releaseYear;
    @Property
    private LocalDateTime dateCreated;
    @Relationship(type = "GOSTA_DO_LIVRO", direction = Relationship.Direction.INCOMING)
    private List<Client> clientList;

    public Book() {}

    public Book(String title, String author, String coverImage, List<Genre> genres, String editor, Short releaseYear, List<Client> clientList) {
        this.title = title;
        this.author = author;
        this.coverImage = coverImage;
        this.genres = genres;
        this.editor = editor;
        this.releaseYear = releaseYear;
        this.clientList = clientList;
        this.dateCreated = LocalDateTime.now();
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
