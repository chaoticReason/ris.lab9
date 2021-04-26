package com.bsuir.stankevich.lab8.repo;

import com.bsuir.stankevich.lab8.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Long> {
    //@Query("SELECT p FROM Client p WHERE p.email = ?1")
    Optional<Client> findClientByEmail(String email);
}
