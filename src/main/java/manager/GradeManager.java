package manager;

import entity.Grade;
import entity.Student;
import entity.Teacher;
import java.util.List;

public class GradeManager {
    public boolean addGrade(Grade grade){
        boolean isAdded = false;
        if(grade.getStudent() != null && grade.getTeacher() != null){
            EntityManager entityManager = new EntityManager();
            isAdded = entityManager.add(grade);
        }
        return isAdded;
    }

    public boolean deleteGrade(Grade grade){
        boolean isDeleted = true;
        if(grade.getStudent() != null && grade.getTeacher() != null){
            EntityManager entityManager = new EntityManager();
            isDeleted = entityManager.delete(grade);
        }
        return isDeleted;
    }

    public Grade updateGrade(Grade grade, int newGrade){
        grade.setGrade(newGrade);
        EntityManager entityManager = new EntityManager();
        entityManager.update(grade);
        return grade;
    }

    public double averageGrades(Student student, Teacher teacher){
        List<Grade> list = student.getGrades();
        if(list == null){
            return 0;
        }
        double avg = 0;
        int counter = 0;
        for(Grade i: list){
            if(i.getTeacher().getSubject().equals(teacher.getSubject())){
                avg += i.getGrade();
                counter++;
            }
        }
        avg = avg / counter;
        return avg;
    }
}
