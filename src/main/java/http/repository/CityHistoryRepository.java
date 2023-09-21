package http.repository;

import crud.repository.CrudRepository;
import http.model.entity.CityHistory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class CityHistoryRepository implements CrudRepository<CityHistory, Long> {

    private final SessionFactory sessionFactory;

    public CityHistoryRepository(final SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public Optional<CityHistory> findById(final Long id) {
        Session session = sessionFactory.openSession();
        CityHistory cityHistory = null;

        try {
            cityHistory = session.get(CityHistory.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return Optional.ofNullable(cityHistory);
    }

    @Override
    public CityHistory save(final CityHistory cityHistory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(cityHistory);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return cityHistory;
    }


    @Override
    public void delete(final Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            CityHistory cityHistory = session.get(CityHistory.class, id);
            if (cityHistory != null) {
                session.remove(cityHistory);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<CityHistory> findAll() {
        Session session = sessionFactory.openSession();
        List<CityHistory> cityHistories = null;

        try {
            cityHistories = session.createQuery("FROM CityHistory", CityHistory.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return cityHistories;
    }
}


