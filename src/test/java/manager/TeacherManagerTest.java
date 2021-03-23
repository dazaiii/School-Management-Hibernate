package manager;

import entity.Teacher;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TeacherManagerTest {
    private static Teacher teacher = new Teacher();
    private static Teacher teacher1 = new Teacher();
    private static Teacher teacher2 = new Teacher();
    private static Teacher teacher3 = new Teacher();
    private static Teacher teacher4 = new Teacher();
    private static TeacherManager teacherManager = new TeacherManager();

    @BeforeAll
    static void beforeAll() {
        teacher.setName("Katarzyna");
        teacher.setSurname("Dobrowicz");
        teacher.setSubject("Jezyk polski");

        teacher1.setName("Grzegorz");
        teacher1.setSurname("Grych");
        teacher1.setSubject("Chemia");

        teacher2.setName("Witold");
        teacher2.setSurname("Alda");
        teacher2.setSubject("Informatyka");

        teacher3.setName("Jakub");
        teacher3.setSurname("Dobrowski");
        teacher3.setSubject("Matematyka");
    }

    @AfterAll
    static void afterAll() {
        teacherManager.deleteTeacher(teacher);
        teacherManager.deleteTeacher(teacher1);
        teacherManager.deleteTeacher(teacher2);
        teacherManager.deleteTeacher(teacher3);
        teacherManager.deleteTeacher(teacher4);
    }

    @Test
    @Order(1)
    void addTeacher() {
        assertTrue(teacherManager.addTeacher(teacher));
        assertTrue(teacherManager.addTeacher(teacher1));
        assertTrue(teacherManager.addTeacher(teacher2));
        assertTrue(teacherManager.addTeacher(teacher3));

        Teacher emptyTeacher = new Teacher();
        assertFalse(teacherManager.addTeacher(emptyTeacher));
    }

    @Test
    @Order(2)
    void updateTeacher(){
        teacher1.setName("Julian");
        teacher1.setSurname("Swoboda");
        teacher1.setSubject("Historia");
        Teacher updatedTeacher = teacherManager.updateTeacher(teacher1);

        assertEquals("Julian", updatedTeacher.getName());
        assertEquals("Swoboda", updatedTeacher.getSurname());
        assertEquals("Historia", updatedTeacher.getSubject());
        assertEquals(teacher1, updatedTeacher);
    }

    @Test
    @Order(3)
    void deleteTeacher() {
        assertTrue(teacherManager.deleteTeacher(teacher1));
    }

    @Test
    @Order(4)
    void findById() {
        teacher4.setName("Mikolaj");
        teacher4.setSurname("Mikolajowski");
        teacher4.setSubject("Biologia");
        teacherManager.addTeacher(teacher4);
        Teacher test = teacherManager.findById(teacher4.getIdTeacher());
        assertEquals("Mikolajowski", test.getSurname());
    }

    @Test
    @Order(5)
    void findTeacher() {
        List<Teacher> list = teacherManager.findTeacher("Dobrow", "");
        assertEquals(teacher.getSurname(), list.get(0).getSurname());
        assertEquals(teacher3.getSurname(), list.get(1).getSurname());
    }

    @Test
    @Order(6)
    void sort() {
        List<Teacher> list = teacherManager.sort("surname", "asc");
        assertEquals("Alda", list.get(0).getSurname());
        assertEquals("Dobrowicz", list.get(1).getSurname());
        assertEquals("Dobrowski", list.get(2).getSurname());
        assertEquals("Mikolajowski", list.get(3).getSurname());
    }
}