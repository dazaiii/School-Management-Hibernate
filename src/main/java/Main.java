import entity.*;
import entity.Class;
import manager.ClassManager;
import manager.GradeManager;
import manager.StudentManager;
import manager.TeacherManager;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Examples
        Student student = new Student();
        StudentManager studentManager = new StudentManager();
        student.setName("Jan");
        student.setSurname("Kowalski");
        student.setJournalNumber(1);
        studentManager.addStudent(student);
        studentManager.findStudent("Kowalski", "Jan");
        student.setName("Krzysztof");
        student.setSurname("Kowalski");
        student.setJournalNumber(15);
        studentManager.updateStudent(student);

        Teacher teacher = new Teacher();
        TeacherManager teacherManager = new TeacherManager();
        teacher.setName("Adam");
        teacher.setSurname("Nowak");
        teacher.setSubject("Matematyka");
        teacherManager.addTeacher(teacher);

        Class classroom = new Class();
        ClassManager classManager = new ClassManager();
        classroom.setMaxAmount(30);
        classroom.setClassName("1a");
        List<Student> list = new ArrayList();
        list.add(student);
        classroom.setStudents(list);

        classManager.addClass(classroom);

        Grade grade = new Grade();
        GradeManager gradeManager = new GradeManager();
        grade.setGrade(5);
        grade.setTeacher(teacher);
        grade.setStudent(student);
        gradeManager.addGrade(grade);

        gradeManager.deleteGrade(grade);
        studentManager.deleteStudent(student);
        teacherManager.deleteTeacher(teacher);
        classManager.deleteClass(classroom);

    }
}
