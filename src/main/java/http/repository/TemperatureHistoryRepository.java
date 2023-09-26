package http.repository;

import crud.repository.CrudRepository;
import http.model.entity.TemperatureHistory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

import static http.utils.HibernateUtil.getSessionFactory;

public class TemperatureHistoryRepository implements CrudRepository<TemperatureHistory, Long> {

    @Override
    public Optional<TemperatureHistory> findById(final Long id) {
        try (Session session = getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(TemperatureHistory.class, id));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public TemperatureHistory save(final TemperatureHistory temperatureHistory) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(temperatureHistory);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return temperatureHistory;
    }

    @Override
    public void delete(final Long id) {
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TemperatureHistory temperatureHistory = session.get(TemperatureHistory.class, id);
            if (temperatureHistory != null) {
                session.remove(temperatureHistory);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<TemperatureHistory> findAll() {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("FROM TemperatureHistory ", TemperatureHistory.class).list();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

