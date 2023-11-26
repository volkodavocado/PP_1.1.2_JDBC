package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {

        UserDao userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.dropUsersTable();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Gendalf", "White", (byte) 120);
        System.out.println("User с именем Gendalf добавлен в базу данных");
        userDaoJDBC.saveUser("Frodo", "Baggins", (byte) 35);
        System.out.println("User с именем Frodo добавлен в базу данных");
        userDaoJDBC.saveUser("Ulfric", "Stormcloak", (byte) 44);
        System.out.println("User с именем Ulfric добавлен в базу данных");
        userDaoJDBC.saveUser("Cole", "Phelps", (byte) 27);
        System.out.println("User с именем Cole добавлен в базу данных");
        System.out.println(userDaoJDBC.getAllUsers().toString());
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
