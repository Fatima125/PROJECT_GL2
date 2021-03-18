package entities;

import connection.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin extends Account{

    //dataBase Connection
    DbConnection dbConnection = new DbConnection();
    Connection connection = dbConnection.getConnection();
    String query;

    public Admin() {
        this.username = "admin";
        this.password = "admin123";
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

    //delete account
    public Boolean deleteAccount(String username) throws SQLException {
        query = "delete from subscribers where username = '"+username+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        int resultSet = statement.executeUpdate(query);
        return (resultSet == 1);
    }

    //searchUsername method
    public Boolean searchUsername(String username) throws  SQLException {
        query = "select * from subscribers where username = '" + username+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet  = statement.executeQuery(query);
        return resultSet.first();
    }

    public ResultSet showSubscribers() throws SQLException {
        query = "select * from subscribers";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first()) return resultSet;
        else return null;
    }

    public ResultSet showMaleNumber() throws SQLException {
        query = "select count(*) from subscribers where gender = 'male'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first()) return resultSet;
        else return null;
    }

    public ResultSet showFemaleNumber() throws SQLException {
        query = "select count(*) from subscribers where gender = 'female'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first()) return resultSet;
        else return null;
    }

    @Override
    public boolean login(String username, String password) throws SQLException {
        return this.exists();
    }
}
