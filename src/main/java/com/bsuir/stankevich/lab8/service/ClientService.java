package com.bsuir.stankevich.lab8.service;

import com.bsuir.stankevich.lab8.entity.City;
import com.bsuir.stankevich.lab8.entity.Client;
import com.bsuir.stankevich.lab8.entity.Country;
import com.bsuir.stankevich.lab8.repo.CityRepository;
import com.bsuir.stankevich.lab8.repo.ClientRepository;
import com.bsuir.stankevich.lab8.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.bsuir.stankevich.lab8.myutils.Checker.*;

//CRUD operations
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         CityRepository cityRepository,
                         CountryRepository countryRepository) {
        this.clientRepository = clientRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    public List<Client> getClients()
    {
        return clientRepository.findAll();
    }

    public void addNewClient(Client client) throws IllegalStateException
    {
        //check email
        if (!isEmail(client.getEmail()))
            throw new IllegalStateException("Not proper email");

        //check email existence
        Optional<Client> personOptional = clientRepository.findClientByEmail(client.getEmail());
        if (personOptional.isPresent())
            throw new IllegalStateException("Email taken");

        //check correctness of number
        if(client.getPhoneNumber() != null)
            if(!isPhoneNumber(client.getPhoneNumber()))
                throw new IllegalStateException("Not proper phone number");

        //checks current cities
        Set<City> c_cities = client.getCurrentCities();
        for (City c :c_cities) {
            Optional<City> cityOptional = cityRepository.findCityByName(c.getName());
            if(cityOptional.isPresent()) {
                City cityDetached = cityOptional.get();
                if(c.getId() == null)
                    c.setId(cityDetached.getId());
                else if(!cityDetached.getId().equals(c.getId()))
                    throw new IllegalStateException("Not proper id of city " + c.getName());
            }
            else{
                if(c.getId()!=null)
                    throw new IllegalStateException("Not proper id of city " + c.getName());
                cityRepository.save(c);
            }
        }

        //checks registration cities
        Set<City> r_cities = client.getRegistrationCities();
        for (City c :r_cities) {
            Optional<City> cityOptional = cityRepository.findCityByName(c.getName());
            if(cityOptional.isPresent()) {
                City cityDetached = cityOptional.get();
                if(c.getId() == null)
                    c.setId(cityDetached.getId());
                else if(!cityDetached.getId().equals(c.getId()))
                    throw new IllegalStateException("Not proper id of city " + c.getName());
            }
            else{
                if(c.getId()!=null)
                    throw new IllegalStateException("Not proper id of city " + c.getName());
                cityRepository.save(c);
            }
        }

        //checks countries
        Set<Country> countries = client.getCitizenship();
        for (Country c :countries) {
            Optional<Country> countryOptional = countryRepository.findCountryByName(c.getName());
            if(countryOptional.isPresent()) {
                Country countryDetached = countryOptional.get();
                if(c.getId() == null)
                    c.setId(countryDetached.getId());
                else if(!countryDetached.getId().equals(c.getId()))
                    throw new IllegalStateException("Not proper id of country " + c.getName());
            }
            else{
                if(c.getId()!=null)
                    throw new IllegalStateException("Not proper id of country " + c.getName());
                countryRepository.save(c);
            }
        }

        clientRepository.save(client);
    }

    public void deleteClient(Long clientId){
        boolean exists = clientRepository.existsById(clientId);
        if (!exists)
        {
            throw new IllegalStateException(
                    "Client with id " + clientId + " does not exist");
        }
        clientRepository.deleteById(clientId);
    }

    public void deleteClient(String email){
        Optional<Client> personOptional = clientRepository.findClientByEmail(email);
        boolean exists = personOptional.isPresent();
        if (!exists)
        {
            throw new IllegalStateException(
                    "Client with email " + email + " does not exist");
        }
        clientRepository.deleteById(personOptional.get().getId());
    }

    @Transactional
    public void updateEmail(Long clientId, String email){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalStateException(
                        "Client with id " + clientId + " does not exist"));

        if (email != null
                && email.length() > 0
                && !Objects.equals(client.getEmail(), email)) {
            //check 1
            Optional<Client> studentOptional = clientRepository.findClientByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            //set email
            client.setEmail(email);
        }
    }

    public Client getClientById(Long clientId){
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalStateException(
                        "Client with id " + clientId + " does not exist"));
    }
}
