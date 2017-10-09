package com.epam.evgeniibaibakov.javaXmlNovice;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public interface JaxB {
    void marshalClass(SimplePerson simplePerson, OutputStream outputStream) throws JAXBException;
    SimplePerson unmarshalClass(InputStream inputStream) throws JAXBException;
}
