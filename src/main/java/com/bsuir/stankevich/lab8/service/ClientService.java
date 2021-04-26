package com.bsuir.stankevich.lab8.service;

import com.bsuir.stankevich.lab8.entity.Client;
import com.bsuir.stankevich.lab8.entity.Country;
import com.bsuir.stankevich.lab8.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.bsuir.stankevich.lab8.myutils.Checker.*;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final CountryService countryService;
    private final CityService cityService;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         CityService cityService,
                         CountryService countryService) {
        this.clientRepository = clientRepository;
        this.cityService = cityService;
        this.countryService = countryService;
    }


    public List<Client> getClients()
    {
        return clientRepository.findAll();
    }

    public void addNewClient(Client client) throws IllegalStateException
    {
        if(!containsData( client.getEmail() ))
            throw new IllegalStateException("Email is required");
        if (isEmailTaken( client.getEmail() ))
            throw new IllegalStateException("Email is taken");

        if(containsData( client.getPhoneNumber() ))
            if(!isPhoneNumber(client.getPhoneNumber()))
                throw new IllegalStateException("Not proper phone number");

        cityService.checkCities( client.getCurrentCities() );
        cityService.checkCities( client.getRegistrationCities() );
        countryService.checkCountries(client.getCitizenship());

        clientRepository.save(client);
    }

    public void deleteClient(Long clientId){
        if (!clientRepository.existsById(clientId))
            throw new IllegalStateException("Client with id " + clientId + " does not exist");

        clientRepository.deleteById(clientId);
    }

    public void deleteClient(String email){
        Optional<Client> personOptional = clientRepository.findClientByEmail(email);
        if (personOptional.isEmpty())
            throw new IllegalStateException("Client with email " + email + " does not exist");

        clientRepository.deleteById(personOptional.get().getId());
    }


    @Transactional
    public void updateClient(Long clientId, Client client) throws IllegalStateException{
        Client oldClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalStateException(
                        "Client with id " + clientId + " does not exist"));

        if(client.equals(oldClient)
                && client.getCitizenship().equals(oldClient.getCitizenship()))
            throw new IllegalStateException("Update client (id = "+ clientId +") with no changes");

        if(containsData( client.getEmail() ))
            if(!client.getEmail().equals(oldClient.getEmail())
                    && isEmailTaken( client.getEmail() ))
                throw new IllegalStateException("Email taken");
            else
                oldClient.setEmail(client.getEmail());

        if (containsData( client.getFirstname() ))
            oldClient.setFirstname(client.getFirstname());

        if(containsData( client.getLastname() ))
            oldClient.setLastname(client.getLastname());

        if(notNull( client.getAddress() ))
            oldClient.setAddress(client.getAddress());

        if(notNull( client.getBirthDate() ))
            oldClient.setBirthDate(client.getBirthDate());

        if(containsData( client.getPhoneNumber() )
                &&isPhoneNumber(client.getPhoneNumber()))
            oldClient.setPhoneNumber(client.getPhoneNumber());

        //citizenship
        Set<Country> countries = client.getCitizenship();
        countryService.checkCountries(countries);
        for (Country c :countries) {
            if(!oldClient.getCitizenship().contains(c))
                oldClient.addCitizenship(c);
        }
        oldClient.getCitizenship()
                .removeIf(c -> (!client.getCitizenship().contains(c)));

    }

    public Client getClientById(Long clientId) throws IllegalStateException{
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalStateException(
                        "Client with id " + clientId + " does not exist"));
    }

    public Client getClientByEmail(String email) throws IllegalStateException{
        return clientRepository.findClientByEmail(email)
                .orElseThrow(() -> new IllegalStateException(
                        "Client with email " + email + " does not exist"));
    }

    public boolean isEmailTaken(String email){
        return clientRepository.findClientByEmail(email).isPresent();
    }
}
