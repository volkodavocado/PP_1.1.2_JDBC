package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    //todo: codeStyle, привыкаем аккуратно работать
    //todo: + общие замечания (описаны в группе)
    //todo: README просьба заполнять..
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "my179sql";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";

    public static Connection getConnection() {//todo: избавляемся от static/ сломали парадигму ООП..
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}