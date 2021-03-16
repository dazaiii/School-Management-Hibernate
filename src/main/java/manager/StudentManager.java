package manager;
import entity.HibernateFactory;
import entity.Student;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public class StudentManager {
    public boolean addStudent(Student student){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        boolean isAdded;
        System.out.println("Transaction started");
        try {
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Student added");
            isAdded = true;
        } catch(Exception e){
            transaction.rollback();
            e.printStackTrace();
            System.out.println("Student cannot be added");
            isAdded = false;
        } finally {
            session.close();
            hibernateFactory.getSessionFactory().close();
        }
        return isAdded;
    }

    public boolean deleteStudent(Student student){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Transaction started");
        boolean isDeleted;
        try {
            session.delete(student);
            session.getTransaction().commit();
            System.out.println("Student deleted");
            isDeleted = true;
        } catch(Exception e){
            transaction.rollback();
            e.printStackTrace();
            System.out.println("Student cannot be deleted");
            isDeleted = false;
        } finally {
            session.close();
            hibernateFactory.getSessionFactory().close();
        }
        return isDeleted;
    }

    public Student getStudent(Long id){
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

    public List<Student> findStudent(String text){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("surname", text));
        List<Student> results = criteria.list();
        for(Student i : results)
            System.out.println(i);
        return results;
    }

    public List<Student> searchPartial(String text){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.like("surname", "%"+text+"%", MatchMode.ANYWHERE));
        List<Student> results = criteria.list();
        for(Student i : results)
            System.out.println(i);
        return results;
    }

    public List<Student> findByNameAndSurname(String name, String surname){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("surname", surname));
        List<Student> results = criteria.list();
        for(Student i : results)
            System.out.println(i);
        return results;
    }

    /*public List<Student> sort(String type){

        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.addOrder(Order.asc(type));
        List<Student> results = criteria.list();
        for(Student i : results)
            System.out.println(i);
        return results;
    }*/
}
