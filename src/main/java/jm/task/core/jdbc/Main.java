package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.saveUser("Gendalf", "White", (byte) 120);
        System.out.println("User с именем Gendalf добавлен в базу данных");
        userDao.saveUser("Frodo", "Baggins", (byte) 35);
        System.out.println("User с именем Frodo добавлен в базу данных");
        userDao.saveUser("Ulfric", "Stormcloak", (byte) 44);
        System.out.println("User с именем Ulfric добавлен в базу данных");
        userDao.saveUser("Cole", "Phelps", (byte) 27);
        System.out.println("User с именем Cole добавлен в базу данных");
        userDao.removeUserById(1);
        System.out.println("User с id 1 удален из таблицы");
        System.out.println(userDao.getAllUsers().toString());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}