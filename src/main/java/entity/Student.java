package entity;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "idStudent", referencedColumnName = "idStudent")
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

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", journalNumber=" + journalNumber +
                ", idClass=" + idClass +
                '}';
    }
}
