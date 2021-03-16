package sample;

import entities.Subscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class SighUp {

    ObservableList<String> genders = FXCollections.observableArrayList("male", "female");

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private DatePicker birthDate;
    @FXML
    private ComboBox<String> gender;
    @FXML
    Label subscriptionError;

    @FXML
    private void initialize() {
        gender.setValue("female");
        gender.setItems(genders);
    }


    // create account method
    public void createAccount(ActionEvent actionEvent) throws SQLException {

        if (username.getText().isEmpty() || password.getText().isEmpty() || gender.getValue() == null || birthDate.getValue() == null) {
            subscriptionError.setText("Please enter your data");
        }
        else{
            Subscriber subscriber = new Subscriber(username.getText(), password.getText(), gender.getValue(), birthDate.getValue());
            Boolean exists = subscriber.searchUsername();
            if(exists) {
                subscriptionError.setText("Username already used, please change it!");
                username.clear();
            }
            else {
                subscriber.createAccount();
                subscriptionError.setText("You have subscribed successfully!");
                username.clear();
                password.clear();
            }
        }
    }
}
