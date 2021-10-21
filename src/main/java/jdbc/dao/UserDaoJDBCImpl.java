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
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS jmp2 " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "name VARCHAR (45), lastname VARCHAR (45), age INT )");
            connection.commit();
            System.out.println("Таблица создана...");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS jmp2");
            connection.commit();
            System.out.println("Таблица удалена...");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        Statement statement = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO jmp2 (name, lastname, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Пользователь сохранен...");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }

    }

    public void removeUserById(long id) {
        Statement statement = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM jmp2 WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
            System.out.println("Удален пользователь id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {
        Statement statement = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM jmp2");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE jmp2");
            connection.commit();
            System.out.println("Таблица очищена...");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
