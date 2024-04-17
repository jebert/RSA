package com.jebert.rsa.entities.Clients.service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jebert.rsa.entities.Clients.model.Client;
import com.jebert.rsa.entities.Clients.repository.ClientRepository;
import com.jebert.rsa.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findAddressById(UUID id) {
        return Optional.of(clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Client Not Found with ID:" + id.toString())));
    }

    public List<Client> findAllAddress() {
        return clientRepository.findAll();
    }

    public void delete(UUID uuid) {
        clientRepository.delete(findAddressById(uuid).get());
        ;
    }


}
