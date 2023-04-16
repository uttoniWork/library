package project.library.dto.response;

public class ClientLoginResponse {

    private long clientId;

    private String userName;

    public ClientLoginResponse(long clientId, String userName) {
        this.clientId = clientId;
        this.userName = userName;
    }

    public long getClientId() {
        return clientId;
    }

    public String getUserName() {
        return userName;
    }
}
