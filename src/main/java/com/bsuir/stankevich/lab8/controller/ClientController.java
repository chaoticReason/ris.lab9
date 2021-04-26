package com.bsuir.stankevich.lab8.controller;

import com.bsuir.stankevich.lab8.entity.Client;
import com.bsuir.stankevich.lab8.exception.NotFoundException;
import com.bsuir.stankevich.lab8.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "rest/users")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients(){
        return clientService.getClients();
    }

    @GetMapping("{id}")
    public Client getClient(@PathVariable Long id){
        try {
            return clientService.getClientById(id);
        }catch(Exception e){
            throw new NotFoundException();
        }
    }

    @PostMapping
    public Client addNewClient(@RequestBody Client client){
        clientService.addNewClient(client);
        return clientService.getClientByEmail(client.getEmail());
    }

    @PutMapping("{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client){
        try {
            System.out.println(client.toString());
            clientService.updateClient(id, client);
            return clientService.getClientById(id);
        }catch(Exception e){
            e.printStackTrace();
            throw new NotFoundException();
        }
    }

    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable Long id){
        try {
            clientService.deleteClient(id);
        }catch(Exception e){
            throw new NotFoundException();
        }
    }
}
