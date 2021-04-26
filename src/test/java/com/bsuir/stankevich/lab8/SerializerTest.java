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
    public void testPersonToJson() throws Exception {
        Client kate = Client.builder()
                .firstname("Kate")
                .lastname("Blanchet")
                .email("kityblanch@gmail.com")
                .birthDate(LocalDate.now())
                .build();
        kate.addCurrentCity(new City("Canberra"));
        String json = md.clientToJson(kate);
        Client who = md.jsonToClient(json);
        assertEquals(kate,who);
    }

    @Test
    public void testPersonToXml() throws Exception {
        Client kate = Client.builder()
                .firstname("Kate")
                .lastname("Blanchet")
                .email("kityblanch@gmail.com")
                .birthDate(LocalDate.now())
                .build();
        kate.addCurrentCity(new City("Canberra"));
        String xml = md.clientToXml(kate);
        Client who = md.xmlToClient(xml);
        assertEquals(kate,who);
    }

    @Test
    public void testReadPerson2Xml() throws Exception {
        Client p = md.readXmlClient("person2.xml");
        Client paris = Client.builder()
                .firstname("Paris")
                .lastname("Paris")
                .email("paris.twice@gmail.com")
                .build();
        paris.addCurrentCity(new City("Paris"));
        assertEquals(p,paris);
    }

    @Test
    public void testReadPersonXml() throws Exception {
        Client p = md.readXmlClient("person1.xml");
        assertEquals(p, Client.builder()
                            .firstname("Roger")
                            .lastname("Adamson")
                            .email("tiger233@gmail.com")
                            .build());
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
