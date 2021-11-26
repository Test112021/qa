package pl.jsystems.qa.qadatabase.database;

import pl.jsystems.qa.qadatabase.config.DbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            initConnection();
        }
        return connection;
    }

    private static void initConnection() {
        try {
            Class.forName(DbConfig.DB_CLASS);
            final String url = DbConfig.DB_URL;
            final String user = DbConfig.DB_USER;
            final String pass = DbConfig.DB_PASS;
            connection = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
