package com.intercorp.intercorp.services.interfaces;

import com.intercorp.intercorp.models.Client;

import java.util.List;
import java.util.Map;

public interface IClientService {

    List<Client> getAll();

    Client save(Client client);

    Map<String, Double> getKpis();

}
