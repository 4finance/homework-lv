package io.fourfinanceit.service.impl;

import io.fourfinanceit.repository.ClientRepository;
import io.fourfinanceit.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
