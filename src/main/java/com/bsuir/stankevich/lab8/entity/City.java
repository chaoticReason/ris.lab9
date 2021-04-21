package com.bsuir.stankevich.lab8.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "city")
public class City
{
    @Id
    @SequenceGenerator(
            name = "city_sequence",
            sequenceName = "city_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "city_sequence")
    private Long id;

    @NaturalId
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "currentCities")
    private Set<Client> clientsCurrent = new HashSet<>();

    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "registrationCities")
    private Set<Client> clientsRegistration = new HashSet<>();

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<Client> getClientsRegistration() {
        return clientsRegistration;
    }

    public void setClientsRegistration(Set<Client> clientsRegistration) {
        this.clientsRegistration = clientsRegistration;
    }

    public Set<Client> getClientsCurrent() {
        return clientsCurrent;
    }

    public void setClientsCurrent(Set<Client> clients) {
        this.clientsCurrent = clients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
