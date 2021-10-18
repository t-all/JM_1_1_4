package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE jmp2 (id BIGINT NOT NULL AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "name VARCHAR (45), lastname VARCHAR (45), age INT )");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE jmp2");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO jmp2 " +
                    "(name, lastname, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM jmp2 WHERE id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM jmp2");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE jmp2");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
