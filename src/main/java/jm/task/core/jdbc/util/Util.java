package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "my179sql";

    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();

        settings.put(Environment.DRIVER, DB_DRIVER);
        settings.put(Environment.URL, DB_URL);
        settings.put(Environment.USER, DB_USER);
        settings.put(Environment.PASS, DB_PASSWORD);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");
        settings.put(Environment.SHOW_SQL, "true");

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(User.class);

        return configuration.buildSessionFactory();
    }
}