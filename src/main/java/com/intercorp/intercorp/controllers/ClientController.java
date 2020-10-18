package com.intercorp.intercorp.controllers;

import com.intercorp.intercorp.dtos.ClientDto;
import com.intercorp.intercorp.models.Client;
import com.intercorp.intercorp.services.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    private ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clients = this.clientService.getAll();
        return (clients != null)
                ? ResponseEntity.ok().body(clients)
                : ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createClient(@RequestBody ClientDto clientDto) {

        try {
            Client client = clientService.save(convertToEntity(clientDto));
            return (client != null)
                    ? ResponseEntity.created(null).body(client)
                    : ResponseEntity.noContent().build();

        } catch (ParseException e) {
            return ResponseEntity.noContent().build();
        }
    }

    private Client convertToEntity(ClientDto clientDto) throws ParseException {
        return modelMapper.map(clientDto, Client.class);
    }

}
