package manager;

import entity.HibernateFactory;
import entity.Teacher;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeacherManager {
    public boolean addTeacher(Teacher teacher){
        EntityManager entityManager = new EntityManager();
        return entityManager.add(teacher);
    }

    public boolean deleteTeacher(Teacher teacher){
        EntityManager entityManager = new EntityManager();
        return entityManager.delete(teacher);
    }

    public Teacher findById(Long id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Teacher teacher = null;
        try {
            teacher = session.load(Teacher.class, id);
            Hibernate.initialize(teacher);
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            session.close();
            hibernateFactory.getSessionFactory().close();
        }
        return teacher;
    }

    public List<Teacher> findTeacher(String surname, String name){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> root = criteriaQuery.from(Teacher.class);

        Predicate[] predicates = new Predicate[2];
        predicates[0] = criteriaBuilder.like(root.get("surname"), "%"+surname+"%");
        predicates[1] = criteriaBuilder.like(root.get("name"), "%"+name+"%");

        criteriaQuery.select(root).where(predicates);
        Query<Teacher> query = session.createQuery(criteriaQuery);
        List<Teacher> results = query.getResultList();

        for(Teacher i : results)
            System.out.println(i);

        session.close();
        hibernateFactory.getSessionFactory().close();
        return results;
    }

    public List<Teacher> sort(String column, String type) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> root = criteriaQuery.from(Teacher.class);

        if(type.equalsIgnoreCase("asc")){
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(column)));
        } else if(type.equalsIgnoreCase("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(column)));
        }

        criteriaQuery.orderBy(criteriaBuilder.asc(root.get(column)));
        Query<Teacher> query = session.createQuery(criteriaQuery);
        List<Teacher> results = query.getResultList();

        for(Teacher i : results)
            System.out.println(i);

        session.close();
        hibernateFactory.getSessionFactory().close();
        return results;
    }


}
