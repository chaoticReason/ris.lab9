package com.bsuir.stankevich.lab8;

import com.bsuir.stankevich.lab8.entity.Client;
import com.bsuir.stankevich.lab8.parser.Serializer;
import com.bsuir.stankevich.lab8.parser.Validation;
import com.bsuir.stankevich.lab8.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MainController {

    private final String xsdPath = "person.xsd";

    ClientService clientService;

    public MainController(@Autowired ClientService clientService){
        this.clientService = clientService;
    }

    public void addClient(String xmlPath) throws IOException {
        //validate file
        Validation v = new Validation();
        if ( !v.validateXMLSchema(xsdPath,xmlPath) )
            throw new IllegalStateException("Xml-file isn't valid");

        //read one client from the file
        Serializer serializer = new Serializer();
        Client client = serializer.readXmlClient(xmlPath);

        //add them to database
        clientService.addNewClient(client);
    }

    public void deleteClient(String xmlPath) throws IOException {
        //validate file
        Validation v = new Validation();
        if ( !v.validateXMLSchema(xsdPath,xmlPath) )
            throw new IllegalStateException("Xml-file isn't valid");

        //read one client from the file
        Serializer serializer = new Serializer();
        Client client = serializer.readXmlClient(xmlPath);

        //add them to database
        clientService.deleteClient(client.getEmail());
    }

    public Client updateClient(Long id, Client client) {
        clientService.updateClient(id, client);
        return clientService.getClientById(id);
    }

    public Client getClient(Long id) {
        return clientService.getClientById(id);
    }


    public void readClientFromDatabase(Long id) throws IOException {
        //get client from database
        Client client = clientService.getClientById(id);

        //deserialize
        Serializer serializer = new Serializer();
        System.out.println("Xml:\n" + serializer.clientToXml(client));
        System.out.println("Json:\n" + serializer.clientToJson(client));

    }
}
