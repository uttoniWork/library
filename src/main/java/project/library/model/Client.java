package project.library.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.time.LocalDateTime;

@Node("client")
public class Client {

    @Id
    @GeneratedValue
    private Long clientId;
    @Property
    private String userName;
    @Property
    private String email;
    @Property
    private String password;
    @Property
    private LocalDateTime dateCreated;

    public Client() {}

    public Client(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dateCreated = LocalDateTime.now();
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
}
