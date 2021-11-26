package pl.jsystems.qa.qadatabase.database;

import pl.jsystems.qa.qadatabase.database.model.UserDb;

import javax.activation.DataContentHandler;
import javax.xml.crypto.Data;
import java.awt.image.DataBuffer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static List<UserDb> getAllUsers() {

        List<UserDb> users = new ArrayList<>();

        String sql = "select * from testuser";

        try (Statement statement = DatabaseConnector.getConnection().createStatement(); ResultSet wynik = statement.executeQuery(sql)) {

            while (wynik.next()) {
                UserDb userDb = new UserDb(wynik.getString(1), wynik.getString(2), wynik.getString(3));
                users.add(userDb);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    public static UserDb getUserById(String id) {
        String sql = "select * from testuser where id ='" + id + "'";

        UserDb userDb = null;
        try (Statement statement = DatabaseConnector.getConnection().createStatement(); ResultSet wynik = statement.executeQuery(sql)) {
            while (wynik.next()) {
                userDb = new UserDb(wynik.getString(1), wynik.getString(2), wynik.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userDb;
    }

    public static void saveUser(UserDb userDb) {
        String sql = "insert into testuser (id, name, surname) values ('" + userDb.id + "', '" + userDb.name + "', '" + userDb.surname +"')";
        Statement statement = null;
        try {
            statement = DatabaseConnector.getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void deleteUser(String id) {
        String sql = "delete testuser where id = '" + id + "'";
        Statement statement = null;
        try {
            statement = DatabaseConnector.getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void updateUser(UserDb userDb, String id) {
        String sql = "update testuser set id = '" + userDb.id + "', name = '" + userDb.name + "', surname = '" + userDb.surname + "' where id = '" + id + "'";

        Statement statement = null;
        try {
            statement = DatabaseConnector.getConnection().createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
