package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.dropUsersTable();
        userDao.createUsersTable();

        userDao.saveUser("Gendalf", "White", (byte) 120);
        userDao.saveUser("Frodo", "Baggins", (byte) 35);
        userDao.saveUser("Ulfric", "Stormcloak", (byte) 44);
        userDao.saveUser("Cole", "Phelps", (byte) 27);

        userDao.removeUserById(1);
        userDao.getAllUsers().forEach(x -> System.out.println(x.toString()));
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}