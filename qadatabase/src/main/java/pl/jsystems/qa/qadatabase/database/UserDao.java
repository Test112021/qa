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
}
