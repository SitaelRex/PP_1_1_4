package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "sroot";
    private static final String PASSWORD = "root";
    private static SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(User.class)
            .buildSessionFactory();
    public static Connection connectDatabase() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static Session getHibernateSession(){
        return sessionFactory.getCurrentSession();
    }

    public static void closeHibernateFactory() {
        sessionFactory.getCurrentSession().close();
        sessionFactory.close();
    }
}
