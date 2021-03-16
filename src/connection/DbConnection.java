package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection connection = null;
    public java.sql.Connection getConnection() {
        try {
            String url = "jdbc:postgresql:gldatabase?user=postgres&password=admin";
            connection = DriverManager.getConnection(url);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return connection;
    }
}
