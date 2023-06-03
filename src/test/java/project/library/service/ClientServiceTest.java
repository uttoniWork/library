package project.library.service;

import org.junit.jupiter.api.Test;
import project.library.dto.request.ClientLoginRequest;
import project.library.dto.request.ClientRequest;
import project.library.dto.response.ClientLoginResponse;
import project.library.exception.BookAlreadyExistsException;
import project.library.exception.ClientAlreadyExistsException;
import project.library.exception.ClientNotExistException;
import project.library.model.Client;
import project.library.repository.ClientRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private static final Long CLIENT_ID = 1L;
    private static final String USERNAME = "Harry";
    private static final String PASSWORD = "12345";
    private static final String EMAIL = "harry@gmail.com";

    private final ClientRepository clientRepository = mock(ClientRepository.class);
    private final ClientService clientService = new ClientService(clientRepository);

    @Test
    public void shouldSaveClient() {

        when(clientRepository.findByEmailAndPasswordAndUserName(anyString(), anyString(), anyString())).thenReturn(Optional.empty());
        when(clientRepository.save(any(Client.class))).thenReturn(getClient());

        final Client actualClient = clientService.saveClient(getClientRequest());
        final Client expectedClient = getClient();

        assertThat(actualClient).usingRecursiveComparison().ignoringFields("dateCreated").isEqualTo(expectedClient);
        verify(clientRepository, times(1)).findByEmailAndPasswordAndUserName(anyString(), anyString(), anyString());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void shouldNotSaveBecauseItAlreadyExists(){

        when(clientRepository.findByEmailAndPasswordAndUserName(anyString(), anyString(), anyString())).thenReturn(Optional.of(getClient()));

        final ClientAlreadyExistsException actualClientAlreadyExistsException = assertThrows(ClientAlreadyExistsException.class, () -> {
            clientService.saveClient(getClientRequest());
        });

        final ClientAlreadyExistsException expectedClientAlreadyExistsException = getClientAlreadyExistsException();

        assertThat(actualClientAlreadyExistsException).usingRecursiveComparison().isEqualTo(expectedClientAlreadyExistsException);
        verify(clientRepository, times(1)).findByEmailAndPasswordAndUserName(anyString(), anyString(), anyString());
    }

    @Test
    public void shouldLoginClient() {
        when(clientRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(Optional.of(getClient()));

        final ClientLoginResponse actualClient = clientService.loginClient(getClientLoginRequest());
        final ClientLoginResponse expectedClient = getClientLoginResponse();

        assertThat(actualClient).usingRecursiveComparison().isEqualTo(expectedClient);
        verify(clientRepository, times(1)).findByEmailAndPassword(anyString(), anyString());
    }

    @Test
    public void shouldNotLoginBecauseClientDoesNotExist(){

        when(clientRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        final ClientNotExistException actualClientNotExistsException = assertThrows(ClientNotExistException.class, () -> {
            clientService.loginClient(getClientLoginRequest());
        });

        final ClientNotExistException expectedClientNotExistsException = getClientNotExistException();

        assertThat(actualClientNotExistsException).usingRecursiveComparison().isEqualTo(expectedClientNotExistsException);
        verify(clientRepository, times(1)).findByEmailAndPassword(anyString(), anyString());
    }

    @Test
    public void findClient() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(getClient()));

        final Client actualClient = clientService.findClient(CLIENT_ID);
        final Client expectedClient = getClient();

        assertThat(actualClient).usingRecursiveComparison().ignoringFields("dateCreated").isEqualTo(expectedClient);
        verify(clientRepository, times(1)).findById(anyLong());
    }

    private ClientRequest getClientRequest(){
        return new ClientRequest(USERNAME, EMAIL, PASSWORD);
    }

    private Client getClient(){
        final Client client = new Client(USERNAME, EMAIL, PASSWORD);
        client.setClientId(CLIENT_ID);

        return client;
    }

    private ClientLoginRequest getClientLoginRequest() {
        return new ClientLoginRequest(EMAIL, PASSWORD);
    }

    private ClientAlreadyExistsException getClientAlreadyExistsException() {
        return new ClientAlreadyExistsException("Cliente já cadastrado!");
    }

    private ClientLoginResponse getClientLoginResponse() {
        return new ClientLoginResponse(CLIENT_ID, USERNAME);
    }

    private ClientNotExistException getClientNotExistException() {
        return new ClientNotExistException("Cliente não existe, corrija email e/ou senha!");
    }
}