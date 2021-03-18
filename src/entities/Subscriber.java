package entities;

import connection.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Subscriber extends Account implements User {

    //attributes
    private String gender;
    private LocalDate birthDate;

    //dataBase Connection
    DbConnection dbConnection = new DbConnection();
    Connection connection = dbConnection.getConnection();
    String query;

    public Subscriber(String password, String gender, LocalDate birthDate) {
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    //setters & getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getGender() {
        return gender;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    //constructors
    public Subscriber(String username, String password, Object gender, LocalDate birthDate) {
        this.password = password;
        this.username = username;
        this.gender = (String) gender;
        this.birthDate = birthDate;
    }
    public Subscriber(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Subscriber() {

    }
    public Subscriber(String username) {
        this.username = username;
    }

    //exists method
    public Boolean exists() throws SQLException {
        query = "select * from subscribers where username = '" + username+ "' and password = '" + password+ "'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet.first();
    }

    //searchUsername method
    public Boolean searchUsername() throws  SQLException {
        query = "select * from subscribers where username = '" + username+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet  = statement.executeQuery(query);
        return resultSet.first();
    }

    //login method
    @Override
    public boolean login(String username, String password) throws SQLException {
        query = "select * from subscribers where username = '" + username+ "' and password = '" + password+ "'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet.first();
    }

    //subscription method
    @Override
    public boolean createAccount() throws SQLException {
        query = "insert into subscribers values ('"+getUsername()+"', '"+getPassword()+"', '"+getGender()+"', '"+getBirthDate()+"')";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        int result = statement.executeUpdate(query);
        return (result == 1);
    }

    //update account
    public Boolean modifyAccount(Subscriber subscriber) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

       //query = "update subscribers set username ='"+subscriber.getUsername()+"', password= '"+subscriber.getPassword()+"', gender = '"+subscriber.getGender()+"', birthdate = '"+subscriber.getBirthDate().toString()+" where username = '"+this.username+"'";
        query = "update subscribers set password= '"+subscriber.getPassword()+"', gender = '"+subscriber.getGender()+"', birthdate = '"+subscriber.getBirthDate().toString()+"' where username = '"+this.username+"'";
        int result = statement.executeUpdate(query);
        return (result == 1);
    }

    //delete account
    public Boolean deleteAccount() throws SQLException {
        query = "delete from subscribers where username = '"+this.username+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        int resultSet = statement.executeUpdate(query);
        return (resultSet == 1);
    }

    //show account
    public User consultAccount(String usernameS) throws SQLException {
        query = "select * from subscribers where username = '" +usernameS+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.first()) {
            User user = new Subscriber(resultSet.getString("username"), resultSet.getString("password"), resultSet.getObject("gender"), resultSet.getDate("birthDate").toLocalDate());
            System.out.println(user.toString());
            return user;
        }
        else return null;
    }

    //show music
    public ResultSet showMusic() throws SQLException {
        query = "select * from music";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first()) return resultSet;
        else return null;
    }

    //show specific type of music
    public ResultSet showSpecificTypeOfMusic(String style) throws SQLException, NullPointerException{
        query = "select * from music where style = '"+style+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first())
            return resultSet;
        else return null;
    }

    //show tvSeries
    public ResultSet showSeries() throws SQLException {
        query = "select * from shows";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first()) return resultSet;
        else return null;
    }

    //show specific genre of tvSeries
    public ResultSet showSpecificTypeOfTvSeries(String genre) throws SQLException, NullPointerException{
        query = "select * from shows where genre = '"+genre+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first())
            return resultSet;
        else return null;
    }

    //show books
    public ResultSet showBooks() throws SQLException {
        query = "select * from books";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first()) return resultSet;
        else return null;
    }

    //show specific books depending on the author chosen
    public ResultSet showSpecificBooks(String author) throws SQLException, NullPointerException{
        query = "select * from books where author = '"+author+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first())
            return resultSet;
        else return null;
    }

    public ResultSet showIllness(String illness) throws SQLException, NullPointerException{
        query = "select * from illness where title = '"+illness+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first())
            return resultSet;
        else return null;
    }

    public ResultSet showNote(String noteTitle) throws SQLException, NullPointerException{
        query = "select * from note where title = '"+noteTitle+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.first())
            return resultSet;
        else return null;
    }

    public Boolean deleteNote(String noteTile) throws SQLException {
        query = "delete from note where title = '"+noteTile+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        int resultSet = statement.executeUpdate(query);
        return (resultSet == 1);
    }

    public Boolean modifyNote(NoteBook note) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        query = "update note set description = '" +note.getDescription()+ "' where title = '"+note.getTitle()+"'";
        int result = statement.executeUpdate(query);
        return (result == 1);
    }

    public boolean addNote(NoteBook note) throws SQLException {
        query = "insert into note values ('"+note.getTitle()+"', '"+note.getDescription()+"')";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        int result = statement.executeUpdate(query);
        return (result == 1);
    }

    public ResultSet searchNote(String title) throws  SQLException {
        query = "select * from note where title = '" +title+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet  = statement.executeQuery(query);
        if(resultSet.first()) return resultSet;
        else return null;
    }

    public Boolean searchNote(NoteBook noteBook) throws  SQLException {
        query = "select * from note where title = '" +noteBook.getTitle()+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet  = statement.executeQuery(query);
        return resultSet.next();
    }

    public ResultSet showNotes() throws  SQLException {
        query = "select title from note";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet  = statement.executeQuery(query);
        if(resultSet.first()) return resultSet;
        else return null;
    }
}
