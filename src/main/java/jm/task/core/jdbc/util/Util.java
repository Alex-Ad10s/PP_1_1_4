package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import java.sql.Connection;

import org.hibernate.SessionFactory;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
public class Util {
    private static final String USER_NAME ="root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/katadb1";
    public static Connection getConnection() {
    Connection connection = null;
        try {
            Class.forName("java.sql.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USER_NAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL,"true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                Configuration configuration = new Configuration();
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
