package com.intercorp.intercorp.controllers;

import com.intercorp.intercorp.dtos.NewClientRequets;
import com.intercorp.intercorp.models.Client;
import com.intercorp.intercorp.services.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private ClientService clientService;
    private ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Obtiene todos los clientes",
            response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clients = this.clientService.getAll();
        return (clients != null)
                ? ResponseEntity.ok().body(clients)
                : ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Obtiene las métricas de los clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/kpi")
    public ResponseEntity getKpiClients() {
        try {
            Map<String, Double> kpis = this.clientService.getKpis();
            return (!kpis.isEmpty())
                    ? ResponseEntity.ok().body(kpis)
                    : ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @ApiOperation(value = "Crea un nuevo cliente en BD POSTGRESQL")
    @PostMapping
    public ResponseEntity<?> postCreateClient(@Valid @RequestBody NewClientRequets newClientRequets) {

        try {
            Client client = clientService.save(convertToEntity(newClientRequets));
            return (client != null)
                    ? ResponseEntity.created(null).body(client)
                    : ResponseEntity.noContent().build();

        } catch (ParseException e) {
            return ResponseEntity.noContent().build();
        }
    }

    private Client convertToEntity(NewClientRequets newClientRequets) throws ParseException {
        return modelMapper.map(newClientRequets, Client.class);
    }

}
