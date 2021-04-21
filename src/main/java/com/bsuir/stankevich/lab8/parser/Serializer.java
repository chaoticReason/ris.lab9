package com.bsuir.stankevich.lab8.parser;

import com.bsuir.stankevich.lab8.entity.City;
import com.bsuir.stankevich.lab8.entity.Client;
import com.bsuir.stankevich.lab8.myutils.ManagerResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class Serializer {

    private final ManagerResource urlManager;
    private final ObjectMapper objectMapper;
    private final XmlMapper xmlMapper;

    public Serializer(){
        urlManager = new ManagerResource();
        objectMapper = new ObjectMapper();
        xmlMapper = new XmlMapper();
        objectMapper.registerModule(new JavaTimeModule());
        xmlMapper.registerModule(new JavaTimeModule());
    }

    public Client jsonToClient(String json) throws IOException {
        return objectMapper.readValue(json, Client.class);
    }
    public Client xmlToClient(String xml) throws IOException {
        return xmlMapper.readValue(xml, Client.class);
    }
    public String clientToJson(Client client) throws IOException {
        return objectMapper.writeValueAsString(client);
    }
    public String clientToXml(Client client) throws IOException {
        return xmlMapper.writeValueAsString(client);
    }

    public Client readXmlClient(String path) throws IOException {
        File file = urlManager.createFile(path);
        return xmlMapper.readValue(file, Client.class);
    }

    public Client readJsonClient(String path) throws IOException {
        File file = urlManager.createFile(path);
        return objectMapper.readValue(file, Client.class);
    }

    public void writeJsonClient(Client client, String path) throws IOException {
        String jsonString = objectMapper.writeValueAsString(client);

        FileOutputStream fos = new FileOutputStream(path);
        DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
        outStream.writeUTF(jsonString);
        outStream.close();
    }

    public City readJsonCity(String path) throws IOException {
        File file = urlManager.createFile(path);
        return objectMapper.readValue(file, City.class);
    }

    public City readXmlCity(String path) throws IOException {
        File file = urlManager.createFile(path);
        return xmlMapper.readValue(file, City.class);
    }

}