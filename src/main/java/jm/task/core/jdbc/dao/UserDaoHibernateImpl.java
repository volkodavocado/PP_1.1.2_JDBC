package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = new Util().getSessionFactory();
    }

    private final String getAllUsersQuary = "SELECT * FROM Users";
    private final String cleanUsersQuery = "TRUNCATE TABLE Users";

    @Override
    public void createUsersTable() {
    }

    @Override
    public void dropUsersTable() {
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.save(new User(name, lastName, age));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.remove(session.find(User.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createSQLQuery(getAllUsersQuary).addEntity(User.class)
                    .list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tr = session.beginTransaction();
            session.createSQLQuery(cleanUsersQuery).executeUpdate();
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}