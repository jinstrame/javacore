package tutorial.xml.sax;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tutorial.xml.ConfigurationClass;
import tutorial.xml.dom.Student;

import javax.annotation.Resource;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ConfigurationClass.class})
public class SaxParserTest {

    @Resource
    private SaxParser saxParser;

    private File xmlFile1;

    @Before
    public void setUp() {
        xmlFile1 = new File("src/test/resources/file1.xml");
    }

    @Test
    public void parseSaxFile() throws Exception {
        List<Student> students = saxParser.parseSax(xmlFile1);
        List<Student> expectedStudents = getExpectedStudents();
        assertThat(students, containsInAnyOrder(expectedStudents.toArray()));
        assertThat(students.size(), is(expectedStudents.size()));
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