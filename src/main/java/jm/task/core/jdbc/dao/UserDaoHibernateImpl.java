package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Session session;

    public UserDaoHibernateImpl() {
        this.session = new Util().getSessionFactory().openSession();
    }

    String createUsersQuery = "CREATE TABLE IF NOT EXISTS Users " + "(id INTEGER AUTO_INCREMENT, " + " name VARCHAR(255), " + " lastName VARCHAR(255), " + " age TINYINT, " + " PRIMARY KEY ( id ))";

    String dropUsersQuery = "DROP TABLE IF EXISTS Users";

    String getAllUsersQuery = "SELECT * FROM Users";

    String cleanUsersQuery = "TRUNCATE TABLE Users";


    @Override
    public void createUsersTable() {
        Transaction transaction = session.beginTransaction();
        Query<?> query = session.createSQLQuery(createUsersQuery);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = session.beginTransaction();
        Query<?> query = session.createSQLQuery(dropUsersQuery);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = session.beginTransaction();
        session.remove(session.find(User.class, id));
        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        return session.createNativeQuery(getAllUsersQuery, User.class).list();
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = session.beginTransaction();
        Query<?> query = session.createSQLQuery(cleanUsersQuery);
        query.executeUpdate();
        transaction.commit();
    }
}
