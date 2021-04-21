package com.bsuir.stankevich.lab8.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void itShouldValidate()
    {
        Validation v = new Validation();
        assertTrue( v.validateXMLSchema("person.xsd", "person.xml") );
    }

}