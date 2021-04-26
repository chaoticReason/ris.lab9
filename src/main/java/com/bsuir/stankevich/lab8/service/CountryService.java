package com.bsuir.stankevich.lab8.service;

import com.bsuir.stankevich.lab8.entity.Country;
import com.bsuir.stankevich.lab8.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountries(){ return countryRepository.findAll(); }

    public void checkCountries(Set<Country> countries) throws IllegalStateException{
        for (Country c :countries) {
            Optional<Country> optional = countryRepository.findCountryByName(c.getName());
            if(optional.isPresent()) {
                Country countryDB = optional.get();
                if(c.getId() == null)
                    c.setId(countryDB.getId());
                else if(!countryDB.getId().equals(c.getId()))
                    throw new IllegalStateException("Not proper id of country " + c.getName());
            }
            else{
                if(c.getId() != null)
                    throw new IllegalStateException("Not proper id of country " + c.getName());
                countryRepository.save(c);
            }
        }
    }
}
