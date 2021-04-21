package com.bsuir.stankevich.lab8.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Client")
@Table(name = "client",
        uniqueConstraints = {
                @UniqueConstraint(name = "client_email_unique", columnNames = "email")
        }
)
public class Client
{
    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence")
    @Column(name = "id",
            updatable = false
    )
    private Long id;

    @Column(name = "firstname",
            nullable = false,
            columnDefinition = "TEXT")
    private String firstname;

    @Column(name = "lastname",
            nullable = false,
            columnDefinition = "TEXT")
    private String lastname;

    @Column(name = "patronym",
            columnDefinition = "TEXT")
    private String patronym;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate = LocalDate.now();

    @Column(name = "passport_series")
    private String passportSeries;

    @Column(name = "passport_number")
    private String passportNumber;

    @ManyToMany(
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "current_cities",
            joinColumns = {
                    @JoinColumn(name = "client_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "city_id", referencedColumnName = "id")
            }
    )
    private Set<City> currentCities = new HashSet<>();

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email",
            nullable = false)
    private String email;

    @Column(name = "employed")
    private Boolean employed = false;

    @Column(name = "position")
    private String position = null;

    @ManyToMany(
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "registration_cities",
            joinColumns = {
                    @JoinColumn(name = "client_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "city_id", referencedColumnName = "id")
            }
    )
    private Set<City> registrationCities = new HashSet<>();


    @ManyToMany(
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "citizenship",
            joinColumns = {
                    @JoinColumn(name = "client_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "country_id", referencedColumnName = "id")
            }
    )
    private Set<Country> citizenship = new HashSet<>();

    public Client() {
    }

    public Client(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public Client(String firstname,
                  String lastname,
                  String patronym,
                  LocalDate birthDate,
                  String passportSeries,
                  String passportNumber,
                  String address,
                  String phoneNumber,
                  String email,
                  Boolean employed,
                  String position) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronym = patronym;
        this.birthDate = birthDate;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employed = employed;
        this.position = position;
    }

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

    public Set<City> getRegistrationCities() {
        return registrationCities;
    }

    public void setRegistrationCities(Set<City> registrationCities) {
        this.registrationCities = registrationCities;
    }

    public Set<Country> getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Set<Country> citizenship) {
        this.citizenship = citizenship;
    }

    public Set<City> getCurrentCities() {
        return currentCities;
    }

    public void setCurrentCities(Set<City> currentCities) {
        this.currentCities = currentCities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronym() {
        return patronym;
    }

    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmployed() {
        return employed;
    }

    public void setEmployed(Boolean employed) {
        this.employed = employed;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", employed=" + employed +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && firstname.equals(client.firstname) && lastname.equals(client.lastname) && Objects.equals(patronym, client.patronym) && Objects.equals(birthDate, client.birthDate) && Objects.equals(passportSeries, client.passportSeries) && Objects.equals(passportNumber, client.passportNumber) && Objects.equals(currentCities, client.currentCities) && Objects.equals(address, client.address) && Objects.equals(phoneNumber, client.phoneNumber) && email.equals(client.email) && Objects.equals(employed, client.employed) && Objects.equals(position, client.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, patronym, birthDate, passportSeries, passportNumber, currentCities, address, phoneNumber, email, employed, position);
    }
}
