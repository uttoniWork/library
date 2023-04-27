package project.library.dto.request;

public class BookLinkingRequest {

    private Long clientId;
    private Long bookId;

    public BookLinkingRequest() {
    }

    public BookLinkingRequest(Long clientId, Long bookId) {
        this.clientId = clientId;
        this.bookId = bookId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
