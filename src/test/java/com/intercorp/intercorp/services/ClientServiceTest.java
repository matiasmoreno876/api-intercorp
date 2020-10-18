package com.intercorp.intercorp.services;

import com.intercorp.intercorp.dao.ClientDao;
import com.intercorp.intercorp.dao.interfaces.IClientDao;
import com.intercorp.intercorp.models.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ClientServiceTest {

    @InjectMocks
    ClientService clientService;

    @Mock
    ClientDao clientDao;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void getKpis_return_a_map_with_containt_averageAge() throws ParseException {

        List<Client> clients = mockClients();
        when(clientDao.getAll()).thenReturn(clients);

        assertEquals(28.5, clientService.getKpis().get("promedio_edad"));
    }

    @Test
    void getKpis_return_a_map_with_containt_standar_deviation() throws ParseException {

        List<Client> clients = mockClients();
        when(clientDao.getAll()).thenReturn(clients);

        assertEquals(1.5, clientService.getKpis().get("deviacion_estandar"));
    }

    @Test
    void getAllClients_returns_empty_when_there_are_no_clients_in_the_database() {
        when(clientDao.getAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(Collections.EMPTY_LIST, clientService.getAll());
    }

    private List<Client> mockClients() throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Matias", "Moreno", 27, simpleDateFormat.parse("1992-11-06")));
        clients.add(new Client("Juan", "Perez", 30, simpleDateFormat.parse("1990-10-06")));

        return clients;
    }
}
