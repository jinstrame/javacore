package com.epam.evgeniibaibakov.javaXmlNovice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import java.io.*;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ConfigurationClass.class})
public class JaxBTest {
    private final static String NAME = "Name";
    private final static int AGE = 10;

    private byte[] buffer;

    private SimplePerson testObject;


    @Resource
    private JaxB jaxB;

    @Before
    public void setUp() {
        testObject = new SimplePerson(NAME, AGE);
    }


    /**
     * you need to implement
     */
    @Test
    public void marshallingTest() {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            jaxB.marshalClass(testObject, outputStream);
            buffer = outputStream.toByteArray();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        assertNotNull(buffer);

        SimplePerson resultObject = null;
        try(ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer)) {
            resultObject = jaxB.unmarshalClass(inputStream);
        } catch (JAXBException | IOException e) { }

        assertNotNull(resultObject);
        assertThat(resultObject, equalTo(testObject));
    }
}