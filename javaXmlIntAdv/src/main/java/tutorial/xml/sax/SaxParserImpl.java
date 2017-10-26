package tutorial.xml.sax;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import tutorial.xml.dom.Student;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Component
public class SaxParserImpl implements SaxParser {

    @Resource
    private StudentHandler studentHandler;


    @Override
    public List<Student> parseSax(File input) throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(input, studentHandler);
        return studentHandler.getResult();
    }
}
