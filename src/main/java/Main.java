import entity.HibernateFactory;
import entity.Student;
import entity.Class;
import manager.StudentManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Class class1 = new Class();
        class1.setIdClass(2L);
        class1.setClassName("1b");
        class1.setMaxAmount(30);

        Student student = new Student();
        student.setName("Krzysztof");
        student.setSurname("Krawczyk");
        //student.setIdClass(class1);
        student.setJournalNumber(2);
        StudentManager studentManager = new StudentManager();
        studentManager.addStudent(student);
        studentManager.deleteStudent(student);
        Student foundStudent = studentManager.findById(1L);
        System.out.println("Found student is " + foundStudent.getName() + " " + foundStudent.getSurname());
        studentManager.findStudent("Kra","");
        studentManager.findStudent("","Krzy");
        List<Student> list = studentManager.sort("surname", "asc");
        //for(Student i : list)
        //    System.out.println(i.toString());

    }
}
