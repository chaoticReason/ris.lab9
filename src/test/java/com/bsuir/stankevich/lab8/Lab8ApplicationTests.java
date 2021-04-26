package com.bsuir.stankevich.lab8;

import com.bsuir.stankevich.lab8.entity.Client;
import com.bsuir.stankevich.lab8.entity.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Lab8ApplicationTests {

    @Autowired
    MainController controller;

    @Test
    void addClients() throws IOException {
        controller.addClient("person4.xml");

    }

    @Test
    void deleteClient() throws IOException {
        controller.deleteClient("person4.xml");
    }

    @Test
    void updateClient() {
        Long id = 6L;
        Client client = controller.getClient(id);
        client.setEmail("lazarar@gmail.com");
        client.setPhoneNumber("+375292856432");
        client.setBirthDate(LocalDate.of(1988, 5,25));
        client.setAddress("vul. Blabla, 71-18");
        client.addCitizenship(new Country("Belarus"));
        client.addCitizenship(new Country("Italy"));
        client.addCitizenship(new Country("England"));
        Client newClient = controller.updateClient(id, client);
        assertEquals(newClient.getEmail(), client.getEmail());
    }


    @Test
    void updateClient3() {
        Long id = 5L;
        Client client = controller.getClient(id);
        client.addCitizenship(new Country("Ukraine"));
        assertEquals(2, client.getCitizenship().size());
    }

    @Test
    void updateClient4() {
        Long id = 5L;
        Client client = controller.getClient(id);
        client.removeCitizenship(new Country("Ukraine"));
        assertEquals(1, client.getCitizenship().size());
    }


    @Test
    void readClientFromDatabase() throws IOException {
        Long id = 3L;
        controller.readClientFromDatabase(id);
    }


}
