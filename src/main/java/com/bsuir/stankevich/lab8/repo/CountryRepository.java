package com.bsuir.stankevich.lab8.repo;

import com.bsuir.stankevich.lab8.entity.City;
import com.bsuir.stankevich.lab8.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findCountryByName(String name);
}
