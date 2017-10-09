package com.epam.evgeniibaibakov.javaXmlNovice;

import com.sun.xml.internal.stream.buffer.XMLStreamBufferResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class JaxBImpl implements JaxB {

    @Resource
    JAXBContext simplePersonContext;

    @Override
    public void marshalClass(SimplePerson simplePerson, OutputStream outputStream) throws JAXBException {
        Marshaller marshaller = simplePersonContext.createMarshaller();
        marshaller.marshal(simplePerson, outputStream);
    }

    @Override
    public SimplePerson unmarshalClass(InputStream stream) throws JAXBException {
        Unmarshaller unmarshaller = simplePersonContext.createUnmarshaller();
        Object unmarshaledObject = unmarshaller.unmarshal(stream);
        if (unmarshaledObject instanceof SimplePerson)
            return (SimplePerson) unmarshaledObject;
        else throw new ClassCastException();
    }
}
