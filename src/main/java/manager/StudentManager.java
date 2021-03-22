package manager;
import entity.HibernateFactory;
import entity.Student;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class StudentManager {
    public boolean addStudent(Student student){
        EntityManager entityManager = new EntityManager();
        return entityManager.add(student);
    }

    public boolean deleteStudent(Student student){
        EntityManager entityManager = new EntityManager();
        return entityManager.delete(student);
    }

    public Student findById(Long id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        System.out.println("Transaction started");
        Student student = null;
        try {
            student = session.load(Student.class, id);
            //get zwraca null a load zwraca exception
            //jezeli nie jestes pewien czy istnieje obiekt, uzyj get
            //jezeli jestes pewien, uzyj load
            //get zwraca w pelni zainicjowany obiekt, a load zwraca obiekt proxy
            //load jest nieco szybszy niz get
            Hibernate.initialize(student);
            //inicjalizuje proxy, nie trzeba transakcji
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Student cannot be found");
        } finally {
            session.close();
            hibernateFactory.getSessionFactory().close();
        }
        return student;
    }

    public List<Student> findStudent(String surname, String name){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);

        Predicate[] predicates = new Predicate[2];
        predicates[0] = criteriaBuilder.like(root.get("surname"), "%"+surname+"%");
        predicates[1] = criteriaBuilder.like(root.get("name"), "%"+name+"%");

        criteriaQuery.select(root).where(predicates);
        Query<Student> query = session.createQuery(criteriaQuery);
        List<Student> results = query.getResultList();

        for(Student i : results)
            System.out.println(i);

        session.close();
        hibernateFactory.getSessionFactory().close();
        return results;
    }

    public List<Student> sort(String column, String type) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);

        if(type.equalsIgnoreCase("asc")){
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(column)));
        } else if(type.equalsIgnoreCase("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(column)));
        }

        criteriaQuery.orderBy(criteriaBuilder.asc(root.get(column)));
        Query<Student> query = session.createQuery(criteriaQuery);
        List<Student> results = query.getResultList();

        for(Student i : results)
            System.out.println(i);

        session.close();
        hibernateFactory.getSessionFactory().close();
        return results;
    }

    public void addGrade(){

    }
}
