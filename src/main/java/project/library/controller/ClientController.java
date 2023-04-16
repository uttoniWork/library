package project.library.controller;

import org.example.dto.ClientLoginRequest;
import org.example.dto.ClientRequest;
import org.example.model.Client;
import org.example.service.ClientService;

//@RestController
//@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //    @PostMapping
//    public ResponseEntity<Client> postClient(@RequestBody ClientRequest clientRequest){
    public Client postClient(ClientRequest clientRequest){
        Client savedClient = clientService.saveClient(clientRequest);

//        return ResponseEntity.ok(savedClient);
        return savedClient;
    }

//    public ResponseEntity<Long> loginClient(@RequestBody ClientLoginRequest clientLoginRequest){
    public Long loginClient(ClientLoginRequest clientLoginRequest){
        Long clientId = clientService.loginClient(clientLoginRequest);

        return clientId;
    }
}
