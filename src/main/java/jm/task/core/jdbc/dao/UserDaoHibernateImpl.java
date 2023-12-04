package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String createQuery = "CREATE TABLE IF NOT EXISTS Users "
            + "(id INTEGER AUTO_INCREMENT, "
            + " name VARCHAR(255), "
            + " lastName VARCHAR(255), "
            + " age TINYINT, "
            + " PRIMARY KEY ( id ))";
    private final String dropQuery = "DROP TABLE IF EXISTS Users";
    private final String getAllUsersQuary = "SELECT * FROM Users";
    private final String cleanUsersQuery = "TRUNCATE TABLE Users";
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = new Util().getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<?> query = session.createSQLQuery(createQuery);
            query.executeUpdate();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = new Util().getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<?> query = session
                    .createSQLQuery(dropQuery);
            query.executeUpdate();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = new Util().getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = new Util().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(session.find(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = new Util().getSessionFactory().openSession()) {
            session.beginTransaction();
            return session
                    .createSQLQuery(getAllUsersQuary).addEntity(User.class)
                    .list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = new Util().getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<?> query = session.createSQLQuery(cleanUsersQuery);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}