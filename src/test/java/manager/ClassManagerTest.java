package manager;

import entity.Student;
import entity.Class;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClassManagerTest {

    private static Class class1 = new Class();
    private static Class class2 = new Class();
    private static Class class3 = new Class();
    private static ClassManager classManager = new ClassManager();

    private static Student student = new Student();
    private static StudentManager studentManager = new StudentManager();

    @BeforeAll
    static void beforeAll() {
        class1.setClassName("1a");
        class1.setMaxAmount(30);

        class2.setClassName("1b");
        class2.setMaxAmount(35);

        class3.setClassName("2a");
        class3.setMaxAmount(30);
    }

    @AfterAll
    static void afterAll() {
        classManager.deleteClass(class1);
        classManager.deleteClass(class2);
        classManager.deleteClass(class3);
        studentManager.deleteStudent(student);
    }

    @Test
    @Order(1)
    void addClass() {
        assertTrue(classManager.addClass(class1));
        assertTrue(classManager.addClass(class2));
        assertTrue(classManager.addClass(class3));

        Class emptyClass = new Class();
        assertFalse(classManager.addClass(emptyClass));
    }

    @Test
    @Order(2)
    void updateClass() {
        class1.setClassName("2b");
        class1.setMaxAmount(35);
        Class updatedClass = classManager.updateClass(class1);
        assertEquals(updatedClass.getClassName(), class1.getClassName());
        assertEquals(updatedClass.getMaxAmount(), class1.getMaxAmount());
        assertEquals(updatedClass, class1);
    }

    @Test
    @Order(3)
    void deleteClass() {
        assertTrue(classManager.deleteClass(class1));
    }

    @Test
    @Order(4)
    void addStudent() {
        student.setName("Jan");
        student.setSurname("Kowalski");
        student.setJournalNumber(3);
        studentManager.addStudent(student);

        Class class4 = classManager.addStudent(class2, student);
        assertEquals(class4.getStudents(), class2.getStudents());

        class4 = classManager.addStudent(class2, student);
        assertEquals(class4.getStudents(), class2.getStudents());
    }
}