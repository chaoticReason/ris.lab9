package com.bsuir.stankevich.lab8;

import com.bsuir.stankevich.lab8.entity.City;
import com.bsuir.stankevich.lab8.entity.Client;
import com.bsuir.stankevich.lab8.parser.Serializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {
    private static Serializer md;

    @BeforeAll
    public static void init(){
        md = new Serializer();
    }

    @Test
    public void testWritePersonToXml() throws Exception {
        Client kate = new Client("Kate","Blanchet", "kityblanch@gmail.com");
        kate.setBirthDate(LocalDate.now());
        kate.addCurrentCity(new City("Canberra"));
        String json = md.clientToJson(kate);
        Client who = md.jsonToClient(json);
        assertEquals(kate,who);
    }

    @Test
    public void testWritePersonToJson() throws Exception {
        Client kate = new Client("Kate","Blanchet", "kityblanch@gmail.com");
        kate.setBirthDate(LocalDate.now());
        kate.addCurrentCity(new City("Canberra"));
        String xml = md.clientToXml(kate);
        Client who = md.xmlToClient(xml);
        assertEquals(kate,who);
    }

    @Test
    public void testReadPerson2Xml() throws Exception {
        Client p = md.readXmlClient("person2.xml");
        Client paris = new Client("Paris","Paris", "paris.twice@gmail.com");
        paris.addCurrentCity(new City("Paris"));
        assertEquals(p,paris);
    }

    @Test
    public void testReadPersonXml() throws Exception {
        Client p = md.readXmlClient("person.xml");
        assertEquals(p, new Client("Roger","Adamson", "tiger233@gmail.com<"));
    }

    @Test
    public void testReadCityJson() throws Exception {
        City c = md.readJsonCity("City.json");
        assertEquals(c, new City(5L,"Koenigsberg"));
    }

    @Test
    public void testReadCityXml() throws Exception {
        City c = md.readXmlCity("city.xml");
        assertEquals(c, new City(6L,"Paris"));
    }
}
