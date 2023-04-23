package project.library.service;

import org.springframework.stereotype.Service;
import project.library.dto.request.ClientLoginRequest;
import project.library.dto.request.ClientRequest;
import project.library.dto.response.ClientLoginResponse;
import project.library.exception.ClientNotExistException;
import project.library.model.Client;
import project.library.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(ClientRequest clientRequest) {

        final Client client = new Client(clientRequest.getUserName(), clientRequest.getEmail(), clientRequest.getPassword());

        return clientRepository.save(client);
    }

    public ClientLoginResponse loginClient(ClientLoginRequest clientLoginRequest) {

        final Client clientFound = checkClientExists(clientLoginRequest.getEmail(), clientLoginRequest.getPassword());

        return new ClientLoginResponse(clientFound.getClientId(), clientFound.getUserName());

    }

    private Client checkClientExists(String email, String password){

        return clientRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new ClientNotExistException("Cliente não existe, corrija email e/ou senha!"));
    }

    public Client findClient(Long clientId){
        return clientRepository.findById(clientId).get();
    }
}
