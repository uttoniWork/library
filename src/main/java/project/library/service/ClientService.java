package project.library.service;

import org.springframework.stereotype.Service;
import project.library.dto.request.ClientLoginRequest;
import project.library.dto.request.ClientRequest;
import project.library.dto.response.ClientLoginResponse;
import project.library.exception.ClientAlreadyExistsException;
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

        checkClientAlreadyExists(clientRequest.getEmail(), clientRequest.getPassword(), clientRequest.getUserName());

        return clientRepository.save(new Client(clientRequest.getUserName(), clientRequest.getEmail(), clientRequest.getPassword()));
    }

    public ClientLoginResponse loginClient(ClientLoginRequest clientLoginRequest) {

        final Client clientFound = checkClientExists(clientLoginRequest.getEmail(), clientLoginRequest.getPassword());

        return new ClientLoginResponse(clientFound.getClientId(), clientFound.getUserName());
    }

    public Client findClient(Long clientId){
        return clientRepository.findById(clientId).get();
    }

    private Client checkClientExists(String email, String password){
        return clientRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new ClientNotExistException("Cliente não existe, corrija email e/ou senha!"));
    }

    private void checkClientAlreadyExists(String email, String password, String userName){
        if(clientRepository.findByEmailAndPasswordAndUserName(email, password, userName).isPresent())
            throw new ClientAlreadyExistsException("Cliente já cadastrado!");
    }
}
