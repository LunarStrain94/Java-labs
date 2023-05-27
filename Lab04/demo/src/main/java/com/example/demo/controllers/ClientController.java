package com.example.demo.controllers;

import com.example.demo.config.ClientEnter;
import com.example.demo.models.Client;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "/all")
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Client getByID(@PathVariable("id") String id) {
        return clientService.getById(id);
    }

    @PostMapping(path = "/add")
    public void addClient(@RequestBody ClientEnter client) { clientService.addNewClient(client); }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteClient(@PathVariable("id") String id) { clientService.deleteById(id); }
}
