package sample;

import entities.Subscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ManageAccount {

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
    private void initialize() {
        gender.setValue("female");
        gender.setItems(genders);
    }


    public void showMenu(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("menuSubscriber.fxml");
        menu.setStageTitle("HOME");
    }

    public void showAccount(ActionEvent actionEvent) throws SQLException {
    }

    public void updateAccount(ActionEvent actionEvent) {
    }

    public void deleteAccount(ActionEvent actionEvent) {
    }

    public void showUpdatable(ActionEvent actionEvent) {
    }
}
