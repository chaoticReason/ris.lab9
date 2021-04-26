package com.bsuir.stankevich.lab8.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity(name = "Client")
@Table(name = "client", uniqueConstraints = {
                @UniqueConstraint(name = "client_email_unique", columnNames = "email")})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client
{
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            updatable = false)
    private Long id;

    @Column(name = "firstname",
            nullable = false,
            columnDefinition = "TEXT")
    private String firstname;

    @Column(name = "lastname",
            nullable = false,
            columnDefinition = "TEXT")
    private String lastname;

    @Builder.Default
    @Column(name = "patronym",
            columnDefinition = "TEXT")
    private String patronym="";

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate = LocalDate.now();

    @Builder.Default
    @Column(name = "passport_series")
    private String passportSeries="";

    @Builder.Default
    @Column(name = "passport_number")
    private String passportNumber="";

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "current_cities",
            joinColumns = { @JoinColumn(name = "client_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "city_id", referencedColumnName = "id") }
    )
    private final Set<City> currentCities = new HashSet<>();

    @Builder.Default
    @Column(name = "address")
    private String address="";

    @Builder.Default
    @Column(name = "phone_number")
    private String phoneNumber="";

    @Column(name = "email",
            nullable = false)
    private String email;

    @Builder.Default
    @Column(name = "employed")
    private Boolean employed = false;

    @Builder.Default
    @Column(name = "position")
    private String position = "";

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "registration_cities",
            joinColumns = { @JoinColumn(name = "client_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "city_id", referencedColumnName = "id") }
    )
    private final Set<City> registrationCities = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "citizenship",
            joinColumns = { @JoinColumn(name = "client_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "country_id", referencedColumnName = "id") }
    )
    private final Set<Country> citizenship = new HashSet<>();


    public void addCurrentCity(City city) {
        currentCities.add(city);
        city.getClientsCurrent().add(this);
    }

    public void removeCurrentCity(City city) {
        currentCities.remove(city);
        city.getClientsCurrent().remove(this);
    }

    public void addRegistrationCity(City city) {
        registrationCities.add(city);
        city.getClientsRegistration().add(this);
    }

    public void removeRegistrationCity(City city) {
        registrationCities.remove(city);
        city.getClientsRegistration().remove(this);
    }
    public void addCitizenship(Country country) {
        citizenship.add(country);
        country.getClients().add(this);
    }

    public void removeCitizenship(Country country) {
        citizenship.remove(country);
        country.getClients().remove(this);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", patronym='" + patronym + '\'' +
                ", birthDate=" + birthDate +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", currentCities=" + currentCities +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", employed=" + employed +
                ", position='" + position + '\'' +
                ", registrationCities=" + registrationCities +
                ", citizenship=" + citizenship +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return firstname.equals(client.firstname) && lastname.equals(client.lastname) && Objects.equals(patronym, client.patronym) && Objects.equals(birthDate, client.birthDate) && Objects.equals(passportSeries, client.passportSeries) && Objects.equals(passportNumber, client.passportNumber) && Objects.equals(address, client.address) && Objects.equals(phoneNumber, client.phoneNumber) && email.equals(client.email) && Objects.equals(employed, client.employed) && Objects.equals(position, client.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
