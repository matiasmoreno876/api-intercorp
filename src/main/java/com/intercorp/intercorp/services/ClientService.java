package com.intercorp.intercorp.services;

import com.intercorp.intercorp.dao.ClientDao;
import com.intercorp.intercorp.models.Client;
import com.intercorp.intercorp.services.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientService implements IClientService {

    private ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public List<Client> getAll() {
        return this.clientDao.getAll();
    }

    @Override
    public Client save(Client client) {
        return this.clientDao.save(client);
    }

    @Override
    public Map<String, Long> getKpis() {
        return null;
    }
}
