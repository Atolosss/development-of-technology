package http.repository;

import crud.repository.CrudRepository;
import http.model.entity.TemperatureHistory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class TemperatureHistoryRepository implements CrudRepository<TemperatureHistory, Long> {

    private final SessionFactory sessionFactory;

    public TemperatureHistoryRepository(final SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public Optional<TemperatureHistory> findById(final Long id) {
        Session session = sessionFactory.openSession();
        TemperatureHistory temperatureHistory = null;

        try {
            temperatureHistory = session.get(TemperatureHistory.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Optional.ofNullable(temperatureHistory);
    }

    @Override
    public TemperatureHistory save(final TemperatureHistory temperatureHistory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(temperatureHistory);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return temperatureHistory;
    }

    @Override
    public void delete(final Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            TemperatureHistory temperatureHistory = session.get(TemperatureHistory.class, id);
            if (temperatureHistory != null) {
                session.remove(temperatureHistory);
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
    public List<TemperatureHistory> findAll() {
        Session session = sessionFactory.openSession();
        List<TemperatureHistory> temperatureHistories = null;

        try {
            temperatureHistories = session.createQuery("FROM TemperatureHistory ", TemperatureHistory.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return temperatureHistories;
    }
}

