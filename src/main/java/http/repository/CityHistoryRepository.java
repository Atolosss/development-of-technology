package http.repository;

import crud.repository.CrudRepository;
import http.model.entity.CityHistory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import java.util.List;
import java.util.Optional;

import static http.utils.HibernateUtil.getSessionFactory;

public class CityHistoryRepository implements CrudRepository<CityHistory, Long> {


    @Override
    public Optional<CityHistory> findById(final Long id) {
        try (var session = getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(CityHistory.class, id));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Optional<CityHistory> findByName(final String name) {
        var select = "select city from CityHistory city where city.city=:name";

        try (var session = getSessionFactory().openSession()) {
            SelectionQuery<CityHistory> query = session.createSelectionQuery(select, CityHistory.class)
                    .setParameter("name", name);
            return query.getResultList().stream().findFirst();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public CityHistory save(final CityHistory cityHistory) {
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(cityHistory);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return cityHistory;
    }


    @Override
    public void delete(final Long id) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            var cityHistory = session.get(CityHistory.class, id);
            if (cityHistory != null) {
                session.remove(cityHistory);
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
    public List<CityHistory> findAll() {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("FROM CityHistory", CityHistory.class).list();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}


