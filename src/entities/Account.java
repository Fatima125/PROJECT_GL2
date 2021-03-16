package entities;

import java.sql.SQLException;

public abstract class Account {
    String username;
    String password;

    public abstract boolean login(String username, String password) throws SQLException;
}
