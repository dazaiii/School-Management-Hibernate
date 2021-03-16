package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "class")
public class Class {
    @Id
    @Column(name = "idClass")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClass;

    @Column(name = "className")
    private String className;

    @Column(name = "maxAmount")
    private int maxAmount;

    @OneToMany
    @JoinColumn(name = "idClass", referencedColumnName = "idClass")
    private List<Student> students;

    public Long getIdClass() {
        return idClass;
    }

    public String getClassName() {
        return className;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setIdClass(Long idClass) {
        this.idClass = idClass;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Class{" +
                "idClass=" + idClass +
                ", className='" + className + '\'' +
                ", maxAmount=" + maxAmount +
                ", students=" + students +
                '}';
    }
}
