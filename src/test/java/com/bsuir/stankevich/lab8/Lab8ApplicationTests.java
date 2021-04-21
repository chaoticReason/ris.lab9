package com.bsuir.stankevich.lab8;

import com.bsuir.stankevich.lab8.controller.MainController;
import com.bsuir.stankevich.lab8.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
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
        String newEmail = "lazarara@gmail.com";
        Client client = controller.updateEmail(id, newEmail);
        assertEquals(newEmail, client.getEmail());
    }

    @Test
    void updateClient2() {
        Long id = 6L;
        String newEmail = "lazarar@gmail.com";
        Client client = controller.updateEmail(id, newEmail);
        assertEquals(newEmail, client.getEmail());
    }


    @Test
    void readClientFromDatabase() throws IOException {
        Long id = 8L;
        controller.readClientFromDatabase(id);
    }


}
