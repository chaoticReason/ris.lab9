package com.bsuir.stankevich.lab8.parser;

import com.bsuir.stankevich.lab8.myutils.ManagerResource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class Validation {
    static private ManagerResource r;

    public Validation(){
        r = new ManagerResource();
    }
    public boolean validateXMLSchema(String xsdPath, String xmlPath){
        File xsd = r.createFile(xsdPath);
        File xml = r.createFile(xmlPath);
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema( xsd );
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource( xml ));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
}
