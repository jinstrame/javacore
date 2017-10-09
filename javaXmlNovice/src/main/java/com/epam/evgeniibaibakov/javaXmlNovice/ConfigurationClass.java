package com.epam.evgeniibaibakov.javaXmlNovice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


@Configuration
@ComponentScan("com.epam.evgeniibaibakov.javaXmlNovice")
public class ConfigurationClass {
    @Bean
    public JAXBContext simplePersonContext() throws JAXBException {
        return JAXBContext.newInstance(SimplePerson.class);
    }
}
