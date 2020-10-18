package com.intercorp.intercorp.services;

import com.intercorp.intercorp.dao.ClientDao;
import com.intercorp.intercorp.models.Client;
import com.intercorp.intercorp.services.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<String, Double> getKpis() {
        HashMap<String, Double> kpiValues = new HashMap<>();
        List<Client> clients = this.clientDao.getAll();

        if (clients.isEmpty()) {
            return kpiValues;
        }

        List<Integer> allAge = getAllAge(clients);
        kpiValues.put("promedio_edad:", getAverageAge(allAge));
        kpiValues.put("deviacion_estandar:", getStandardDesviation(allAge));

        return kpiValues;
    }

    private List<Integer> getAllAge(List<Client> clients) {
        return clients
                .stream()
                .mapToInt(Client::getAge)
                .boxed()
                .collect(Collectors.toList());

    }

    private Double getAverageAge(List<Integer> allAge) {
        return allAge
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    private Double getStandardDesviation(List<Integer> allAge) {
        double average = getAverageAge(allAge);
        double standardDeviation = 0.0;
        for (double age : allAge) {
            standardDeviation += Math.pow(age - average, 2);
        }

        return Math.sqrt(standardDeviation / allAge.size());
    }
}
