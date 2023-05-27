package com.example.demo.controllers;

import com.example.demo.config.ClientEnter;
import com.example.demo.config.DeviceEnter;
import com.example.demo.models.Client;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "/all")
    public String getAll(Model model) {
        model.addAttribute("clients", clientService.getAll());
        return "allClients.html";
    }

    @GetMapping(path = "/{id}")
    public Client getByID(@PathVariable("id") String id) {
        return clientService.getById(id);
    }

    @GetMapping("/add")
    public String clientForm(Model model) {
        model.addAttribute("client", new ClientEnter());
        return "addClient";
    }
    @PostMapping("/add")
    public String clientSubmit(@ModelAttribute ClientEnter clientEnter, Model model) {
        model.addAttribute("client", clientEnter);
        clientService.addNewClient(clientEnter);
        return "postClient";
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteClient(@PathVariable("id") String id) { clientService.deleteById(id); }
}
