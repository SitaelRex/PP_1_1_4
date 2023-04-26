package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.util.List;

import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {
        Session session = Util.getHibernateSession();
        Transaction transaction = session.beginTransaction();
        String query = "CREATE TABLE IF NOT EXISTS users (id int NOT NULL AUTO_INCREMENT,name varchar(45) NOT NULL,lastName varchar(45) NOT NULL,age int DEFAULT NULL, PRIMARY KEY (id)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3";
        session.createSQLQuery(query).addEntity(User.class).executeUpdate();

        transaction.commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getHibernateSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getHibernateSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getHibernateSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getHibernateSession();
        Transaction transaction = session.beginTransaction();
        List<User> result = session.createQuery("SELECT a FROM User a", User.class).getResultList();
        transaction.commit();
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getHibernateSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE User a").executeUpdate();
        transaction.commit();
    }
}
