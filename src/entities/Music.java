package entities;

import connection.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Music {

    private String title ;
    private String  description ;

    public Music(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Music() {}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    DbConnection dbConnection = new DbConnection();
    Connection connection = dbConnection.getConnection();
    String query;

    /*
    public ResultSet showMusic() throws SQLException {
        query = "select * from music";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first()) return resultSet;
        else return null;
    }

    public ResultSet showSpecificTypeOfMusic(String style) throws SQLException, NullPointerException{
        query = "select * from music where type = '"+style+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first())
            return resultSet;
        else return null;
    }

    */
}
