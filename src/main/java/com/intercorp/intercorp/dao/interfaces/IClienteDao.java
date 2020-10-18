package com.intercorp.intercorp.dao.interfaces;

import com.intercorp.intercorp.models.Client;

import java.util.List;

public interface IClienteDao {
        List<Client> getAll();
        Client save(Client client);
}
