package tutorial.xml.sax;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import tutorial.xml.dom.Student;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Component
public interface SaxParser {
    List<Student> parseSax(File input) throws IOException, SAXException, ParserConfigurationException;
}
