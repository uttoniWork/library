package project.library.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.library.dto.request.ClientLoginRequest;
import project.library.dto.request.ClientRequest;
import project.library.dto.response.ClientLoginResponse;
import project.library.model.Client;
import project.library.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> postClient(@RequestBody ClientRequest clientRequest) {

        Client savedClient = clientService.saveClient(clientRequest);

        return ResponseEntity.ok(savedClient);
    }

    @GetMapping("/login")
    public ResponseEntity<ClientLoginResponse> loginClient(@RequestParam String email,
                                                           @RequestParam String password) {

        ClientLoginResponse clientLoginResponse = clientService.loginClient(new ClientLoginRequest(email, password));

        return ResponseEntity.ok(clientLoginResponse);
    }
}
