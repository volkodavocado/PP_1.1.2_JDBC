package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection;

    public UserDaoJDBCImpl() {
        this.connection = new Util().getConnection();
    }

    String createUsersQuery = "CREATE TABLE IF NOT EXISTS Users " +
            "(id INTEGER AUTO_INCREMENT, " +
            " name VARCHAR(255), " +
            " lastName VARCHAR(255), " +
            " age TINYINT, " +
            " PRIMARY KEY ( id ))";

    String dropUsersQuery = "DROP TABLE IF EXISTS Users";

    String insertQuery = "INSERT INTO Users(name, lastName, age) VALUES (?, ?, ?)";

    String deleteUserQuery = "DELETE FROM Users WHERE id = ?";

    String getAllUsersQuery = "SELECT * FROM Users";

    String cleanUsersQuery = "TRUNCATE TABLE Users";

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(createUsersQuery);
        } catch (SQLException e) {
            System.out.println("An error occurred while creating the table" + e.getMessage());
        }
        System.out.println("Table is created successful by JDBC");
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(dropUsersQuery);
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the table" + e.getMessage());
        }
        System.out.println("Table is deleted successful by JDBC");
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while saving the user" + e.getMessage());
        }
        System.out.println("User is saved successful by JDBC");
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(deleteUserQuery)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the user" + e.getMessage());
        }
        System.out.println("User is deleted successful by JDBC");
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement =
                     connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllUsersQuery);
            while (resultSet.next()) {
                User newUser = new User();
                newUser.setId(resultSet.getLong(1));
                newUser.setName(resultSet.getString(2));
                newUser.setLastName(resultSet.getString(3));
                newUser.setAge(resultSet.getByte(4));
                userList.add(newUser);
                System.out.println(newUser);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while getting all users" + e.getMessage());
        }
        System.out.println("All users are added successful by JDBC");
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(cleanUsersQuery);
        } catch (SQLException e) {
            System.out.println("An error occurred while cleaning the table" + e.getMessage());
        }
        System.out.println("Table is cleaned successful by JDBC");
    }
}
