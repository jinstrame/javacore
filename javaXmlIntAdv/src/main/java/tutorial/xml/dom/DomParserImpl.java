package tutorial.xml.dom;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Component
public class DomParserImpl implements DomParser {
    @Override
    public Document parseXml(File file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        return document;
    }

    @Override
    public List<Student> getStudentsFromDocument(Document document) {
        document.getDocumentElement().normalize();

        NodeList childrenList = document.getDocumentElement().getElementsByTagName(STUDENT);

        LinkedList<Student> students = new LinkedList<>();

        for (int i = 0; i < childrenList.getLength(); i++) {
            Node item = childrenList.item(i);

            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;

                Student student = new Student();

                student.setRollNo(parseInt(element.getAttribute(ROLL_NO)));
                student.setFirstName(element.getElementsByTagName(FIRST_NAME).item(0).getTextContent());
                student.setLastName(element.getElementsByTagName(LAST_NAME).item(0).getTextContent());
                student.setNickname(element.getElementsByTagName(NICKNAME).item(0).getTextContent());
                student.setMarks(parseInt(element.getElementsByTagName(MARKS).item(0).getTextContent()));

                students.add(student);
            }
        }

        return students;
    }

    @Override
    public Document createXmlDocumentWithOneStudent(Student student) throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootElement = document.createElement(CLASS);
        Element studentElement = document.createElement(STUDENT);

        studentElement.setAttribute(ROLL_NO, student.getRollNo().toString());
        appendChildToElement(document, FIRST_NAME, student.getFirstName(), studentElement);
        appendChildToElement(document, LAST_NAME, student.getLastName(), studentElement);
        appendChildToElement(document, NICKNAME, student.getNickname(), studentElement);
        appendChildToElement(document, MARKS, student.getMarks().toString(), studentElement);

        rootElement.appendChild(studentElement);
        document.appendChild(rootElement);

        return document;
    }

    private void appendChildToElement(Document document, String childName, String childValue,  Element appendTo) {
        Element element = document.createElement(childName);
        element.appendChild(document.createTextNode(childValue));
        appendTo.appendChild(element);
    }
}
