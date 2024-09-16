package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }
@Override
    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS db_table (" +
        "id INT AUTO_INCREMENT PRIMARY KEY," +
        "name VARCHAR(255)," +
        "lastName VARCHAR(255)," +
        "age TINYINT" +
        ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
@Override
    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS katadb1,db_table;";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
@Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO db_table (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
@Override
    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM db_table WHERE id=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
@Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String SQL = "SELECT id, name, lastName, age FROM db_table";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
@Override
    public void cleanUsersTable() {
        String SQL = "TRUNCATE TABLE db_table";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
