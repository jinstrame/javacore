package tutorial.xml.dom;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DomParser {

    String STUDENT = "student";
    String ROLL_NO = "rollno";
    String FIRST_NAME = "firstname";
    String LAST_NAME = "lastname";
    String NICKNAME = "nickname";
    String MARKS = "marks";
    String CLASS = "class";

    Document parseXml(File file) throws IOException, SAXException, ParserConfigurationException;

    List<Student> getStudentsFromDocument(Document document);

    Document createXmlDocumentWithOneStudent(Student student) throws ParserConfigurationException;
}
