package entity;

import javax.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @Column(name = "idGrade")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrade;

    @Column(name = "grade")
    private int grade;

    @ManyToOne
    @JoinColumn(name = "idStudent", referencedColumnName = "idStudent")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idTeacher", referencedColumnName = "idTeacher")
    private Teacher teacher;

    public Long getIdGrade() {
        return idGrade;
    }

    public int getGrade() {
        return grade;
    }

    public Student getStudent() {
        return student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setIdGrade(Long idGrade) {
        this.idGrade = idGrade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "idGrade=" + idGrade +
                ", grade=" + grade +
                ", student=" + student +
                ", teacher=" + teacher +
                '}';
    }
}
