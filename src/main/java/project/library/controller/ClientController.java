package project.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.library.dto.ClientLoginRequest;
import project.library.dto.ClientRequest;
import project.library.model.Client;
import project.library.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> postClient(@RequestBody ClientRequest clientRequest) {
        Client savedClient = clientService.saveClient(clientRequest);

        return ResponseEntity.ok(savedClient);
    }

    public ResponseEntity<Long> loginClient(@RequestBody ClientLoginRequest clientLoginRequest) {
        Long clientId = clientService.loginClient(clientLoginRequest);

        return clientId;
    }
}
