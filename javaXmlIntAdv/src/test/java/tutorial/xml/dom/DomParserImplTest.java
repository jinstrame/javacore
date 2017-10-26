package tutorial.xml.dom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Document;
import tutorial.xml.ConfigurationClass;

import javax.annotation.Resource;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ConfigurationClass.class})
public class DomParserImplTest {

    @Resource
    private DomParser domParser;

    private File xmlFile1;

    @Before
    public void setUp() {
        xmlFile1 = new File("src/test/resources/file1.xml");
    }

    /**
     * implement parseXml() method to get xml document as DOM
     */
    @Test
    public void parseXml() throws Exception {
        Document document = domParser.parseXml(xmlFile1);
        document.getDocumentElement().normalize();
        assertThat(document.getDocumentElement().getNodeName(), is("class"));
    }

    /**
     * implement getStudentsFromDocument() method to get List of Students from xml file;
     */

    @Test
    public void getStudentList() throws Exception {
        Document document = domParser.parseXml(xmlFile1);
        List<Student> students = domParser.getStudentsFromDocument(document);
        List<Student> expectedStudents = getExpectedStudents();
        assertThat(students, containsInAnyOrder(expectedStudents.toArray()));
        assertThat(students.size(), is(expectedStudents.size()));
    }


    /**
     * implement createXmlDocumentWithOneStudent() method
     */

    @Test
    public void createDomDocumentTest() throws Exception {
        Student originalStudent = new Student("dinkar", "kad", "dinkar", 85, 393);

        Document document = domParser.createXmlDocumentWithOneStudent(originalStudent);
        List<Student> studentsFromDocument = domParser.getStudentsFromDocument(document);

        assertThat(studentsFromDocument.size(), is(1));
        assertThat(studentsFromDocument, contains(originalStudent));
    }


    private List<Student> getExpectedStudents() {
        LinkedList<Student> students = new LinkedList<>();
        Student student1 = new Student("dinkar", "kad", "dinkar", 85, 393);
        Student student2 = new Student("Vaneet", "Gupta", "vinni", 95, 493);
        Student student3 = new Student("jasvir", "singn", "jazz", 90, 593);

        students.add(student1);
        students.add(student2);
        students.add(student3);

        return students;
    }
}