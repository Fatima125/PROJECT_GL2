package entities;

import java.sql.SQLException;

public class Admin extends Account{

    @Override
    public boolean login(String username, String password) throws SQLException {
        if( (username.equals("admin")) && (password.equals("admin123")))
            return true;
        else return false;
    }
}
