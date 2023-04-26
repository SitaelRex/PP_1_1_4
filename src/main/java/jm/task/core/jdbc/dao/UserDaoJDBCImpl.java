package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.connectDatabase(); Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (id int NOT NULL AUTO_INCREMENT,name varchar(45) NOT NULL,lastName varchar(45) NOT NULL,age int DEFAULT NULL, PRIMARY KEY (id)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3");
        } catch (SQLException e) {
            System.out.println("НЕ УДАЛОСЬ СОЗДАТЬ ТАБЛИЦУ ЮЗЕРОВ\n" + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.connectDatabase(); Statement statement = connection.createStatement();) {
            statement.execute("DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            System.out.println("ОШИБКА УДАЛЕНИЯ ТАБЛИЦЫ\n" + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.connectDatabase(); Statement statement = connection.createStatement();) {
            String insertTemplate = "INSERT INTO users(name, lastName, age) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTemplate);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
            System.out.println("User с именем – "+ name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("НЕ УДАЛОСЬ СОЗДАТЬ ЮЗЕРА\n" + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.connectDatabase(); Statement statement = connection.createStatement();) {
            String insertTemplate = "DELETE FROM users WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTemplate);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("ОШИБКА УДАЛЕНИЯ ТАБЛИЦЫ\n" + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.connectDatabase(); Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");//statement.getResultSet();
            List<User> result = new ArrayList<User>();
            while (resultSet.next()) {
                User user = new User((String) resultSet.getObject(2), (String) resultSet.getObject(3), resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            System.out.println("ОШИБКА УДАЛЕНИЯ ТАБЛИЦЫ\n" + e.getMessage());
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.connectDatabase(); Statement statement = connection.createStatement();) {
            statement.execute("DELETE FROM users;");
        } catch (SQLException e) {
            System.out.println("ОШИБКА УДАЛЕНИЯ ТАБЛИЦЫ\n" + e.getMessage());
        }
    }
}
