package manager;

import entity.Student;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentManagerTest {
    private static Student student = new Student();
    private static Student student1 = new Student();
    private static Student student2 = new Student();
    private static Student student3 = new Student();
    private static Student student4 = new Student();
    private static StudentManager studentManager = new StudentManager();

    @BeforeAll
    static void beforeAll() {
        student.setName("Jan");
        student.setSurname("Kowalski");
        student.setJournalNumber(3);

        student1.setName("Anna");
        student1.setSurname("Maj");
        student1.setJournalNumber(5);

        student2.setName("Maja");
        student2.setSurname("Glowacka");
        student2.setJournalNumber(2);

        student3.setName("Adam");
        student3.setSurname("Kowal");
        student3.setJournalNumber(8);
    }

    @AfterAll
    static void afterAll() {
        studentManager.deleteStudent(student);
        studentManager.deleteStudent(student1);
        studentManager.deleteStudent(student3);
        studentManager.deleteStudent(student4);
    }

    @Test
    @Order(1)
    void addStudent() {
        assertTrue(studentManager.addStudent(student));
        assertTrue(studentManager.addStudent(student1));
        assertTrue(studentManager.addStudent(student2));
        assertTrue(studentManager.addStudent(student3));

        Student emptyStudent = new Student();
        assertFalse(studentManager.addStudent(emptyStudent));
    }

    @Test
    @Order(2)
    void updateStudent(){
        student2.setName("Zofia");
        student2.setSurname("Kotecka");
        student2.setJournalNumber(15);
        Student updatedStudent = studentManager.updateStudent(student2);

        assertEquals("Zofia", updatedStudent.getName());
        assertEquals("Kotecka", updatedStudent.getSurname());
        assertEquals(15, updatedStudent.getJournalNumber());
        assertEquals(student2, updatedStudent);
    }

    @Test
    @Order(3)
    void deleteStudent() {
        assertTrue(studentManager.deleteStudent(student2));
    }

    @Test
    @Order(4)
    void findById() {
        student4.setName("Robinson");
        student4.setSurname("Crusoe");
        student4.setJournalNumber(10);
        studentManager.addStudent(student4);
        Student test = studentManager.findById(student4.getIdStudent());
        assertEquals("Crusoe", test.getSurname());
    }

    @Test
    @Order(5)
    void findStudent() {
        List<Student> list = studentManager.findStudent("Kowal", "");
        assertEquals(student.getSurname(), list.get(0).getSurname());
        assertEquals(student3.getSurname(), list.get(1).getSurname());
    }

    @Test
    @Order(6)
    void sort() {
        List<Student> list = studentManager.sort("surname", "asc");
        assertEquals("Crusoe", list.get(0).getSurname());
        assertEquals("Kowal", list.get(1).getSurname());
        assertEquals("Kowalski", list.get(2).getSurname());
        assertEquals("Maj", list.get(3).getSurname());
    }
}