package project.library.dto.request;

public class ClientLoginRequest {

    private String email;
    private String password;

    public ClientLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
