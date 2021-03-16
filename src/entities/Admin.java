package entities;

import java.sql.SQLException;

public class Admin extends Account{

    public Admin() {
    }

    public Admin(String username, String password) {
        super.password = password;
        super.username = username;
    }

    public boolean exists() throws SQLException {
        if( (this.username.equals("admin")) && (this.password.equals("admin123")))
            return true;
        else return false;
    }

    @Override
    public boolean login(String username, String password) throws SQLException {
        return this.exists();
    }
}
