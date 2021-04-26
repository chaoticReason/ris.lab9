package com.bsuir.stankevich.lab8.service;

import com.bsuir.stankevich.lab8.entity.City;
import com.bsuir.stankevich.lab8.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCities(){ return cityRepository.findAll(); }

    public void checkCities(Set<City> cities) throws IllegalStateException{
        for (City iCity : cities) {
            Optional<City> optional = cityRepository.findCityByName(iCity.getName());
            if(optional.isPresent()) {
                City dbCity = optional.get();
                if(iCity.getId() == null)
                    iCity.setId(dbCity.getId());
                else if(!dbCity.getId().equals(iCity.getId()))
                    throw new IllegalStateException("Not proper id of city " + iCity.getName());
            }
            else{
                if(iCity.getId()!=null)
                    throw new IllegalStateException("Not proper id of city " + iCity.getName());
                cityRepository.save(iCity);
            }
        }
    }

}
