package entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "idStudent")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "journalNumber")
    private int journalNumber;

    @ManyToOne
    @JoinColumn(name = "idClass", referencedColumnName = "idClass")
    private Class idClass;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Grade> grades;

    public Long getIdStudent() {
        return idStudent;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getJournalNumber() {
        return journalNumber;
    }

    public Class getIdClass() {
        return idClass;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setJournalNumber(int journalNumber) {
        this.journalNumber = journalNumber;
    }

    public void setIdClass(Class idClass) {
        this.idClass = idClass;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        String student =
                "Student{" +
                "idStudent=" + idStudent +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", journalNumber=" + journalNumber;
                if (idClass != null) {
                    student += ", Class=" + idClass.getClassName();
                }
                student += '}';
                if(grades != null){
                    for(Grade i: grades){
                        System.out.println(i.getIdGrade() + ", " + i.getGrade()/* + ", " + i.getTeacher()*/);
                    }
                }

                return student;
    }
}
