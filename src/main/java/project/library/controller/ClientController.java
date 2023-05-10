package project.library.controller;

import jdk.swing.interop.SwingInterOpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


//    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> postClient(@RequestBody ClientRequest clientRequest) {

        Client savedClient = clientService.saveClient(clientRequest);
        System.out.println("cadastro: nome=" + clientRequest.getUserName());

//        logger.info("m=postClient msg=cliente cadastrado, id=[{}], username=[{}], email=[{}], date=[{}]", savedClient.getClientId(), savedClient.getUserName(), savedClient.getEmail(), savedClient.getDateCreated());

        return ResponseEntity.ok(savedClient);
    }

    @GetMapping("/login")
    public ResponseEntity<ClientLoginResponse> loginClient(@RequestParam String email,
                                                           @RequestParam String password) {

        System.out.println("requisição de login recebido com: email=" + email + ", senha: " + password);
//        logger.info("m=loginClient msg=requisição de login recebido com: email=[{}], senha=[{}]", email, password);

        ClientLoginResponse clientLoginResponse = clientService.loginClient(new ClientLoginRequest(email, password));

        System.out.println("requisição de login recebido com: id=" + clientLoginResponse.getClientId() + ", username: " + clientLoginResponse.getUserName());
//        logger.info("m=loginClient msg=cliente loggado: id=[{}], username=[{}]", clientLoginResponse.getClientId(), clientLoginResponse.getUserName());

        return ResponseEntity.ok(clientLoginResponse);
    }
}
