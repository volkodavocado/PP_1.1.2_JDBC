package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
                                //todo: codeStyle - сливается код..
    //Session session;//todo: благодаря SessionFactory - Session для нас уже не является дорогой операцией и может(и должна) использоваться в каждом методе

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = new Util().getSessionFactory();
    }

    private String createUsersQuery = "CREATE TABLE IF NOT EXISTS Users "
            + "(id INTEGER AUTO_INCREMENT, "
            + " name VARCHAR(255), "
            + " lastName VARCHAR(255), "
            + " age TINYINT, "
            + " PRIMARY KEY ( id ))";//todo потеряли модификатор

    String dropUsersQuery = "DROP TABLE IF EXISTS Users";

    String getAllUsersQuery = "SELECT * FROM Users";

    String cleanUsersQuery = "TRUNCATE TABLE Users";


    @Override
    public void createUsersTable() {//todo: в кажлом методе используем try_with_resources  - для .openSession();
//        Transaction transaction = session.beginTransaction();//todo: используем аннотацию Transaction (над класом), параметризированную READONLY - над нужными методами
        Query<?> query = session.createSQLQuery(createUsersQuery);
        //todo: правильно подавать запрос при работе с Session
        sessionFactory.getCurrentSession().createQuery(...)

        query.executeUpdate();
//        transaction.commit();
    }

    @Override
    public void dropUsersTable() {
//        Transaction transaction = session.beginTransaction();
        Query<?> query = session.createSQLQuery(dropUsersQuery);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        //todo:   в продолжении мысли -> например:    sessionFactory.getCurrentSession().save(..);  работаем так, то есть НЕ ЧЕРЕЗ SQL

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
