package manager;

import entity.Student;
import entity.Class;

import java.util.List;

public class ClassManager {
    public boolean addClass(Class schoolClass){
        EntityManager entityManager = new EntityManager();
        return entityManager.add(schoolClass);
    }

    public boolean deleteClass(Class schoolClass){
        EntityManager entityManager = new EntityManager();
        return entityManager.delete(schoolClass);
    }

    public Class updateClass(Class schoolClass){
        EntityManager entityManager = new EntityManager();
        entityManager.update(schoolClass);
        return schoolClass;
    }

    public Class addStudent(Class schoolClass, Student student){
        List<Student> list = schoolClass.getStudents();
        list.add(student);
        schoolClass.setStudents(list);
        EntityManager entityManager = new EntityManager();
        entityManager.update(schoolClass);
        return schoolClass;
    }
}
