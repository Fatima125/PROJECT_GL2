package entities;

import java.sql.SQLException;

public interface User {
    boolean createAccount() throws SQLException;
}
