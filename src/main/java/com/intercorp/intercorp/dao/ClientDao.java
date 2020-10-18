package com.intercorp.intercorp.dao;

import com.intercorp.intercorp.dao.interfaces.IClientDao;
import com.intercorp.intercorp.models.Client;
import com.intercorp.intercorp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDao implements IClientDao {

    private ClientRepository clientRepository;

    @Autowired
    public ClientDao(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client save(Client client) {
        return this.clientRepository.save(client);
    }

}
