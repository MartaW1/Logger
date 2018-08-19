package repository;

import model.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Repository {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


    public static void save(Event event){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
           transaction = session.beginTransaction();
            session.persist(event);
            session.flush();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public static boolean existsById(String id) {
        Session session = sessionFactory.openSession();
        Event event = session.get(Event.class, id);
        session.close();
        if (event != null){
            return true;
        } else {
            return false;
        }
    }

    public static List<Event> getEvents() {
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Event> events = session.createQuery("FROM Event").list();
        session.close();
        return events;
    }
}

