package com.epam.evgeniibaibakov.javaXmlNovice;


import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@XmlRootElement
public class SimplePerson {
    @XmlElement
    private String name;
    @XmlAttribute
    private int age;
}
