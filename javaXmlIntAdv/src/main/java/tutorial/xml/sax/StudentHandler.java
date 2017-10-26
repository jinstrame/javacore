package tutorial.xml.sax;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tutorial.xml.dom.Student;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Component
public class StudentHandler extends DefaultHandler {

    private String STUDENT = "student";
    private String ROLL_NO = "rollno";
    private String FIRST_NAME = "firstname";
    private String LAST_NAME = "lastname";
    private String NICKNAME = "nickname";
    private String MARKS = "marks";
    private String CLASS = "class";

    private Student currentStundert;

    private List<Student> students;

    @Getter
    private List<Student> result = Collections.emptyList();


    private boolean firstName = false;
    private boolean lastName = false;
    private boolean nickname = false;
    private boolean marks = false;


    @Override
    public void startDocument() throws SAXException {
        students = new LinkedList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(STUDENT)) {
            currentStundert = new Student();
            currentStundert.setRollNo(parseInt(attributes.getValue(ROLL_NO)));
        } else if (qName.equalsIgnoreCase(FIRST_NAME)) {
            firstName = true;
        } else if (qName.equalsIgnoreCase(LAST_NAME)) {
            lastName = true;
        } else if (qName.equalsIgnoreCase(NICKNAME)) {
            nickname = true;
        } else if (qName.equalsIgnoreCase(MARKS)) {
            marks = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(STUDENT)) {
            students.add(currentStundert);
        } else if (qName.equalsIgnoreCase(FIRST_NAME)) {
            firstName = false;
        } else if (qName.equalsIgnoreCase(LAST_NAME)) {
            lastName = false;
        } else if (qName.equalsIgnoreCase(NICKNAME)) {
            nickname = false;
        } else if (qName.equalsIgnoreCase(MARKS)) {
            marks = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (firstName) currentStundert.setFirstName(String.valueOf(ch, start, length));
        else if (lastName) currentStundert.setLastName(String.valueOf(ch, start, length));
        else if (nickname) currentStundert.setNickname(String.valueOf(ch, start, length));
        else if (marks) currentStundert.setMarks(parseInt(String.valueOf(ch, start, length)));
    }

    @Override
    public void endDocument() throws SAXException {
        result = Collections.unmodifiableList(students);
    }

    private void resetFlags() {
        firstName = false;
        lastName = false;
        nickname = false;
        marks = false;
    }
}
