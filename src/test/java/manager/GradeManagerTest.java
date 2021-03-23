package manager;

import entity.Grade;
import entity.Student;
import entity.Teacher;
import org.junit.jupiter.api.*;

import java.lang.management.MemoryType;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GradeManagerTest {

    private static Grade grade1 = new Grade();
    private static Grade grade2 = new Grade();
    private static Grade grade3 = new Grade();
    private static GradeManager gradeManager = new GradeManager();

    private static Student student = new Student();
    private static Teacher teacher = new Teacher();
    private static StudentManager studentManager = new StudentManager();
    private static TeacherManager teacherManager = new TeacherManager();

    @BeforeAll
    static void beforeAll() {
        student.setName("Jan");
        student.setSurname("Kowalski");
        student.setJournalNumber(3);
        studentManager.addStudent(student);

        teacher.setName("Katarzyna");
        teacher.setSurname("Dobrowicz");
        teacher.setSubject("Jezyk polski");
        teacherManager.addTeacher(teacher);

        grade1.setGrade(5);
        grade1.setStudent(student);
        grade1.setTeacher(teacher);

        grade2.setGrade(4);
        grade2.setStudent(student);
        grade2.setTeacher(teacher);

        grade3.setGrade(3);
        grade3.setStudent(student);
        grade3.setTeacher(teacher);
    }

    @AfterAll
    static void afterAll() {
        studentManager.deleteStudent(student);
        teacherManager.deleteTeacher(teacher);
        gradeManager.deleteGrade(grade1);
        gradeManager.deleteGrade(grade2);
        gradeManager.deleteGrade(grade3);
    }

    @Test
    @Order(1)
    void addGrade() {
        assertTrue(gradeManager.addGrade(grade1));
        assertTrue(gradeManager.addGrade(grade2));
        assertTrue(gradeManager.addGrade(grade3));

        Grade emptyGrade = new Grade();
        assertFalse(gradeManager.addGrade(emptyGrade));
    }

    @Test
    @Order(2)
    void deleteGrade() {
        assertTrue(gradeManager.deleteGrade(grade1));
    }

    @Test
    @Order(3)
    void updateGrade() {
        Grade newGrade = gradeManager.updateGrade(grade2, 5);
        assertEquals(grade2, newGrade);
    }

    @Test
    @Order(4)
    void averageGrades() {
        Student found = studentManager.findById(student.getIdStudent());
        assertEquals(4.0, gradeManager.averageGrades(found, teacher));
    }
}